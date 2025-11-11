package com.tl.codingcontest.training.magiccurrency.level2;

public class MagicCurrencyLevel2 {

    private final int x;
    private final int y;

    public MagicCurrencyLevel2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int calculatePossibleDesks() {
        return x / 3 * y;
    }
}
