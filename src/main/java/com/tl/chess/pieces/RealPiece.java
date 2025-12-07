package com.tl.chess.pieces;

import com.tl.chess.Field;
import java.util.Arrays;

public class RealPiece implements Cloneable {

    int id;
    boolean isWhite;
    PieceDefinition pieceDefinition;
    Field currentField;
    Field previousField;
    boolean wasMovedDuringTheGame;

    public RealPiece(int id, boolean isWhite, PieceDefinition pieceDefinition, Field currentField) {
        this.id = id;
        this.isWhite = isWhite;
        this.pieceDefinition = pieceDefinition;
        this.currentField = currentField;
    }

    public int getId() {
        return id;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public PieceDefinition getPieceDefinition() {
        return pieceDefinition;
    }

    public Field getCurrentField() {
        return currentField;
    }

    public Field getPreviousField() {
        return previousField;
    }

    public boolean isWasMovedDuringTheGame() {
        return wasMovedDuringTheGame;
    }

    public char getDisplaySign() {
        return isWhite() ?
                Character.toUpperCase(getPieceDefinition().getDisplaySign()) :
                Character.toLowerCase(getPieceDefinition().getDisplaySign());
    }

    @Override
    public RealPiece clone() {
        return new RealPiece(id, isWhite, pieceDefinition, new Field(currentField.line(), currentField.file()));
    }

    public void moveTo(Field field) {
        previousField = currentField;
        currentField = field;
        wasMovedDuringTheGame = true;
    }

    public void setPieceDefinition(PieceDefinition pieceDefinition) {
        this.pieceDefinition = pieceDefinition;
    }

    public char getUpperSign() {
        return Character.toUpperCase(getDisplaySign());
    }
}