package com.tl.chess.rules.piecemove;

import com.tl.chess.common.Field;
import com.tl.chess.common.Position;
import com.tl.chess.pieces.RealPiece;
import java.util.ArrayList;
import java.util.List;

public class RookMoveRuleSimply implements SimplyPieceMoveRule {

    @Override
    public List<Field> calculateNextPossibleField(RealPiece piece, Position position) {
        List<Field> possibleFields = new ArrayList<>();

        for (int line = piece.getCurrentField().line() + 1; line < position.getChessboard().getLinesNumber(); line++) {
            Field newField = new Field(line, piece.getCurrentField().file());
            RealPiece pieceOnField = position.getPieceOnField(newField);
            if (pieceOnField == null) {
                possibleFields.add(newField);
            } else {
                possibleFields.add(newField);
                break;
            }
        }

        for (int line = piece.getCurrentField().line() - 1; line >= 0; line--) {
            Field newField = new Field(line, piece.getCurrentField().file());
            RealPiece pieceOnField = position.getPieceOnField(newField);
            if (pieceOnField == null) {
                possibleFields.add(newField);
            } else {
                possibleFields.add(newField);
                break;
            }
        }

        for (int file = piece.getCurrentField().file() + 1; file < position.getChessboard().getFilesNumber(); file++) {
            Field newField = new Field(piece.getCurrentField().line(), file);
            RealPiece pieceOnField = position.getPieceOnField(newField);
            if (pieceOnField == null) {
                possibleFields.add(newField);
            } else {
                possibleFields.add(newField);
                break;
            }
        }

        for (int file = piece.getCurrentField().file() - 1; file >= 0; file--) {
            Field newField = new Field(piece.getCurrentField().line(), file);
            RealPiece pieceOnField = position.getPieceOnField(newField);
            if (pieceOnField == null) {
                possibleFields.add(newField);
            } else {
                possibleFields.add(newField);
                break;
            }
        }

        return possibleFields;
    }

    @Override
    public boolean isAttacking() {
        return true;
    }
}
