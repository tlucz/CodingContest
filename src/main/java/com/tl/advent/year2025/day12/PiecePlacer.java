package com.tl.advent.year2025.day12;

import java.util.List;

public class PiecePlacer {

    private final int sizeX;
    private final int sizeY;

    public PiecePlacer(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    public boolean canBePlaced(List<Integer> piecesNumber) {
        int piecesAmount = piecesNumber.stream().mapToInt(Integer::intValue).sum();
        int maxX = sizeX / 3;
        int maxY = sizeY / 3;
        return maxX*maxY>=piecesAmount;
    }
}
