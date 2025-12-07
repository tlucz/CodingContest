package com.tl.chess.days.year2025day7;

import com.tl.chess.MovesNumberComparator;
import com.tl.chess.Position;
import com.tl.chess.PositionFilter;
import com.tl.chess.display.ConsoleDisplayManager;
import com.tl.chess.display.DisplayManager;
import com.tl.chess.engines.Engine;
import com.tl.chess.engines.StandardEngine;
import java.util.List;
import java.util.PriorityQueue;

public class MainApp {

    public static void main(String[] args) {

        Position initialPosition = PositionRepository.POSITION;
        DisplayManager displayManager = new ConsoleDisplayManager();
        displayManager.printPosition(initialPosition);
        System.out.println("---------------");

        PriorityQueue<Position> positions = new PriorityQueue<>(new MovesNumberComparator());
        positions.add(initialPosition);
        Engine engine = new StandardEngine();
        PositionFilter positionFilter = new Day7PositionFilter();
        long c = 0;
        while (!positions.isEmpty()) {
            c++;
            if (c % 25000 == 0) {
                System.out.println(c + " :" + positions.size());
            }
            Position position = positions.poll();
            if (!position.isWhiteTurn() && engine.isCheckmate(position)) {
//                System.out.println();
//                displayManager.printPosition(position);
                System.out.println();
                position.getMoves().forEach(System.out::println);
                System.out.println("Checkmate! " + position.isWhiteTurn());
                System.out.println();
                System.out.flush();
            }

            List<Position> nextPositions = engine.calculateNextPositions(position);

            for (Position nextPosition : nextPositions) {
                if (positionFilter.isPositionOk(nextPosition)) {
                    positions.add(nextPosition);
                }
            }
        }
    }
}
