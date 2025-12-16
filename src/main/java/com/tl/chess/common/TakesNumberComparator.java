package com.tl.chess.common;

import java.util.Comparator;

public class TakesNumberComparator implements Comparator<Position> {

    @Override
    public int compare(Position o1, Position o2) {
        return Long.compare(
                o2.getMoves().stream().filter(m->m.contains("x")).count(),
                o1.getMoves().stream().filter(m->m.contains("x")).count());
    }
}
