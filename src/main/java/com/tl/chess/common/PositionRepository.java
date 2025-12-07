package com.tl.chess.common;

import static com.tl.chess.pieces.PieceDefnitionRepository.BISHOP_DEFINITION;
import static com.tl.chess.pieces.PieceDefnitionRepository.KING_DEFINITION;
import static com.tl.chess.pieces.PieceDefnitionRepository.KNIGHT_DEFINITION;
import static com.tl.chess.pieces.PieceDefnitionRepository.PAWN_DEFINITION;
import static com.tl.chess.pieces.PieceDefnitionRepository.QUEEN_DEFINITION;
import static com.tl.chess.pieces.PieceDefnitionRepository.ROOK_DEFINITION;

import com.tl.chess.boards.StandardChessboard;
import com.tl.chess.pieces.RealPiece;
import java.util.List;

 public class PositionRepository {

     public static Position START_POSITION = new Position(new StandardChessboard(), List.of(
            new RealPiece(1, true, ROOK_DEFINITION, new Field(0, 0)),
            new RealPiece(2, true, KNIGHT_DEFINITION, new Field(0, 1)),
            new RealPiece(3, true, BISHOP_DEFINITION, new Field(0, 2)),
            new RealPiece(4, true, QUEEN_DEFINITION, new Field(0, 3)),
            new RealPiece(5, true, KING_DEFINITION, new Field(0, 4)),
            new RealPiece(6, true, BISHOP_DEFINITION, new Field(0, 5)),
            new RealPiece(7, true, KNIGHT_DEFINITION, new Field(0, 6)),
            new RealPiece(8, true, ROOK_DEFINITION, new Field(0, 7)),
            new RealPiece(17, false, ROOK_DEFINITION, new Field(7, 0)),
            new RealPiece(18, false, KNIGHT_DEFINITION, new Field(7, 1)),
            new RealPiece(19, false, BISHOP_DEFINITION, new Field(7, 2)),
            new RealPiece(20, false, QUEEN_DEFINITION, new Field(7, 3)),
            new RealPiece(21, false, KING_DEFINITION, new Field(7, 4)),
            new RealPiece(22, false, BISHOP_DEFINITION, new Field(7, 5)),
            new RealPiece(23, false, KNIGHT_DEFINITION, new Field(7, 6)),
            new RealPiece(24, false, ROOK_DEFINITION, new Field(7, 7)),
            new RealPiece(9, true, PAWN_DEFINITION, new Field(1, 0)),
            new RealPiece(10, true, PAWN_DEFINITION, new Field(1, 1)),
            new RealPiece(11, true, PAWN_DEFINITION, new Field(1, 2)),
            new RealPiece(12, true, PAWN_DEFINITION, new Field(1, 3)),
            new RealPiece(13, true, PAWN_DEFINITION, new Field(1, 4)),
            new RealPiece(14, true, PAWN_DEFINITION, new Field(1, 5)),
            new RealPiece(15, true, PAWN_DEFINITION, new Field(1, 6)),
            new RealPiece(16, true, PAWN_DEFINITION, new Field(1, 7)),
            new RealPiece(25, false, PAWN_DEFINITION, new Field(6, 0)),
            new RealPiece(26, false, PAWN_DEFINITION, new Field(6, 1)),
            new RealPiece(27, false, PAWN_DEFINITION, new Field(6, 2)),
            new RealPiece(28, false, PAWN_DEFINITION, new Field(6, 3)),
            new RealPiece(29, false, PAWN_DEFINITION, new Field(6, 4)),
            new RealPiece(30, false, PAWN_DEFINITION, new Field(6, 5)),
            new RealPiece(31, false, PAWN_DEFINITION, new Field(6, 6)),
            new RealPiece(32, false, PAWN_DEFINITION, new Field(6, 7))
    ), true, List.of());

}
