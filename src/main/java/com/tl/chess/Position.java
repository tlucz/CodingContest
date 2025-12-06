package com.tl.chess;

import com.tl.chess.boards.Chessboard;
import com.tl.chess.pieces.RealPiece;
import java.util.ArrayList;
import java.util.List;

public class Position implements Cloneable {

    Chessboard chessboard;
    List<RealPiece> pieces;
    List<String> moves;
    boolean isWhiteTurn;

    public Position(Chessboard chessboard, List<RealPiece> pieces, boolean isWhiteTurn, List<String> moves) {
        this.chessboard = chessboard;
        this.pieces = pieces;
        this.isWhiteTurn = isWhiteTurn;
        this.moves = moves;
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
        return new Position(chessboard, pieces.stream().map(p -> p.clone()).toList(), isWhiteTurn, new ArrayList<>(moves));
    }

    public void removePiece(int id) {
        pieces.stream().filter(p -> p.getId() == id).findAny().ifPresent(p ->
                moves.set(moves.size() - 1, moves.getLast().replace("-", "x")));
        pieces = pieces.stream().filter(p -> p.getId() != id).toList();
    }

    public void movePiece(int pieceId, Field field) {
        pieces.stream().filter(p -> p.getId() == pieceId).findAny().ifPresent(
                p -> {
                    moves.add((p.isWhite() ? "w" : "b") + " " +
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

    public List<String> getMoves() {
        return moves;
    }
}
