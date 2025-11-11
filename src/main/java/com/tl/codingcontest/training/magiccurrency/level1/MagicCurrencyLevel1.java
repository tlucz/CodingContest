package com.tl.codingcontest.training.magiccurrency.level1;

public class MagicCurrencyLevel1 {

    private final int x;
    private final int y;

    public MagicCurrencyLevel1(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int calculatePossibleDesks() {
        return x / 3 * y;
    }
}
