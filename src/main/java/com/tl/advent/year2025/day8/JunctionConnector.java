package com.tl.advent.year2025.day8;

import com.tl.utils.map3d.Point3D;
import com.tl.utils.map3d.Point3DUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class JunctionConnector {

    private final Map<String, Point3D> junctionsMap = new HashMap<>();
    private final Map<String, Double> distancesMap = new HashMap<>();
    private final List<Entry<String, Double>> sortedEntries;
    private List<Set<String>> circuits = new ArrayList<>();

    public JunctionConnector(List<String> lines) {
        for (String line : lines) {
            if (junctionsMap.containsKey(line)) {
                throw new RuntimeException("Duplicate junction");
            }
            String[] parts = line.split(",");
            junctionsMap.put(line, new Point3D(Long.parseLong(parts[0]), Long.parseLong(parts[1]), Long.parseLong(parts[2])));
        }

        List<String> junctionIds = junctionsMap.keySet().stream().toList();
        for (int j1idx = 0; j1idx < junctionIds.size() - 1; j1idx++) {
            for (int j2idx = j1idx + 1; j2idx < junctionIds.size(); j2idx++) {
                String junction1Id = junctionIds.get(j1idx);
                String junction2Id = junctionIds.get(j2idx);
                distancesMap.put(junction1Id + "-" + junction2Id,
                        Point3DUtils.distance(junctionsMap.get(junction1Id), junctionsMap.get(junction2Id)));
            }
        }
        sortedEntries = distancesMap.entrySet().stream().sorted(Entry.comparingByValue()).toList();
    }

    public long calculatePart1(int connectionsNumber) {
        for (int i = 0; i < connectionsNumber; i++) {
            Entry<String, Double> shortestConnection = sortedEntries.get(i);
            String[] split = shortestConnection.getKey().split("-");
            String junction1Id = split[0];
            String junction2Id = split[1];

            Integer circuitNumber1 = findJunctionInCircuits(junction1Id);
            Integer circuitNumber2 = findJunctionInCircuits(junction2Id);
            if (circuitNumber1 == null && circuitNumber2 == null) {
                HashSet<String> newSet = new HashSet<>();
                newSet.add(junction1Id);
                newSet.add(junction2Id);
                circuits.add(newSet);
            } else if (circuitNumber1 == null) {
                circuits.get(circuitNumber2).add(junction1Id);
            } else if (circuitNumber2 == null) {
                circuits.get(circuitNumber1).add(junction2Id);
            } else if (!circuitNumber1.equals(circuitNumber2)) {
                circuits.get(circuitNumber1).addAll(circuits.get(circuitNumber2));
                circuits.remove((int) circuitNumber2);
            }
        }
        List<Integer> sorted = circuits.stream().map(Set::size).sorted(Collections.reverseOrder()).toList();
        return sorted.get(0) * sorted.get(1) * sorted.get(2);
    }

    private Integer findJunctionInCircuits(String junctionId) {
        for (int i = 0; i < circuits.size(); i++) {
            if (circuits.get(i).contains(junctionId)) {
                return i;
            }
        }
        return null;
    }

    public long calculatePart2() {
        int i = 0;
        while (true) {
            Entry<String, Double> shortestConnection = sortedEntries.get(i);
            String[] split = shortestConnection.getKey().split("-");
            String junction1Id = split[0];
            String junction2Id = split[1];

            Integer circuitNumber1 = findJunctionInCircuits(junction1Id);
            Integer circuitNumber2 = findJunctionInCircuits(junction2Id);
            if (circuitNumber1 == null && circuitNumber2 == null) {
                HashSet<String> newSet = new HashSet<>();
                newSet.add(junction1Id);
                newSet.add(junction2Id);
                circuits.add(newSet);
            } else if (circuitNumber1 == null) {
                circuits.get(circuitNumber2).add(junction1Id);
            } else if (circuitNumber2 == null) {
                circuits.get(circuitNumber1).add(junction2Id);
            } else if (!circuitNumber1.equals(circuitNumber2)) {
                circuits.get(circuitNumber1).addAll(circuits.get(circuitNumber2));
                circuits.remove((int) circuitNumber2);
            }
            if (circuits.size() == 1 && circuits.get(0).size() == junctionsMap.size()) {
                return junctionsMap.get(junction1Id).x() * junctionsMap.get(junction2Id).x();
            }
            i++;
        }
    }
}
