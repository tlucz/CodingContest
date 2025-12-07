package com.tl.chess.common;

import com.tl.chess.boards.Chessboard;
import com.tl.chess.pieces.PieceType;
import com.tl.chess.pieces.RealPiece;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Position implements Cloneable {

    Chessboard chessboard;
    List<RealPiece> pieces;
    List<String> moves;
    boolean isWhiteTurn;
    private RealPiece lastMovedPiece;

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

    public void capturePiece(int id) {
        pieces.stream().filter(p -> p.getId() == id).findAny().ifPresent(p ->
                enhanceLastMove((String text) -> text.replace("-", "x")));
        pieces = pieces.stream().filter(p -> p.getId() != id).toList();
    }

    public void enhanceLastMove(Function<String, String> enhancer) {
        moves.set(moves.size() - 1, enhancer.apply(moves.getLast()));
    }

    public void movePiece(int pieceId, Field field) {
        pieces.stream().filter(p -> p.getId() == pieceId).findAny().ifPresent(
                p -> {
                    moves.add((p.isWhite() ? "w" : "b") + " " +
                            p.getDisplaySign() + p.getCurrentField().toString() + "-" + field.toString());
                    p.moveTo(field);
                    lastMovedPiece = p;
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

    public void addMove(String move) {
        moves.add(move);
    }

    public boolean areKingsSeparated() {
        List<RealPiece> kings = pieces.stream().filter(p -> p.getPieceDefinition().getPieceType() == PieceType.King).toList();
        Field whiteKingField = kings.stream().filter(p -> p.isWhite()).findAny().get().getCurrentField();
        Field blackKingField = kings.stream().filter(p -> !p.isWhite()).findAny().get().getCurrentField();
        return Math.abs(whiteKingField.line() - blackKingField.line())>=2
                || Math.abs(whiteKingField.file() - blackKingField.file())>=2;
    }

    public RealPiece getLastMovedPiece() {
        return lastMovedPiece;
    }
}
