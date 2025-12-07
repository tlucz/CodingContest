package com.tl.chess.tests;

import com.tl.chess.boards.StandardChessboard;
import com.tl.chess.common.MovesNumberComparator;
import com.tl.chess.ProblemSolver;
import com.tl.chess.common.Position;
import com.tl.chess.common.PositionConverter;
import com.tl.chess.engines.Engine;
import com.tl.chess.engines.StandardEngine;
import com.tl.chess.pieces.RealPiece;
import java.util.List;

public class StalemateTestApp {

    public static void main(String[] args) {
        Engine engine = new StandardEngine();

        String positionTxt = """
                       k
                        
                       P
                       K
                        
                        
                        
                        
                """;

        List<RealPiece> realPieces = PositionConverter.getPiecesFromTxt(positionTxt);
        ProblemSolver solver = new ProblemSolver(
                new Position(new StandardChessboard(), realPieces, false, List.of()),
                new MovesNumberComparator(),
                position-> position.getMoves().size()<=6,
                position -> engine.isStalemate(position),
                false);
        solver.solve(true);
    }
}
