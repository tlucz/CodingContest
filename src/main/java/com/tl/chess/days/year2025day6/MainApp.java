package com.tl.chess.days.year2025day6;

import com.tl.chess.common.MovesNumberComparator;
import com.tl.chess.common.PositionRepository;
import com.tl.chess.ProblemSolver;
import com.tl.chess.engines.Engine;
import com.tl.chess.engines.StandardEngine;

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
