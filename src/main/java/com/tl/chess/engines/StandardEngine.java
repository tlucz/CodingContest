package com.tl.chess.engines;

import com.tl.chess.common.Field;
import com.tl.chess.common.Position;
import com.tl.chess.pieces.RealPiece;
import com.tl.chess.rules.KingLongCastleRule;
import com.tl.chess.rules.KingShortCastleRule;
import com.tl.chess.rules.PawnPromotionPostProcessingRule;
import com.tl.chess.rules.PostProcessingRule;
import com.tl.chess.rules.piecemove.ComplicatedPieceMoveRule;
import com.tl.chess.rules.piecemove.SimplyPieceMoveRule;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StandardEngine implements Engine {

    private final PostProcessingRule postProcessingRule;
    private final KingShortCastleRule kingShortCastleRule;
    private final KingLongCastleRule kingLongCastleRule;

    public StandardEngine() {
        postProcessingRule = new PawnPromotionPostProcessingRule();
        kingShortCastleRule = new KingShortCastleRule();
        kingLongCastleRule = new KingLongCastleRule();
    }

    public List<Position> calculateNextPositions(Position position, boolean shouldAnnotatePosition) {
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
            for (ComplicatedPieceMoveRule rule : piece.getPieceDefinition().getComplicatedRules()) {
                nextPositions.addAll(rule.calculateNextPossiblePositions(piece, position));
            }
        }

        kingShortCastleRule.calculateNextPossiblePosition(position, getAttackedFields(position, !position.isWhiteTurn()))
                .ifPresent(nextPositions::add);
        kingLongCastleRule.calculateNextPossiblePosition(position, getAttackedFields(position, !position.isWhiteTurn()))
                .ifPresent(nextPositions::add);

        nextPositions = nextPositions.stream().flatMap(
                        p -> postProcessingRule.calculatePossibleProcessedPositions(p).stream())
                .filter(p -> !isCheck(p, p.isWhiteTurn()))
                .filter(Position::areKingsSeparated)
                .toList();
        nextPositions.forEach(p -> {
            p.changeTurn();
            if (shouldAnnotatePosition) {
                if (isCheckmate(p)) {
                    p.enhanceLastMove(m -> m + "#");
                } else if (isStalemate(p)) {
                    p.enhanceLastMove(m -> m + "=");
                } else if (isCheck(p, p.isWhiteTurn())) {
                    p.enhanceLastMove(m -> m + "+");
                }
            }
        });

        return nextPositions;
    }

    @Override
    public boolean isCheckmate(Position position) {
        if (isCheck(position, position.isWhiteTurn())) {
            return calculateNextPositions(position, false).stream().allMatch(p -> isCheck(p, !p.isWhiteTurn()));
        }
        return false;
    }

    @Override
    public boolean isStalemate(Position position) {
        if (!isCheck(position, position.isWhiteTurn())) {
            return calculateNextPositions(position, false).isEmpty();
        }
        return false;
    }

    private boolean isCheck(Position position, boolean whiteKing) {
        Set<Field> attackedFields = getAttackedFields(position, !whiteKing);
        Field kingField = position.getPieces().stream()
                .filter(p -> p.isWhite() == whiteKing)
                .filter(p -> Character.toUpperCase(p.getDisplaySign()) == 'K')
                .findAny()
                .map(RealPiece::getCurrentField)
                .orElseThrow();
        return attackedFields.contains(kingField);
    }

    private Set<Field> getAttackedFields(Position position, boolean isWhite) {
        Set<Field> attackedFields = new HashSet<>();
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