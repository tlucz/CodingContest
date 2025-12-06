package com.tl.chess.pieces;

import com.tl.chess.Field;
import com.tl.chess.Position;
import java.util.List;

public class Pawn1MoveRuleSimply implements SimplyPieceMoveRule {

    @Override
    public List<Field> calculateNextPossibleField(RealPiece piece, Position position) {
        Field field = null;
        if (piece.isWhite()) {
            field = new Field(piece.getCurrentField().line() + 1, piece.getCurrentField().file());
        } else {
            field= new Field(piece.getCurrentField().line() - 1, piece.getCurrentField().file());
        }
        if(position.getPieceOnField(field) == null) {
            return List.of(field);
        }
        return List.of();
    }

    @Override
    public boolean isAttacking() {
        return false;
    }
}
