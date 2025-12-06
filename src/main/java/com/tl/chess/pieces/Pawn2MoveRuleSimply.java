package com.tl.chess.pieces;

import com.tl.chess.Field;
import com.tl.chess.Position;
import java.util.List;

public class Pawn2MoveRuleSimply implements SimplyPieceMoveRule {

    @Override
    public List<Field> calculateNextPossibleField(RealPiece piece, Position position) {
        Field field = null;
        Field passedField = null;
        if (piece.isWhite()) {
            if(piece.getCurrentField().line() != 1) {
                return List.of();
            }
            passedField = new Field(piece.getCurrentField().line() + 1, piece.getCurrentField().file());
            field = new Field(piece.getCurrentField().line() + 2, piece.getCurrentField().file());
        } else {
            if(piece.getCurrentField().line() != 6) {
                return List.of();
            }
            passedField = new Field(piece.getCurrentField().line() - 1, piece.getCurrentField().file());
            field = new Field(piece.getCurrentField().line() - 2, piece.getCurrentField().file());
        }
        if(position.getPieceOnField(field) == null && position.getPieceOnField(passedField)==null) {
            return List.of(field);
        }
        return List.of();
    }
}
