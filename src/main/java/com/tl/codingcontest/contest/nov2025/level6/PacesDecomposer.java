package com.tl.codingcontest.contest.nov2025.level6;

import java.util.*;
import java.util.stream.Stream;

public class PacesDecomposer {

    public static void main(String[] args) {
        List<List<Integer>> paces = new PlanExecutor().execute(List.of(
                new Point(0, 0),
                new Point(0, 3),
                new Point(6, 3),
                new Point(6, 0)
        ));
        PacesDecomposer decomposer = new PacesDecomposer();
        List<Point> points = decomposer.decompose(paces);
        System.out.println(points);
    }

    public List<Point> decompose(List<List<Integer>> paces) {
        validate(paces);
        List<Integer> pacesX = paces.get(0);
        List<Integer> pacesY = paces.get(1);

        List<Integer> xMoves = pacesX.stream()
                .flatMap(this::decomposePace)
                .toList();

        List<Integer> yMoves = pacesY.stream()
                .flatMap(this::decomposePace)
                .toList();

        int currentX = 0;
        int currentY = 0;
        List<Point> points = new ArrayList<>();
        Set<String> usedPoints = new HashSet<>();
        int commonMax = Math.min(xMoves.size(), yMoves.size());
        for (int i = 0; i < commonMax; i++) {
            currentX += xMoves.get(i);
            currentY += yMoves.get(i);
            addPointIfNew(points, currentX, currentY, usedPoints);
        }

        for (int x = commonMax; x < xMoves.size(); x++) {
            currentX += xMoves.get(x);
            addPointIfNew(points, currentX, currentY, usedPoints);
        }

        for (int y = commonMax; y < yMoves.size(); y++) {
            currentY += yMoves.get(y);
            addPointIfNew(points, currentX, currentY, usedPoints);
        }

        return points;
    }

    private static void addPointIfNew(List<Point> points, int currentX, int currentY, Set<String> usedPoints) {
        Point point = new Point(currentX, currentY);
        String pointString = point.toString();
        if (!usedPoints.contains(pointString)) {
            usedPoints.add(pointString);
            points.add(point);
        }
    }

    private Stream<Integer> decomposePace(int pace) {
        if (pace == 0) {
            return Stream.of(0);
        }
        List<Integer> moves = new ArrayList<>();
        int absPace = Math.abs(pace);
        for (int i = 0; i < absPace - 1; i++) {
            moves.add(0);
        }
        if (pace > 0) {
            moves.add(1);
        } else {
            moves.add(-1);
        }
        return moves.stream();
    }

    private void validate(List<List<Integer>> paces) {
        if (paces.size() != 2) {
            throw new IllegalArgumentException("wrong size of paces");
        }
    }
}
