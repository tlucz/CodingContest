package com.tl.advent.year2025.day7;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BeamSimulator {

    private final List<String> lines;

    public BeamSimulator(List<String> lines) {
        this.lines = lines;
    }

    public String calculatePart1() {
        int startColumn = findStartColumn(lines.get(0));
        Set<Integer> beams = new HashSet<>();
        beams.add(startColumn);
        int numberOfSplit = 0;
        for (int row = 1; row < lines.size(); row++) {
            Set<Integer> newBeams = new HashSet<>();
            for (int beamColumn : beams) {
                if (lines.get(row).charAt(beamColumn) == '^') {
                    newBeams.add(beamColumn - 1);
                    newBeams.add(beamColumn + 1);
                    numberOfSplit++;
                } else {
                    newBeams.add(beamColumn);
                }
            }
            beams = newBeams;
        }
        return numberOfSplit + "";
    }

    private int findStartColumn(String line) {
        return line.indexOf("S");
    }

    public String calculatePart2() {
        int startColumn = findStartColumn(lines.get(0));
        Map<Integer, Long> beamsTimelines = new HashMap<>();
        beamsTimelines.put(startColumn, 1L);
        for (int row = 1; row < lines.size(); row++) {
            Map<Integer, Long> newtimelines = new HashMap<>();
            for (var beamTimeline : beamsTimelines.entrySet()) {
                if (lines.get(row).charAt(beamTimeline.getKey()) == '^') {
                    addTimelines(newtimelines, beamTimeline.getKey() - 1, beamTimeline.getValue());
                    addTimelines(newtimelines, beamTimeline.getKey() + 1, beamTimeline.getValue());
                } else {
                    addTimelines(newtimelines, beamTimeline.getKey(), beamTimeline.getValue());
                }
            }
            beamsTimelines = newtimelines;
        }
        return beamsTimelines.values().stream().mapToLong(Long::longValue).sum() + "";
    }

    private void addTimelines(Map<Integer, Long> timelines, int column, long value) {
        if(timelines.containsKey(column)) {
            timelines.put(column, timelines.get(column) + value);
        } else {
            timelines.put(column, value);
        }
    }
}
