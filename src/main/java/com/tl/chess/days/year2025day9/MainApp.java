package com.tl.chess.days.year2025day9;

import com.tl.chess.ProblemSolver;
import com.tl.chess.boards.StandardChessboard;
import com.tl.chess.common.MovesNumberComparator;
import com.tl.chess.common.MovesNumberFilter;
import com.tl.chess.common.Position;
import com.tl.chess.common.PositionConverter;
import com.tl.chess.engines.Engine;
import com.tl.chess.engines.StandardEngine;
import com.tl.chess.pieces.RealPiece;
import java.util.List;

public class MainApp {

    public static void main(String[] args) {
        Engine engine = new StandardEngine();

        String positionTxt = """
                ...R...B
                ...p....
                ....kP..
                ........
                ........
                ........
                ........
                .B.....K        
                """;

        positionTxt = """
                ...R...B
                ...p....
                ....k...
                ....Pp..
                ........
                ........
                ........
                .B.....K        
                """;
        List<RealPiece> realPieces = PositionConverter.getPiecesFromTxt(positionTxt);
        ProblemSolver solver = new ProblemSolver(
                new Position(new StandardChessboard(), realPieces, true, List.of()),
                new MovesNumberComparator(),
                new MovesNumberFilter(3),
                position -> !position.isWhiteTurn() && engine.isCheckmate(position),
                false);
        solver.solve(false);
    }
}
