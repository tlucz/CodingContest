package com.tl.chess.rules.piecemove;

import com.tl.chess.common.Field;
import com.tl.chess.common.Position;
import com.tl.chess.pieces.RealPiece;
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

    @Override
    public boolean isAttacking() {
        return false;
    }
}
