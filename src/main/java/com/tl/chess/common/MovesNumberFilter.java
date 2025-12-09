package com.tl.chess.common;

import java.util.function.Predicate;

public class MovesNumberFilter implements Predicate<Position> {

    private final int allowedMoves;

    public MovesNumberFilter(int allowedMoves) {
        this.allowedMoves = allowedMoves;
    }

    @Override
    public boolean test(Position position) {
        if (position.getMoves().size() >= allowedMoves) {
            return false;
        }
        return true;
    }
}
