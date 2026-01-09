package com.tl.chess.days.year2025day19;

import com.tl.chess.ProblemSolver;
import com.tl.chess.boards.StandardChessboard;
import com.tl.chess.common.CombinedFilter;
import com.tl.chess.common.MovesNumberComparator;
import com.tl.chess.common.MovesNumberFilter;
import com.tl.chess.common.Position;
import com.tl.chess.common.PositionConverter;
import com.tl.chess.common.SimplyNoRepetitionFilter;
import com.tl.chess.engines.Engine;
import com.tl.chess.engines.StandardEngine;
import com.tl.chess.pieces.RealPiece;
import java.util.List;

public class MainAppDay19 {

    public static void main(String[] args) {
        Engine engine = new StandardEngine();

        var positionTxt = """
                ....N...
                KPpk.P..
                .......R
                .bpp..B.
                ..p.BP.n
                ........
                .p.....p
                .......Q        
                """;
        List<RealPiece> realPieces = PositionConverter.getPiecesFromTxt(positionTxt);
        ProblemSolver solver = new ProblemSolver(
                new Position(new StandardChessboard(), realPieces, false, List.of()),
                new MovesNumberComparator(),
                new MovesNumberFilter(4),
                position -> !position.isWhiteTurn() && engine.isCheckmate(position),
                false);
        solver.solve(false);
    }
}
