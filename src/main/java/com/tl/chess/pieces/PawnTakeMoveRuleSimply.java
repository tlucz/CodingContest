package com.tl.chess.pieces;

import com.tl.chess.Field;
import com.tl.chess.Position;
import java.util.ArrayList;
import java.util.List;

public class PawnTakeMoveRuleSimply implements SimplyPieceMoveRule {

    @Override
    public List<Field> calculateNextPossibleField(RealPiece piece, Position position) {
        Field field1 = null;
        Field field2 = null;
        List<Field> result = new ArrayList<>();
        if (piece.isWhite()) {
            field1 = new Field(piece.getCurrentField().line() + 1, piece.getCurrentField().file()-1);
            field2 = new Field(piece.getCurrentField().line() + 1, piece.getCurrentField().file()+1);
        } else {
            field1 = new Field(piece.getCurrentField().line() - 1, piece.getCurrentField().file()-1);
            field2 = new Field(piece.getCurrentField().line() - 1, piece.getCurrentField().file()+1);
        }
        RealPiece pieceOnField1 = position.getPieceOnField(field1);
        RealPiece pieceOnField2 = position.getPieceOnField(field2);
        if(pieceOnField1 != null && pieceOnField1.isWhite!= piece.isWhite()) {
            result.add(field1);
        }
        if(pieceOnField2 != null && pieceOnField2.isWhite!= piece.isWhite()) {
            result.add(field2);
        }
        return result;
    }
}
