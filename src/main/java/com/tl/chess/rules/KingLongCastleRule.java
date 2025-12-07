package com.tl.chess.rules;

import com.tl.chess.common.Field;
import com.tl.chess.common.Position;
import com.tl.chess.pieces.RealPiece;
import java.util.Optional;
import java.util.Set;

public class KingLongCastleRule {

    public Optional<Position> calculateNextPossiblePosition(Position position, Set<Field> attackedFields) {
        int line = position.isWhiteTurn() ? 0 : 7;
        Field kingField = new Field(line, 4);
        Field rookField = new Field(line, 0);
        Field freeField1 = new Field(line, 1);
        Field freeField2 = new Field(line, 2);
        Field freeField3 = new Field(line, 3);
        RealPiece kingPiece = position.getPieceOnField(kingField);
        RealPiece freePiece1 = position.getPieceOnField(freeField1);
        RealPiece freePiece2 = position.getPieceOnField(freeField2);
        RealPiece freePiece3 = position.getPieceOnField(freeField3);
        RealPiece rookPiece = position.getPieceOnField(rookField);
        if (freePiece1 == null && freePiece2 == null && freePiece3 == null
                && kingPiece != null && kingPiece.isWhite() == position.isWhiteTurn() && kingPiece.getUpperSign() == 'K'
                && !kingPiece.wasMovedDuringTheGame()
                && rookPiece != null && rookPiece.isWhite() == position.isWhiteTurn() && rookPiece.getUpperSign() == 'R'
                && !rookPiece.wasMovedDuringTheGame()
                && !attackedFields.contains(freeField2)
                && !attackedFields.contains(freeField3)
                && !attackedFields.contains(kingField)) {
            Position newPosition = position.clone();
            newPosition.getPieceOnField(kingField).moveTo(new Field(line, 2));
            newPosition.getPieceOnField(rookField).moveTo(new Field(line, 3));
            newPosition.addMove(position.isWhiteTurn() ? "w 0-0-0" : "b 0-0-0");
            return Optional.of(newPosition);
        }
        return Optional.empty();
    }
}
