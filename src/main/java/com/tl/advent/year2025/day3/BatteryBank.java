package com.tl.advent.year2025.day3;

import java.util.Arrays;

public class BatteryBank {


    private final int[] batteriesArray;
    private final int bankSize;

    public BatteryBank(String line) {
        batteriesArray = Arrays.stream(line.split("")).mapToInt(Integer::parseInt).toArray();
        bankSize = batteriesArray.length;
    }

    public long calculateMaxVoltage(int numberOfBatteriesToSwitchOn) {
        int indexToStartSearch = 0;
        String result = "";
        for (int batteryToSwitch = 1; batteryToSwitch <= numberOfBatteriesToSwitchOn; batteryToSwitch++) {
            int indexToEndSearch = bankSize - numberOfBatteriesToSwitchOn + batteryToSwitch - 1;
            int foundIndex = findIndexWithBiggestValue(indexToStartSearch, indexToEndSearch);
            result += String.valueOf(batteriesArray[foundIndex]);
            indexToStartSearch = foundIndex + 1;
        }
        return Long.parseLong(result);
    }

    private int findIndexWithBiggestValue(int indexToStartSearch, int indexToEndSearch) {
        int bestValue = -1;
        int bestIndex = -1;
        for (int i = indexToStartSearch; i <= indexToEndSearch; i++) {
            if (batteriesArray[i] > bestValue) {
                bestValue = batteriesArray[i];
                bestIndex = i;
            }
        }
        return bestIndex;
    }
}
