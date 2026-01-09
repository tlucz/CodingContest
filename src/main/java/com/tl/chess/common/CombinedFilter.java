package com.tl.chess.common;

import java.util.List;
import java.util.function.Predicate;

public class CombinedFilter implements Predicate<Position> {

    private final List<Predicate<Position>> filters;

    public CombinedFilter(List<Predicate<Position>> filters) {
        this.filters = filters;
    }

    @Override
    public boolean test(Position position) {
        return filters.stream().allMatch(f->f.test(position));
    }
}
