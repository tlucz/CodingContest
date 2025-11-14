package com.tl.codingcontest.contest.nov2025.level6;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlanExecutor {

    public static void main(String[] args) {
        List<List<Integer>> paces = new PlanExecutor().execute(List.of(
                new Point(0, 0),
                new Point(0, 3),
                new Point(6, 3),
                new Point(6, 0)
        ));
        System.out.println(paces.get(0).size());
        System.out.println(paces.get(1).size());
        System.out.println(paces);
        System.out.println(paces.get(0).stream().map(integer -> Integer.toString(integer)).collect(Collectors.joining(" ")));
        System.out.println(paces.get(1).stream().map(integer -> Integer.toString(integer)).collect(Collectors.joining(" ")));
    }

    public List<List<Integer>> execute(List<Point> points) {
        Spaceship spaceship = new Spaceship();
        List<Integer> pacesX = new ArrayList<>();
        List<Integer> pacesY = new ArrayList<>();
        for (int i = 0; i < points.size() - 1; i++) {
            Point startPoint = points.get(i);
            Point endPoint = points.get(i + 1);
            int relativeXEnd = endPoint.getX() - startPoint.getX();
            int relativeYEnd = endPoint.getY() - startPoint.getY();

            List<Integer> travelPacesX = spaceship.travelTo(relativeXEnd);
            pacesX.addAll(travelPacesX);
            List<Integer> travelPacesY = spaceship.travelTo(relativeYEnd);
            pacesY.addAll(travelPacesY);
            int xTime = time(travelPacesX);
            int yTime = time(travelPacesY);
            for (int x = xTime; x < yTime; x++) {
                pacesX.add(0);
            }
            for (int y = yTime; y < xTime; y++) {
                pacesY.add(0);
            }
        }

        return List.of(pacesX, pacesY);
    }

    public int time(List<Integer> paces){
        return paces.stream()
                .mapToInt(x -> x)
                .map(operand -> Math.abs(operand))
                .map(operand -> Math.max(operand, 1))
                .sum();
    }
}
