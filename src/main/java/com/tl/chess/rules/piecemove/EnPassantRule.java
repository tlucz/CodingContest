package com.tl.chess.rules.piecemove;

import com.tl.chess.common.Field;
import com.tl.chess.common.Position;
import com.tl.chess.pieces.PieceType;
import com.tl.chess.pieces.RealPiece;
import java.util.List;

public class EnPassantRule implements ComplicatedPieceMoveRule {

    @Override
    public List<Position> calculateNextPossiblePositions(RealPiece piece, Position position) {
        int line = position.isWhiteTurn() ? 4 : 3;
        int direction = position.isWhiteTurn() ? 1 : -1;
        RealPiece lastMovedPiece = position.getLastMovedPiece();
        if (lastMovedPiece == null || piece.getCurrentField().line() != line) {
            return List.of();
        }
        if (lastMovedPiece.isWhite() != piece.isWhite() && lastMovedPiece.getPieceDefinition().getPieceType() == PieceType.Pawn
                && lastMovedPiece.getCurrentField().line() == line &&
                Math.abs(piece.getCurrentField().file() - lastMovedPiece.getCurrentField().file()) == 1) {
            Position newPosition = position.clone();
            newPosition.movePiece(piece.getId(),
                    new Field(
                            line + direction,
                            lastMovedPiece.getCurrentField().file()));
            newPosition.capturePiece(lastMovedPiece.getId());
            newPosition.enhanceLastMove(move -> move + " ep");
            return List.of(newPosition);
        }
        return List.of();
    }
}
