package com.tl.advent.year2025.day11;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DevicesGraph {

    private Map<String, Set<String>> connections = new HashMap<>();

//    private Map<Connection,List<List<String>>> possibleConnectionsCache = new HashMap<>();

    public DevicesGraph(List<String> lines) {
        for (String line : lines) {
//aaa: you hhh
            String[] parts = line.split(":");
            String from = parts[0].trim();
            String[] tos = parts[1].trim().split(" ");
            connections.put(from, Set.of(tos));
        }
    }

    public long connectionsAmount(String from, String to) {
        List<List<String>> currentPaths = new ArrayList<>();
        List<String> currentPath = List.of(from);
        currentPaths.add(currentPath);
        List<List<String>> paths = allPathsTo(to, currentPath);
        System.out.println("Paths size:" + paths.size());

        return paths.stream().filter(p -> (p.contains("dac") && p.contains("fft"))).count();
    }

    private List<List<String>> allPathsTo(String to, List<String> currentPath) {
        //TODO: implement cache
        String last = currentPath.getLast();
        if (last.equals(to)) {
            return List.of(currentPath);
        } else {
            Set<String> connections = this.connections.get(last);
            if (connections == null) {
                return List.of();
            }
            List<List<String>> allPaths = new ArrayList<>();
            for (String nextNode : connections) {
                if (!currentPath.contains(nextNode)) {
                    List<String> newPath = new ArrayList<>(currentPath);
                    newPath.add(nextNode);
                    allPaths.addAll(allPathsTo(to, newPath));
                }
            }
            return allPaths;
        }
    }
}

record Connection(String from, String to) {

}
