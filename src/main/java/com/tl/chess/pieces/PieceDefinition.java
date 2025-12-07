package com.tl.chess.pieces;


import com.tl.chess.rules.piecemove.ComplicatedPieceMoveRule;
import com.tl.chess.rules.piecemove.SimplyPieceMoveRule;
import java.util.List;

public class PieceDefinition {


    private final Character displaySign;
    private final boolean canBeCaptured;
    private final List<SimplyPieceMoveRule> simplyPieceMoveRules;
    private final List<ComplicatedPieceMoveRule> complicatedRules;
    private final PieceType pieceType;

    public PieceDefinition(
            PieceType pieceType,
            Character displaySign,
            boolean canBeCaptured,
            List<SimplyPieceMoveRule> simplyPieceMoveRules,
            List<ComplicatedPieceMoveRule> complicatedRules) {
        this.pieceType = pieceType;
        this.displaySign = displaySign;
        this.canBeCaptured = canBeCaptured;
        this.simplyPieceMoveRules = simplyPieceMoveRules;
        this.complicatedRules = complicatedRules;
    }

    public Character getDisplaySign() {
        return displaySign;
    }

    public List<SimplyPieceMoveRule> getPieceMoveRules() {
        return simplyPieceMoveRules;
    }

    public boolean canBeCaptured() {
        return canBeCaptured;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public List<ComplicatedPieceMoveRule> getComplicatedRules() {
        return complicatedRules;
    }
}
