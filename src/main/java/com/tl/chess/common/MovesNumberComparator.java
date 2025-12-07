package com.tl.chess.common;

import java.util.Comparator;

public class MovesNumberComparator implements Comparator<Position> {

    @Override
    public int compare(Position o1, Position o2) {
        return Integer.compare(o2.getMoves().size(),o1.getMoves().size());
    }
}
