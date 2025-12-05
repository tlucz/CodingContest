package com.tl.advent.year2025.day5;

import java.util.ArrayList;
import java.util.List;

public class IdValidator {

    private List<Range> freshRanges = new ArrayList<>();

    public IdValidator(List<String> freshIdsRanges) {
        for (String r : freshIdsRanges) {
            String[] split = r.split("-");
            freshRanges.add(new Range(Long.parseLong(split[0]), Long.parseLong(split[1])));
        }
    }

    public void combineRanges() {
        List<Range> sortedList = freshRanges.stream().sorted().toList();
        List<Range> combinedList = new ArrayList<>();
        Range previousRange = null;
        for (Range range : sortedList) {
            if (previousRange == null) {
                previousRange = new Range(range.from(), range.to());
                continue;
            }
            if (range.from() <= previousRange.to()) {
                previousRange = new Range(previousRange.from(), Math.max(previousRange.to(), range.to()));
            } else {
                combinedList.add(previousRange);
                previousRange = new Range(range.from(), range.to());
            }
        }
        combinedList.add(previousRange);
        freshRanges = combinedList;

    }


    public long getFreshIdsAmount() {
        return freshRanges.stream().mapToLong(r -> r.to() - r.from + 1).sum();
    }

    public boolean isFresh(String id) {
        long l = Long.parseLong(id);
        for (Range r : freshRanges) {
            if (r.isIn(l)) {
                System.out.println(r);
                return true;
            }
        }
        return false;
    }

    record Range(long from, long to) implements Comparable<Range> {

        boolean isIn(long value) {
            return value >= from && value <= to;
        }

        @Override
        public int compareTo(Range o) {
            if (from == o.from) {
                return Long.compare(to, o.to);
            }
            return Long.compare(from, o.from);
        }
    }
}
