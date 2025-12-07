package com.tl.chess.days.year2025day7;

import static com.tl.chess.pieces.PieceDefnitionRepository.BISHOP_DEFINITION;
import static com.tl.chess.pieces.PieceDefnitionRepository.KING_DEFINITION;
import static com.tl.chess.pieces.PieceDefnitionRepository.KNIGHT_DEFINITION;
import static com.tl.chess.pieces.PieceDefnitionRepository.PAWN_DEFINITION;
import static com.tl.chess.pieces.PieceDefnitionRepository.QUEEN_DEFINITION;
import static com.tl.chess.pieces.PieceDefnitionRepository.ROOK_DEFINITION;

import com.tl.chess.Field;
import com.tl.chess.Position;
import com.tl.chess.boards.StandardChessboard;
import com.tl.chess.pieces.RealPiece;
import java.util.List;

class PositionRepository {

    static Position POSITION = new Position(new StandardChessboard(), List.of(
            new RealPiece(1, true, ROOK_DEFINITION, new Field(0, 0)),
            new RealPiece(3, true, BISHOP_DEFINITION, new Field(4, 4)),
            new RealPiece(5, true, KING_DEFINITION, new Field(0, 4)),
            new RealPiece(6, true, BISHOP_DEFINITION, new Field(2, 3)),
            new RealPiece(8, true, ROOK_DEFINITION, new Field(0, 7)),
            new RealPiece(10, true, PAWN_DEFINITION, new Field(1, 1)),
            new RealPiece(11, true, PAWN_DEFINITION, new Field(1, 2)),
            new RealPiece(14, true, PAWN_DEFINITION, new Field(2, 6)),
            new RealPiece(15, true, PAWN_DEFINITION, new Field(5, 6)),

            new RealPiece(17, false, ROOK_DEFINITION, new Field(7, 0)),
            new RealPiece(20, false, QUEEN_DEFINITION, new Field(3, 3)),
            new RealPiece(21, false, KING_DEFINITION, new Field(7, 4)),
            new RealPiece(22, false, BISHOP_DEFINITION, new Field(3, 5)),
            new RealPiece(24, false, ROOK_DEFINITION, new Field(7, 7)),
            new RealPiece(25, false, PAWN_DEFINITION, new Field(5, 0)),
            new RealPiece(27, false, PAWN_DEFINITION, new Field(4, 2)),
            new RealPiece(29, false, PAWN_DEFINITION, new Field(2, 4)),
            new RealPiece(31, false, PAWN_DEFINITION, new Field(4, 6)),
            new RealPiece(32, false, PAWN_DEFINITION, new Field(4, 7)),
            new RealPiece(33, false, PAWN_DEFINITION, new Field(6, 7))
    ), false, List.of());
}
