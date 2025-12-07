package com.tl.chess.days.year2025day6;

import com.tl.chess.MovesNumberComparator;
import com.tl.chess.Position;
import com.tl.chess.PositionRepository;
import com.tl.chess.ProblemSolver;
import com.tl.chess.display.ConsoleDisplayManager;
import com.tl.chess.display.DisplayManager;
import com.tl.chess.engines.Engine;
import com.tl.chess.engines.StandardEngine;
import java.util.List;
import java.util.PriorityQueue;
import java.util.function.Predicate;

public class MainApp {

    public static void main(String[] args) {

        Engine engine = new StandardEngine();
        ProblemSolver solver = new ProblemSolver(
                PositionRepository.START_POSITION,
                new MovesNumberComparator(),
                new Day6PositionFilter(),
                position -> position.isWhiteTurn() && engine.isCheckmate(position)
        );
        solver.solve();
    }
}
