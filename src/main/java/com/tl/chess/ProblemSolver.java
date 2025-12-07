package com.tl.chess;

import com.tl.chess.display.ConsoleDisplayManager;
import com.tl.chess.display.DisplayManager;
import com.tl.chess.engines.Engine;
import com.tl.chess.engines.StandardEngine;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.function.Predicate;

public class ProblemSolver {

    private final Position initialPosition;
    private final Comparator<Position> priorityQueueComparator;
    private final Predicate<Position> positionFilter;
    private final Predicate<Position> solutionPredicate;

    public ProblemSolver(
            Position initialPosition,
            Comparator<Position> priorityQueueComparator,
            Predicate<Position> positionFilter, Predicate<Position> solutionPredicate) {
        this.initialPosition = initialPosition;
        this.priorityQueueComparator = priorityQueueComparator;
        this.positionFilter = positionFilter;
        this.solutionPredicate = solutionPredicate;
    }

    public void solve() {
        DisplayManager displayManager = new ConsoleDisplayManager();
        displayManager.printPosition(initialPosition);
        System.out.println("---------------");
        System.out.println();

        PriorityQueue<Position> positions = new PriorityQueue<>(priorityQueueComparator);
        positions.add(initialPosition);
        Engine engine = new StandardEngine();
        long c = 0;
        while (!positions.isEmpty()) {
            c++;
            if (c % 25000 == 0) {
                System.out.println(c + " :" + positions.size());
            }
            Position position = positions.poll();
            if (solutionPredicate.test(position)) {
                printSolution(position);
            }

            List<Position> nextPositions = engine.calculateNextPositions(position);

            for (Position nextPosition : nextPositions) {
                if (positionFilter.test(nextPosition)) {
                    positions.add(nextPosition);
                }
            }
        }
    }

    private static void printSolution(Position position) {
        System.out.println();
        position.getMoves().forEach(System.out::println);
        System.out.println("Solution found!");
        System.out.println();
        System.out.flush();
    }
}