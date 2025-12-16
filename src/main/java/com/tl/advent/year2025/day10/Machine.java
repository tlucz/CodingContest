package com.tl.advent.year2025.day10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Machine {

    private final boolean[] part1IndicatorDesiredState;
    private List<List<Integer>> buttonsConfiguration;
    private final int[] desiredJoltage;
    private final String line;
    Map<Integer, List<Integer>> buttonsIndexesForIndicator = new HashMap<>();

    public Machine(String line) {
        this.line = line;
        //[.###.#] (0,1,2,3,4) (0,3,4) (0,1,2,4,5) (1,2) {10,11,11,5,10,5}
        int closingSquareIdx = line.indexOf(']');
        int openingCurly = line.indexOf('{');
        String indicTxt = line.substring(1, closingSquareIdx);
        String switchTxt = line.substring(closingSquareIdx + 2, openingCurly - 1);
        String joltageTxt = line.substring(openingCurly + 1, line.length() - 1);
        part1IndicatorDesiredState = new boolean[indicTxt.length()];
        for (int i = 0; i < indicTxt.length(); i++) {
            if (indicTxt.charAt(i) == '#') {
                part1IndicatorDesiredState[i] = true;
            }
        }

        buttonsConfiguration = new ArrayList<>();
        for (String s : switchTxt.split(" ")) {
            List<Integer> indicatorsToSwitch = Arrays.stream(s.replace("(", "").replace(")", "").split(","))
                    .map(v -> Integer.parseInt(v)).toList();
            buttonsConfiguration.add(indicatorsToSwitch);
        }
        desiredJoltage = Arrays.stream(joltageTxt.split(",")).mapToInt(v -> Integer.parseInt(v)).toArray();
//        sortedButtonsConfiguration = sortedButtonsConfiguration.stream().sorted((o1, o2) -> Integer.compare(o2.size(), o1.size())).toList();
    }

    public int configure() {
        PriorityQueue<MachineState> queue = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.pressedButtons, o2.pressedButtons));
        queue.add(new MachineState(new boolean[part1IndicatorDesiredState.length], 0));
        MachineState stateToAnalyze;
        while ((stateToAnalyze = queue.poll()) != null) {
            if (areArraysEqual(stateToAnalyze.indState, part1IndicatorDesiredState)) {
                return stateToAnalyze.pressedButtons;
            }
            for (List<Integer> button : buttonsConfiguration) {
                boolean[] newIndState = copyArray(stateToAnalyze.indState);
                button.forEach(indIdx -> newIndState[indIdx] = !newIndState[indIdx]);
                queue.add(new MachineState(newIndState, stateToAnalyze.pressedButtons + 1));
            }
        }
        throw new RuntimeException("not found");
    }

    public int configurePart2SecondAttempt() {

        for (int i = 0; i < desiredJoltage.length; i++) {
            buttonsIndexesForIndicator.put(i, findButtonsIndexesForIndicator(i));
        }
        MachineState2 initialMachineState = new MachineState2(new int[desiredJoltage.length], 0);

        // maybe sort indicators
        MachineState2 bestState = getBestMachineState(initialMachineState);
        return bestState.pressedButtons;
    }

    private MachineState2 getBestMachineState(MachineState2 currentState) {
        if (areArraysEqual(currentState.joltageState, desiredJoltage)) {
            return currentState;
        }
        if (exceeds(currentState.joltageState, desiredJoltage)) {
            return null;
        }

        int[] joltageDiffs = calculateDiff(desiredJoltage, currentState.joltageState);
        int indicatorToAdjustIdx = findLowestPositiveValueIndex(joltageDiffs);
//        int indicatorToAdjustIdx = findHighestPositiveValueIndex(joltageDiffs);
        int missingValue = joltageDiffs[indicatorToAdjustIdx];

        List<Integer> buttonsIndexesForIndicatorToAdjust = buttonsIndexesForIndicator.get(indicatorToAdjustIdx);
        List<List<Integer>> buttonsThatIncreasesIndicator = buttonsIndexesForIndicatorToAdjust
                .stream().map(idx -> buttonsConfiguration.get(idx))
                .toList();
        // generate all posibilities to achieve missing value
        // limit it to not add buttons that would exceeds desired joltage for indicator
        List<Integer> maxClicksForButton = calculateMaxPossibleClicksForButton(currentState, buttonsThatIncreasesIndicator);
        if(maxClicksForButton.stream().allMatch(i->i==0)) {
            return null;
        }
        // reduce amount of the possibilities by eminating not possible button and also adjust maxClicks for button inside the method dynamically
        List<int[]> clickForButtonPossibilities = generateAllPossibilities2(missingValue, maxClicksForButton);

        MachineState2 bestStateSoFar = null;
        for (int[] clickForButtonsPossibility : clickForButtonPossibilities) {
            int[] newState = copyArray(currentState.joltageState);
            for (int i = 0; i < clickForButtonsPossibility.length; i++) {
                List<Integer> buttonToInvoke = buttonsThatIncreasesIndicator.get(i);
                int numberOfClicks = clickForButtonsPossibility[i];
                for (Integer ind : buttonToInvoke) {
                    newState[ind] += numberOfClicks;
                }
            }
            if (exceeds(newState, desiredJoltage)) {
                continue;
            }
            //TODO: to not click the same button next time
            MachineState2 bestState = getBestMachineState(new MachineState2(newState, currentState.pressedButtons + missingValue));
            if (bestState != null) {
                if (bestStateSoFar == null || bestStateSoFar.pressedButtons > bestState.pressedButtons) {
                    bestStateSoFar = bestState;
                }
            }
        }
        return bestStateSoFar;
    }

    private List<Integer> calculateMaxPossibleClicksForButton(MachineState2 currentState, List<List<Integer>> buttonsThatIncreasesIndicator) {
        List<Integer> maxClicks = new ArrayList<>();
        for (List<Integer> button : buttonsThatIncreasesIndicator) {
            maxClicks.add(button.stream().mapToInt(ind -> desiredJoltage[ind] - currentState.joltageState[ind]).min().getAsInt());
        }
        return maxClicks;
    }

    private int findLowestPositiveValueIndex(int[] joltageDiffs) {
        int bestIdx = -1;
        int bestValue = Integer.MAX_VALUE;
        for (int i = 0; i < joltageDiffs.length; i++) {
            if (joltageDiffs[i] > 0 && joltageDiffs[i] < bestValue) {
                bestIdx = i;
                bestValue = joltageDiffs[i];
            }
        }
        return bestIdx;
    }

    private int findHighestPositiveValueIndex(int[] joltageDiffs) {
        int bestIdx = -1;
        int bestValue = Integer.MIN_VALUE;
        for (int i = 0; i < joltageDiffs.length; i++) {
            if (joltageDiffs[i] > 0 && joltageDiffs[i] > bestValue) {
                bestIdx = i;
                bestValue = joltageDiffs[i];
            }
        }
        return bestIdx;
    }

    private List<Integer> findButtonsIndexesForIndicator(int indicator) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < buttonsConfiguration.size(); i++) {
            if (buttonsConfiguration.get(i).contains(indicator)) {
                result.add(i);
            }
        }
        return result;
    }

    private List<int[]> generateAllPossibilities(int value, int arraySize) {
        if (arraySize == 1) {
            return List.of(new int[]{value});
        } else {
            List<int[]> possibilities = new ArrayList<>();
            for (int i = 0; i <= value; i++) {
                List<int[]> subpossibilities = generateAllPossibilities(value - i, arraySize - 1);
                for (int[] subpossibility : subpossibilities) {
                    int[] newPossibility = new int[arraySize];
                    newPossibility[0] = i;
                    System.arraycopy(subpossibility, 0, newPossibility, 1, subpossibility.length);
                    possibilities.add(newPossibility);
                }
            }
            return possibilities;
        }
    }

    private List<int[]> generateAllPossibilities2(int totalValue, List<Integer> maxClicksForButton) {
        if (maxClicksForButton.stream().mapToInt(v -> v).sum() < totalValue) {
            return List.of();
        }
        if (maxClicksForButton.size() == 1) {
            return List.of(new int[]{Math.min(maxClicksForButton.getFirst(), totalValue)});
        } else {
            List<int[]> possibilities = new ArrayList<>();
            for (int i = 0; i <= Math.min(maxClicksForButton.getFirst(), totalValue); i++) {
                List<int[]> subpossibilities = generateAllPossibilities2(totalValue - i, maxClicksForButton.subList(1, maxClicksForButton.size()));
                for (int[] subpossibility : subpossibilities) {
                    int[] newPossibility = new int[maxClicksForButton.size()];
                    newPossibility[0] = i;
                    System.arraycopy(subpossibility, 0, newPossibility, 1, subpossibility.length);
                    possibilities.add(newPossibility);
                }
            }
            return possibilities;
        }
    }

    public int configurePart2() {
        MachineState2 initialMachineState = new MachineState2(new int[desiredJoltage.length], 0);

        MachineState2 bestState = getBestMachineStateAttempt1(0, initialMachineState);

        return bestState.pressedButtons;
    }

    private MachineState2 getBestMachineStateAttempt1(int buttonIdx, MachineState2 currentState) {
        List<Integer> indicatorsToIncrease = buttonsConfiguration.get(buttonIdx);
        int[] stateDiff = calculateDiff(desiredJoltage, currentState.joltageState);
        //if there is no chance
        Set<Integer> possibleIndicators = new HashSet<>();
        for (int i = buttonIdx; i < buttonsConfiguration.size(); i++) {
            possibleIndicators.addAll(buttonsConfiguration.get(i));
        }

        for (int i = 0; i < stateDiff.length; i++) {
            if (stateDiff[i] > 0 && !possibleIndicators.contains(i)) {
                return null;
            }
        }

        int maxPossibleNUmberOfClicks = indicatorsToIncrease.stream().mapToInt(ind -> stateDiff[ind]).min().getAsInt();
        MachineState2 bestStateSoFar = null;
        for (int i = maxPossibleNUmberOfClicks; i >= 0; i--) {
            int[] newState = copyArray(currentState.joltageState);
            for (int ind : indicatorsToIncrease) {
                newState[ind] += i;
            }
            MachineState2 stateAfterClicks = new MachineState2(newState, currentState.pressedButtons + i);
            if (areArraysEqual(stateAfterClicks.joltageState, desiredJoltage)) {
                return stateAfterClicks;
            }
            if (buttonIdx >= buttonsConfiguration.size() - 1) {
                return null;
            }
            MachineState2 bestState = getBestMachineStateAttempt1(buttonIdx + 1, stateAfterClicks);
            if (bestState != null) {
                if (bestStateSoFar == null || bestState.pressedButtons < bestStateSoFar.pressedButtons) {
                    bestStateSoFar = bestState;
                    //TODO: just for testing
                    return bestStateSoFar;
                }
            }
        }
        return bestStateSoFar;
    }

    private int[] calculateDiff(int[] desiredJoltage, int[] joltageState) {
        int[] diff = new int[desiredJoltage.length];
        for (int i = 0; i < desiredJoltage.length; i++) {
            diff[i] = desiredJoltage[i] - joltageState[i];
        }
        return diff;
    }


    private int[] copyArray(int[] a) {
        int[] r = new int[a.length];
        System.arraycopy(a, 0, r, 0, a.length);
        return r;
    }

    private boolean exceeds(int[] joltageState, int[] desiredJoltage) {
        for (int i = 0; i < joltageState.length; i++) {
            if (joltageState[i] > desiredJoltage[i]) {
                return true;
            }
        }
        return false;
    }

    private boolean areArraysEqual(int[] a1, int[] a2) {
        for (int i = 0; i < a1.length; i++) {
            if (a1[i] != a2[i]) {
                return false;
            }
        }
        return true;
    }

    private boolean[] copyArray(boolean[] a) {
        boolean[] r = new boolean[a.length];
        System.arraycopy(a, 0, r, 0, a.length);
        return r;
    }

    private boolean areArraysEqual(boolean[] a1, boolean[] a2) {
        for (int i = 0; i < a1.length; i++) {
            if (a1[i] != a2[i]) {
                return false;
            }
        }
        return true;
    }

    record MachineState(boolean[] indState, int pressedButtons) {

    }

    record MachineState2(int[] joltageState, int pressedButtons) {

        @Override
        public String toString() {
            return Arrays.toString(joltageState) + " " + pressedButtons;
        }
    }
}
