package com.tl.chess.rules.piecemove;

import com.tl.chess.common.Field;
import com.tl.chess.common.Position;
import com.tl.chess.pieces.RealPiece;
import java.util.ArrayList;
import java.util.List;

public class KnightMoveRuleSimply implements SimplyPieceMoveRule {

    @Override
    public List<Field> calculateNextPossibleField(RealPiece piece, Position position) {
        List<Field> possibleFields = new ArrayList<>();
        possibleFields.add(new Field(piece.getCurrentField().line() + 2, piece.getCurrentField().file() + 1));
        possibleFields.add(new Field(piece.getCurrentField().line() + 2, piece.getCurrentField().file() - 1));
        possibleFields.add(new Field(piece.getCurrentField().line() - 2, piece.getCurrentField().file() + 1));
        possibleFields.add(new Field(piece.getCurrentField().line() - 2, piece.getCurrentField().file() - 1));
        possibleFields.add(new Field(piece.getCurrentField().line() + 1, piece.getCurrentField().file() + 2));
        possibleFields.add(new Field(piece.getCurrentField().line() + 1, piece.getCurrentField().file() - 2));
        possibleFields.add(new Field(piece.getCurrentField().line() - 1, piece.getCurrentField().file() + 2));
        possibleFields.add(new Field(piece.getCurrentField().line() - 1, piece.getCurrentField().file() - 2));
        return possibleFields.stream().filter(field -> position.getChessboard().isInBoard(field)).toList();
    }

    @Override
    public boolean isAttacking() {
        return true;
    }
}
