package com.tl.chess.days.year2025day7;

import com.tl.chess.common.Position;
import java.util.function.Predicate;

public class Day7PositionFilter implements Predicate<Position> {

    @Override
    public boolean test(Position position) {
        if (position.getMoves().size() >= 4) {
            return false;
        }
        return true;
    }
}
