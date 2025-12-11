package com.tl.advent.year2025.day11;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DevicesGraph {

    private Map<String, Set<String>> connections = new HashMap<>();

    private Map<String, PathsToOutData> pathsProvidesToOutCache = new HashMap<>();

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
        PathsToOutData allPathsToOut = getAllPathsProvidesToOut(from);

        System.out.println("All paths:" + allPathsToOut.total());
        return allPathsToOut.throughBoth;
    }

    static long counter = 0;

    private PathsToOutData getAllPathsProvidesToOut(String node) {
        if (pathsProvidesToOutCache.containsKey(node)) {
            return pathsProvidesToOutCache.get(node);
        } else {
            if (node.equals("out")) {
                PathsToOutData retVal = new PathsToOutData(1, 0, 0, 0);
                pathsProvidesToOutCache.put(node, retVal);
                return retVal;
            } else {
                if (connections.get(node) == null) {
                    PathsToOutData retVal = new PathsToOutData(0, 0, 0, 0);
                    pathsProvidesToOutCache.put(node, retVal);
                    return retVal;
                }
                PathsToOutData sum = new PathsToOutData(0, 0, 0, 0);
                for (String nextNode : connections.get(node)) {
                    PathsToOutData nextNodeData = getAllPathsProvidesToOut(nextNode);
                    sum = new PathsToOutData(
                            sum.total + nextNodeData.total,
                            sum.throughDAC + nextNodeData.throughDAC,
                            sum.throughFFT + nextNodeData.throughFFT,
                            sum.throughBoth + nextNodeData.throughBoth);
                }
                if (node.equals("dac")) {
                    sum = new PathsToOutData(sum.total, sum.total, sum.throughFFT, sum.throughFFT);
                } else if (node.equals("fft")) {
                    sum = new PathsToOutData(sum.total, sum.throughDAC, sum.total, sum.throughDAC);
                }
                this.pathsProvidesToOutCache.put(node, sum);
                counter++;
                System.out.println("Cache:" + node + " :" + counter);
                return sum;
            }
        }
    }

    record PathsToOutData(long total, long throughDAC, long throughFFT, long throughBoth) {

    }
}
