package com.tl.advent.year2025.day6;

import java.util.ArrayList;
import java.util.List;

public class Calculator {

    private final String[] operations;
    private final List<String> originalLines;
    List<String[]> numbersLines = new ArrayList<>();

    public Calculator(List<String> lines) {
        originalLines = lines;
        operations = lines.getLast().strip().split(" +");
        for (int i = 0; i < lines.size() - 1; i++) {
            String[] numbers = lines.get(i).strip().split(" +");
            numbersLines.add(numbers);
        }
    }

    public long calculatePart1() {
        long numberOfColumns = operations.length;
        long total = 0;
        for (int columnIdx = 0; columnIdx < numberOfColumns; columnIdx++) {
            long columnValue = Long.parseLong(numbersLines.get(0)[columnIdx]);
            for (int lineIdx = 1; lineIdx < numbersLines.size(); lineIdx++) {
                long value = Long.parseLong(numbersLines.get(lineIdx)[columnIdx]);
                switch (operations[columnIdx]) {
                    case "+" -> columnValue += value;
                    case "*" -> columnValue *= value;
                }
            }
            total += columnValue;
        }
        return total;
    }

    public long calculatePart2() {
        long total = 0;
//        123 328  51 64
//         45 64  387 23
//          6 98  215 314
//        *   +   *   +
        List<Character> operators = new ArrayList<>();
        List<Integer> operatorsIndexes = new ArrayList<>();
        String operatorsLine = originalLines.getLast();
        for (int idx = 0; idx < operatorsLine.length(); idx++) {
            if (operatorsLine.charAt(idx) != ' ') {
                operators.add(operatorsLine.charAt(idx));
                operatorsIndexes.add(idx);
            }
        }
        for (int columnNumberIdx = 0; columnNumberIdx < operators.size(); columnNumberIdx++) {
            List<Long> numbersToOperate = new ArrayList<>();
            for (int charIdx =
                    (columnNumberIdx == operators.size() - 1 ?
                            originalLines.getFirst().length() - 1 :
                            operatorsIndexes.get(columnNumberIdx + 1) - 2);
                    charIdx >= operatorsIndexes.get(columnNumberIdx); charIdx--) {
                String number = "";
                for (int rowIdx = 0; rowIdx < numbersLines.size(); rowIdx++) {
                    char ch = originalLines.get(rowIdx).charAt(charIdx);
                    if (ch != ' ') {
                        number += ch;
                    }
                }
                numbersToOperate.add(Long.parseLong(number));
            }
            long operationValue;
            if (operators.get(columnNumberIdx) == '*') {
                operationValue = 1;
                for (long number : numbersToOperate) {
                    operationValue *= number;
                }
            } else {
                operationValue = 0;
                for (long number : numbersToOperate) {
                    operationValue += number;
                }
            }
            total += operationValue;
        }
        return total;
    }
}
