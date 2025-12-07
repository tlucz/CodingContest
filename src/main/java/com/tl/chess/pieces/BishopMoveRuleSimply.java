package com.tl.chess.pieces;

import com.tl.chess.common.Field;
import com.tl.chess.common.Position;
import java.util.ArrayList;
import java.util.List;

public class BishopMoveRuleSimply implements SimplyPieceMoveRule{

    @Override
    public List<Field> calculateNextPossibleField(RealPiece piece, Position position) {
        List<Field> possibleFields = new ArrayList<>();

        int lineChange=1;
        int fileChange=1;
        Field newField = new Field(piece.getCurrentField().line(), piece.getCurrentField().file());
        while(true) {
            newField = new Field(newField.line() + lineChange, newField.file() + fileChange);
            if(!position.getChessboard().isInBoard(newField)) {
                break;
            }
            RealPiece pieceOnField = position.getPieceOnField(newField);
            if (pieceOnField == null) {
                possibleFields.add(newField);
            } else {
                possibleFields.add(newField);
                break;
            }
        }

        lineChange=1;
        fileChange=-1;
        newField = new Field(piece.getCurrentField().line(), piece.getCurrentField().file());
        while(true) {
            newField = new Field(newField.line() + lineChange, newField.file() + fileChange);
            if(!position.getChessboard().isInBoard(newField)) {
                break;
            }
            RealPiece pieceOnField = position.getPieceOnField(newField);
            if (pieceOnField == null) {
                possibleFields.add(newField);
            } else {
                possibleFields.add(newField);
                break;
            }
        }

        lineChange=-1;
        fileChange=1;
        newField = new Field(piece.getCurrentField().line(), piece.getCurrentField().file());
        while(true) {
            newField = new Field(newField.line() + lineChange, newField.file() + fileChange);
            if(!position.getChessboard().isInBoard(newField)) {
                break;
            }
            RealPiece pieceOnField = position.getPieceOnField(newField);
            if (pieceOnField == null) {
                possibleFields.add(newField);
            } else {
                possibleFields.add(newField);
                break;
            }
        }

        lineChange=-1;
        fileChange=-1;
        newField = new Field(piece.getCurrentField().line(), piece.getCurrentField().file());
        while(true) {
            newField = new Field(newField.line() + lineChange, newField.file() + fileChange);
            if(!position.getChessboard().isInBoard(newField)) {
                break;
            }
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
