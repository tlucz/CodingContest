package com.tl.chess.pieces;


import java.util.List;

public class PieceDefinition {

    private final Character displaySign;
    private final List<SimplyPieceMoveRule> simplyPieceMoveRules;

    public PieceDefinition(Character displaySign, List<SimplyPieceMoveRule> simplyPieceMoveRules) {
        this.displaySign = displaySign;
        this.simplyPieceMoveRules = simplyPieceMoveRules;
    }

    public Character getDisplaySign() {
        return displaySign;
    }

    public List<SimplyPieceMoveRule> getPieceMoveRules() {
        return simplyPieceMoveRules;
    }
}
