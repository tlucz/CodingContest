package com.tl.chess.days.year2025day7;

import static com.tl.chess.pieces.PieceDefinitionRepository.BISHOP_DEFINITION;
import static com.tl.chess.pieces.PieceDefinitionRepository.KING_DEFINITION;
import static com.tl.chess.pieces.PieceDefinitionRepository.PAWN_DEFINITION;
import static com.tl.chess.pieces.PieceDefinitionRepository.QUEEN_DEFINITION;
import static com.tl.chess.pieces.PieceDefinitionRepository.ROOK_DEFINITION;

import com.tl.chess.boards.StandardChessboard;
import com.tl.chess.common.Field;
import com.tl.chess.common.MovesNumberComparator;
import com.tl.chess.ProblemSolver;
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
                r   k  r
                       p
                p     P\s
                  p B pp
                   q b \s
                   Bp P\s
                 PP    \s
                R   K  R        
                """;

        List<RealPiece> realPieces = PositionConverter.getPiecesFromTxt(positionTxt);
        ProblemSolver solver = new ProblemSolver(
                new Position(new StandardChessboard(), realPieces, false, List.of()),
                new MovesNumberComparator(),
                new Day7PositionFilter(),
                position -> !position.isWhiteTurn() && engine.isCheckmate(position),
                false);
        solver.solve();
    }
}
