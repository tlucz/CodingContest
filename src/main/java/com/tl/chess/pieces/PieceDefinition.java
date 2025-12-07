package com.tl.chess.pieces;


import com.tl.chess.rules.piecemove.SimplyPieceMoveRule;
import java.util.List;

public class PieceDefinition {

    private final Character displaySign;
    private final boolean canBeCaptured;
    private final List<SimplyPieceMoveRule> simplyPieceMoveRules;

    public PieceDefinition(Character displaySign, boolean canBeCaptured, List<SimplyPieceMoveRule> simplyPieceMoveRules) {
        this.displaySign = displaySign;
        this.canBeCaptured = canBeCaptured;
        this.simplyPieceMoveRules = simplyPieceMoveRules;
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
}
