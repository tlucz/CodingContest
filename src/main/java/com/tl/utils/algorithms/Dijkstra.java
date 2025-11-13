package com.tl.utils.algorithms;
import java.util.*;

public class Dijkstra {

    static record Edge(String target, int weight) {

    }

    static class Result {
        Map<String, Integer> distances;
        Map<String, List<String>> paths;

        Result(Map<String, Integer> distances, Map<String, List<String>> paths) {
            this.distances = distances;
            this.paths = paths;
        }
    }

    public static Result dijkstra(Map<String, List<Edge>> graph, String start) {
        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> previous = new HashMap<>();
        Map<String, List<String>> paths = new HashMap<>();

        // Initialize
        for (String node : graph.keySet()) {
            distances.put(node, Integer.MAX_VALUE);
            previous.put(node, null);
        }
        distances.put(start, 0);

        PriorityQueue<Map.Entry<String, Integer>> pq =
                new PriorityQueue<>(Map.Entry.comparingByValue());
        pq.add(Map.entry(start, 0));

        // Dijkstra core
        while (!pq.isEmpty()) {
            Map.Entry<String, Integer> current = pq.poll();
            String currentNode = current.getKey();
            int currentDist = current.getValue();

            if (currentDist > distances.get(currentNode)) continue;

            for (Edge edge : graph.getOrDefault(currentNode, List.of())) {
                int newDist = currentDist + edge.weight;
                if (newDist < distances.get(edge.target)) {
                    distances.put(edge.target, newDist);
                    previous.put(edge.target, currentNode);
                    pq.add(Map.entry(edge.target, newDist));
                }
            }
        }

        // Reconstruct paths for all nodes
        for (String node : graph.keySet()) {
            paths.put(node, reconstructPath(start, node, previous));
        }

        return new Result(distances, paths);
    }

    private static List<String> reconstructPath(String start, String end, Map<String, String> previous) {
        List<String> path = new ArrayList<>();
        for (String at = end; at != null; at = previous.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        if (!path.isEmpty() && !path.get(0).equals(start)) {
            return List.of(); // unreachable
        }
        return path;
    }

    public static void main(String[] args) {
        Map<String, List<Edge>> graph = Map.of(
                "A", List.of(new Edge("B", 4), new Edge("C", 2)),
                "B", List.of(new Edge("C", 5), new Edge("D", 10)),
                "C", List.of(new Edge("E", 3)),
                "D", List.of(),
                "E", List.of(new Edge("D", 4))
        );

        Result result = dijkstra(graph, "A");

        System.out.println("Shortest distances from A:");
        result.distances.forEach((node, dist) ->
                System.out.println("A -> " + node + " = " +
                        (dist == Integer.MAX_VALUE ? "INF" : dist))
        );

        System.out.println("\nShortest paths from A:");
        result.paths.forEach((node, path) ->
                System.out.println("A -> " + node + " = " + path)
        );
    }
}