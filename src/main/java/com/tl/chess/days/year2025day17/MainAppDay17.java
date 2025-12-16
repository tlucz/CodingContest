package com.tl.chess.days.year2025day17;

import com.tl.chess.ProblemSolver;
import com.tl.chess.boards.StandardChessboard;
import com.tl.chess.common.MovesNumberComparator;
import com.tl.chess.common.MovesNumberFilter;
import com.tl.chess.common.Position;
import com.tl.chess.common.PositionConverter;
import com.tl.chess.common.TakesNumberComparator;
import com.tl.chess.engines.Engine;
import com.tl.chess.engines.StandardEngine;
import com.tl.chess.pieces.RealPiece;
import java.util.List;

public class MainAppDay17 {

    public static void main(String[] args) {
        Engine engine = new StandardEngine();

        var positionTxt = """
                B.......
                .RN.R..Q
                .B..b.r.
                ....br..
                ....k...
                .....p..
                ..K..P..
                ........        
                """;
        List<RealPiece> realPieces = PositionConverter.getPiecesFromTxt(positionTxt);
        ProblemSolver solver = new ProblemSolver(
                new Position(new StandardChessboard(), realPieces, false, List.of()),
                new TakesNumberComparator(),
                new MovesNumberFilter(4),
                position -> !position.isWhiteTurn() && engine.isStalemate(position),
                false);
        solver.solve(false);
    }
}
