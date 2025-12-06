package com.tl.chess.engines;

import com.tl.chess.Field;
import com.tl.chess.Position;
import com.tl.chess.pieces.RealPiece;
import com.tl.chess.pieces.SimplyPieceMoveRule;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractEngine implements Engine {

    public AbstractEngine() {
    }

    public List<Position> calculateNextPositions(Position position) {
        List<Position> nextPositions = new ArrayList<>();
        for (RealPiece piece : position.getPieces().stream().filter(p -> p.isWhite() == position.isWhiteTurn()).toList()) {
            for (SimplyPieceMoveRule simplyPieceMoveRule : piece.getPieceDefinition().getPieceMoveRules()) {
                List<Field> possibleFields = simplyPieceMoveRule.calculateNextPossibleField(piece, position);
                for (Field field : possibleFields) {
                    RealPiece pieceOnField = position.getPieceOnField(field);
                    if (pieceOnField == null) {
                        Position newPosition = position.clone();
                        newPosition.movePiece(piece.getId(), field);
                        newPosition.changeTurn();
                        nextPositions.add(newPosition);
                    } else if(pieceOnField.isWhite()!=piece.isWhite()) {
                        Position newPosition = position.clone();
                        newPosition.movePiece(piece.getId(), field);
                        newPosition.removePiece(pieceOnField.getId());
                        newPosition.changeTurn();
                        nextPositions.add(newPosition);
                    }
                }
            }
        }
        return nextPositions;
    }


}
