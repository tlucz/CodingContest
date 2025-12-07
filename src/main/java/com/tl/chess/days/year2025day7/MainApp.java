package com.tl.chess.days.year2025day7;

import com.tl.chess.common.MovesNumberComparator;
import com.tl.chess.ProblemSolver;
import com.tl.chess.engines.Engine;
import com.tl.chess.engines.StandardEngine;

public class MainApp {

    public static void main(String[] args) {
        Engine engine = new StandardEngine();

        ProblemSolver solver = new ProblemSolver(
                PositionRepository.POSITION,
                new MovesNumberComparator(),
                new Day7PositionFilter(),
                position -> !position.isWhiteTurn() && engine.isCheckmate(position),
                false);
        solver.solve();
    }
}
