package com.tl.chess.pieces;

import static com.tl.chess.pieces.PieceDefnitionRepository.BISHOP_DEFINITION;
import static com.tl.chess.pieces.PieceDefnitionRepository.KNIGHT_DEFINITION;
import static com.tl.chess.pieces.PieceDefnitionRepository.QUEEN_DEFINITION;
import static com.tl.chess.pieces.PieceDefnitionRepository.ROOK_DEFINITION;

import com.tl.chess.Position;
import java.util.List;

public class PawnPromotionPostProcessingRule implements PostProcessingRule {

    @Override
    public List<Position> calculatePossibleProcessedPositions(Position position) {
        if (position.isWhiteTurn()) {
            for (RealPiece piece : position.getPieces()) {
                if (piece.isWhite() && Character.toUpperCase(piece.getDisplaySign()) == 'P'
                        && piece.currentField.line() == 7) {
                    return List.of(
                            changePieceTo(position, piece, QUEEN_DEFINITION),
                            changePieceTo(position, piece, KNIGHT_DEFINITION),
                            changePieceTo(position, piece, BISHOP_DEFINITION),
                            changePieceTo(position, piece, ROOK_DEFINITION)
                    );
                }
            }
        } else {
            for (RealPiece piece : position.getPieces()) {
                if (!piece.isWhite() && Character.toUpperCase(piece.getDisplaySign()) == 'P'
                        && piece.currentField.line() == 0) {
                    return List.of(
                            changePieceTo(position, piece, QUEEN_DEFINITION),
                            changePieceTo(position, piece, KNIGHT_DEFINITION),
                            changePieceTo(position, piece, BISHOP_DEFINITION),
                            changePieceTo(position, piece, ROOK_DEFINITION)
                    );
                }
            }
        }
        return List.of(position);
    }

    private Position changePieceTo(Position position, RealPiece piece, PieceDefinition pieceDefinition) {
        Position newPosition = position.clone();
        position.getPieces().stream().filter(p -> p.getId() == piece.getId()).findFirst()
                .ifPresent(p -> {
                    p.setPieceDefinition(pieceDefinition);
                    newPosition.enhanceLastMove(move -> move + pieceDefinition.getDisplaySign());
                });
        return newPosition;
    }
}
