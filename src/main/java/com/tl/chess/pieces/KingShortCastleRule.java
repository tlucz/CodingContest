package com.tl.chess.pieces;

import com.tl.chess.Field;
import com.tl.chess.Position;
import java.util.List;
import java.util.Optional;

public class KingShortCastleRule {

    public Optional<Position> calculateNextPossiblePosition(Position position, List<Field> attackedFields) {
        int line = position.isWhiteTurn() ? 0 : 7;
        Field kingField = new Field(line, 4);
        Field rookField = new Field(line, 7);
        Field freeField1 = new Field(line, 5);
        Field freeField2 = new Field(line, 6);
        RealPiece kingPiece = position.getPieceOnField(kingField);
        RealPiece freePiece1 = position.getPieceOnField(freeField1);
        RealPiece freePiece2 = position.getPieceOnField(freeField2);
        RealPiece rookPiece = position.getPieceOnField(rookField);
        if (freePiece1 == null && freePiece2 == null
                && kingPiece != null && kingPiece.isWhite() == position.isWhiteTurn() && kingPiece.getUpperSign() == 'K'
                && !kingPiece.wasMovedDuringTheGame
                && rookPiece != null && rookPiece.isWhite() == position.isWhiteTurn() && rookPiece.getUpperSign() == 'R'
                && !rookPiece.wasMovedDuringTheGame
                && !attackedFields.contains(freeField1)
                && !attackedFields.contains(freeField2)
                && !attackedFields.contains(kingField)
        ) {
            Position newPosition = position.clone();
            newPosition.getPieceOnField(kingField).moveTo(new Field(line, 6));
            newPosition.getPieceOnField(rookField).moveTo(new Field(line, 5));
            newPosition.addMove(position.isWhiteTurn() ? "w 0-0" : "b 0-0");
            return Optional.of(newPosition);
        }
        return Optional.empty();
    }
}
