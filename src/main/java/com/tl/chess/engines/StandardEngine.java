package com.tl.chess.engines;

import com.tl.chess.common.Field;
import com.tl.chess.common.Position;
import com.tl.chess.pieces.KingLongCastleRule;
import com.tl.chess.pieces.KingShortCastleRule;
import com.tl.chess.pieces.PawnPromotionPostProcessingRule;
import com.tl.chess.pieces.PostProcessingRule;
import com.tl.chess.pieces.RealPiece;
import com.tl.chess.pieces.SimplyPieceMoveRule;
import java.util.ArrayList;
import java.util.List;

public class StandardEngine implements Engine {

    private final PostProcessingRule postProcessingRule;
    private final KingShortCastleRule kingShortCastleRule;
    private final KingLongCastleRule kingLongCastleRule;

    public StandardEngine() {
        postProcessingRule = new PawnPromotionPostProcessingRule();
        kingShortCastleRule = new KingShortCastleRule();
        kingLongCastleRule = new KingLongCastleRule();
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
                        nextPositions.add(newPosition);
                    } else if (pieceOnField.isWhite() != piece.isWhite() && pieceOnField.getPieceDefinition().canBeCaptured()) {
                        Position newPosition = position.clone();
                        newPosition.movePiece(piece.getId(), field);
                        newPosition.capturePiece(pieceOnField.getId());
                        nextPositions.add(newPosition);
                    }
                }
            }
        }
        kingShortCastleRule.calculateNextPossiblePosition(position, getAttackedFields(position, !position.isWhiteTurn())).ifPresent(nextPositions::add);
        kingLongCastleRule.calculateNextPossiblePosition(position, getAttackedFields(position, !position.isWhiteTurn())).ifPresent(nextPositions::add);

        nextPositions = nextPositions.stream().flatMap(
                p -> postProcessingRule.calculatePossibleProcessedPositions(p).stream())
                .filter(p->!isCheck(p, p.isWhiteTurn()))
                .toList();

        nextPositions.forEach(p -> p.changeTurn());

        return nextPositions;
    }

    @Override
    public boolean isCheckmate(Position position) {
        if (isCheck(position, position.isWhiteTurn())) {
            boolean b = calculateNextPositions(position).stream().allMatch(p -> isCheck(p, !p.isWhiteTurn()));
            return b;
        }
        return false;
    }

    private boolean isCheck(Position position, boolean whiteKing) {
        List<Field> attackedFields = getAttackedFields(position, !whiteKing);
        Field kingField = position.getPieces().stream()
                .filter(p -> p.isWhite() == whiteKing)
                .filter(p -> Character.toUpperCase(p.getDisplaySign()) == 'K')
                .findAny()
                .map(p -> p.getCurrentField())
                .orElseThrow();
        return attackedFields.contains(kingField);
    }

    private List<Field> getAttackedFields(Position position, boolean isWhite) {
        List<Field> attackedFields = new ArrayList<>();
        for (RealPiece piece : position.getPieces().stream().filter(p -> p.isWhite() == isWhite).toList()) {
            for (SimplyPieceMoveRule simplyPieceMoveRule : piece.getPieceDefinition().getPieceMoveRules()) {
                if (simplyPieceMoveRule.isAttacking()) {
                    List<Field> possibleFields = simplyPieceMoveRule.calculateNextPossibleField(piece, position);
                    attackedFields.addAll(possibleFields);
                }
            }
        }
        return attackedFields;
    }
}