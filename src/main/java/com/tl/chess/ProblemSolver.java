package com.tl.chess;

import com.tl.chess.common.Position;
import com.tl.chess.display.ConsoleDisplayManager;
import com.tl.chess.display.DisplayManager;
import com.tl.chess.engines.Engine;
import com.tl.chess.engines.StandardEngine;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.function.Predicate;

public class ProblemSolver {

    private final Position initialPosition;
    private final Comparator<Position> priorityQueueComparator;
    private final Predicate<Position> positionFilter;
    private final Predicate<Position> solutionPredicate;
    private static int processedPositions = 0;

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
        while (!positions.isEmpty()) {

            displayProgress(positions);

            Position position = positions.poll();

            if (solutionPredicate.test(position)) {
                printSolution(position);
            }

            if (positionFilter.test(position)) {
                positions.addAll(engine.calculateNextPositions(position));
            }
        }
    }

    private static void displayProgress(PriorityQueue<Position> positions) {
        processedPositions++;
        if (processedPositions % 25000 == 0) {
            System.out.println(processedPositions + " :" + positions.size());
        }
    }

    private static void printSolution(Position position) {
        System.out.println();
        System.out.println("Solution found:");
        position.getMoves().forEach(System.out::println);
        System.out.println();
        System.out.flush();
    }
}