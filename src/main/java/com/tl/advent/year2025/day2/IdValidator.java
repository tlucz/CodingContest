package com.tl.advent.year2025.day2;


import static com.tl.utils.NumbersHelper.isInteger;

import java.util.ArrayList;
import java.util.List;

class IdValidator {

    private final long minValue;
    private final long maxValue;
    private List<Long> wrongIds = new ArrayList<>();

    IdValidator(long minValue, long maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    void validate() {
        for (long i = minValue; i <= maxValue; i++) {
            if (isInvalid2(i)) {
                wrongIds.add(i);
            }
        }
    }

    private boolean isInvalid(long value) {
        String stringRepr = String.valueOf(value);
        int length = stringRepr.length();
        double possibleSequenceLength = (double) length / 2;
        if (isInteger(possibleSequenceLength)) {
            String sequence = stringRepr.substring(0, (int)possibleSequenceLength);
            String numberFromSequence = sequence.repeat(2);
            if (numberFromSequence.equals(stringRepr)) {
                System.out.println(value);
                return true;
            }
        }
        return false;
    }

    private boolean isInvalid2(long value) {
        String stringRepr = String.valueOf(value);
        int length = stringRepr.length();
        int maxPossibleSequence = length / 2;
        for (int possibleSequence = 1; possibleSequence <= maxPossibleSequence; possibleSequence++) {
            double sequenceOccurences = (double) length / possibleSequence;
            if (isInteger(sequenceOccurences)) {
                String sequence = stringRepr.substring(0, possibleSequence);
                String numberFromSequence = sequence.repeat((int) sequenceOccurences);
                if (numberFromSequence.equals(stringRepr)) {
                    System.out.println(value);
                    return true;
                }
            }
        }
        return false;
    }

    List<Long> getWrongIds() {
        return wrongIds;
    }
}
