package com.tl.chess;

import com.tl.chess.boards.Chessboard;
import com.tl.chess.pieces.RealPiece;
import java.util.ArrayList;
import java.util.List;

public class Position implements Cloneable {

    Chessboard chessboard;
    List<RealPiece> pieces;
    List<String> moves = new ArrayList<>();
    boolean isWhiteTurn = true;

    public Position(Chessboard chessboard, List<RealPiece> pieces) {
        this.chessboard = chessboard;
        this.pieces = pieces;
    }

    public Chessboard getChessboard() {
        return chessboard;
    }

    public List<RealPiece> getPieces() {
        return pieces;
    }

    public RealPiece getPieceOnField(Field field) {
        return pieces.stream().filter(piece -> piece.getCurrentField().equals(field)).findFirst().orElse(null);
    }

    @Override
    public Position clone() {
        return new Position(chessboard, pieces.stream().map(p -> p.clone()).toList());
    }

    public void removePiece(int id) {
        pieces.stream().filter(p -> p.getId() == id).findAny().ifPresent(p ->
                moves.add("captured:" + p.getDisplaySign() + p.getCurrentField().toString()));
        pieces.removeIf(p -> p.getId() == id);
    }

    public void movePiece(int pieceId, Field field) {
        pieces.stream().filter(p -> p.getId() == pieceId).findAny().ifPresent(
                p -> {
                    moves.add(p.isWhite() ? "w" : "b" + " " +
                            p.getDisplaySign() + p.getCurrentField().toString() + "-" + field.toString());
                    p.moveTo(field);
                });
    }

    public boolean isWhiteTurn() {
        return isWhiteTurn;
    }

    public void changeTurn() {
        isWhiteTurn = !isWhiteTurn;
    }
}
