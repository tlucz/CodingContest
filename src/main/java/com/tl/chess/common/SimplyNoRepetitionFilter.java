package com.tl.chess.common;

import java.util.function.Predicate;

public class SimplyNoRepetitionFilter implements Predicate<Position> {

    @Override
    public boolean test(Position position) {
        int movesCount = position.getMoves().size();
        if (movesCount >= 5) {
            boolean isRepetition = position.getMoves().get(movesCount - 1).equals(position.getMoves().get(movesCount - 5));
            return !isRepetition;
        }
        return true;
    }
}
