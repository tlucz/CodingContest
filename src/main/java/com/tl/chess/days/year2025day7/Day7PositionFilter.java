package com.tl.chess.days.year2025day7;

import com.tl.chess.Position;
import com.tl.chess.PositionFilter;

public class Day7PositionFilter implements PositionFilter {

    @Override
    public boolean isPositionOk(Position position) {
        if(position.getMoves().size() > 4) {
            return false;
        }
        return true;
    }
}
