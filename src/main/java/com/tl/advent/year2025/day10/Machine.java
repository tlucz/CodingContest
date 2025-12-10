package com.tl.advent.year2025.day10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Machine {

    private final boolean[] indicatorDesiredState;
    private final List<List<Integer>> buttonsConfiguration;
    private final int[] desiredJoltage;

    public Machine(String line) {
        //[.###.#] (0,1,2,3,4) (0,3,4) (0,1,2,4,5) (1,2) {10,11,11,5,10,5}
        int closingSquareIdx = line.indexOf(']');
        int openingCurly = line.indexOf('{');
        String indicTxt = line.substring(1, closingSquareIdx);
        String switchTxt = line.substring(closingSquareIdx + 2, openingCurly - 1);
        String joltageTxt = line.substring(openingCurly + 1, line.length() - 1);
        indicatorDesiredState = new boolean[indicTxt.length()];
        for (int i = 0; i < indicTxt.length(); i++) {
            if (indicTxt.charAt(i) == '#') {
                indicatorDesiredState[i] = true;
            }
        }

        buttonsConfiguration = new ArrayList<>();
        for (String s : switchTxt.split(" ")) {
            List<Integer> indicatorsToSwitch = Arrays.stream(s.replace("(", "").replace(")", "").split(","))
                    .map(v -> Integer.parseInt(v)).toList();
            buttonsConfiguration.add(indicatorsToSwitch);
        }
        desiredJoltage = Arrays.stream(joltageTxt.split(",")).mapToInt(v -> Integer.parseInt(v)).toArray();
    }

    public int configure() {
        PriorityQueue<MachineState> queue = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.pressedButtons, o2.pressedButtons));
        queue.add(new MachineState(new boolean[indicatorDesiredState.length], 0));
        MachineState stateToAnalyze;
        while ((stateToAnalyze = queue.poll()) != null) {
            if (areArraysEqual(stateToAnalyze.indState, indicatorDesiredState)) {
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

    public int configurePart2() {
        PriorityQueue<MachineState2> queue = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.pressedButtons, o2.pressedButtons));
        queue.add(new MachineState2(new int[indicatorDesiredState.length], 0));
        MachineState2 stateToAnalyze;
        while ((stateToAnalyze = queue.poll()) != null) {
            if (areArraysEqual(stateToAnalyze.joltageState, desiredJoltage)) {
                return stateToAnalyze.pressedButtons;
            }
            if(exceeds(stateToAnalyze.joltageState,desiredJoltage)) {
                continue;
            }
            for (List<Integer> button : buttonsConfiguration) {
                int[] newJoltageState = copyArray(stateToAnalyze.joltageState);
                button.forEach(indIdx -> newJoltageState[indIdx] = newJoltageState[indIdx] + 1);
                queue.add(new MachineState2(newJoltageState, stateToAnalyze.pressedButtons + 1));
            }
        }
        throw new RuntimeException("not found");
    }

    private boolean exceeds(int[] joltageState, int[] desiredJoltage) {
        for (int i = 0; i <joltageState.length; i++) {
            if(joltageState[i]>desiredJoltage[i]) {
                return true;
            }
        }
        return false;
    }

    private int[] copyArray(int[] a) {
        int[] r = new int[a.length];
        System.arraycopy(a, 0, r, 0, a.length);
        return r;
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

    }
}
