package com.tl.codingcontest.contest.nov2025.level3;

import java.util.Map;

public class Pace {

    private static final int MIN_PACE = 5;
    private static final int MAX_PACE = 1;

    private static final Map<Integer, Pace> PACES = Map.of(
            0, new Pace(0),
            1, new Pace(1),
            2, new Pace(2),
            3, new Pace(3),
            4, new Pace(4),
            5, new Pace(5)
    );

    private final int pace;

    public Pace(int pace) {
        this.pace = pace;
    }

    public int distanceToStop() {
        return MIN_PACE - pace;
    }

    public Pace accelerate() {
        if (pace == 0) {
            return PACES.get(MIN_PACE);
        }
        if (pace == MAX_PACE) {
            return this;
        } else {
            return PACES.get(pace - 1);
        }
    }

    public Pace brake() {
        if (pace == 0) {
            return this;
        }
        if (pace == MIN_PACE) {
            return PACES.get(0);
        } else {
            return PACES.get(pace + 1);
        }
    }

    public int getPace() {
        return pace;
    }
}
