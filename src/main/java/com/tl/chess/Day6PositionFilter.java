package com.tl.chess;

public class Day6PositionFilter implements PositionFilter {

    @Override
    public boolean isPositionOk(Position position) {
        if(position.moves.size() > 10) {
            return false;
        }
        int blackMoves = (position.moves.size()) / 2;
        if (blackMoves == 0) {
            return true;
        }
        if (blackMoves < 5) {
            int pawnShouldBeAtLine = 4 - blackMoves + 1;
            return position.getPieces().stream()
                    .filter(p -> !p.isWhite())
                    .filter(p -> p.getDisplaySign() == 'p')
                    .filter(p->p.getId()==29)
                    .anyMatch(p -> p.getCurrentField().line() == pawnShouldBeAtLine);
        } else if (blackMoves == 5) {
            return position.getPieces().stream()
                    .filter(p -> !p.isWhite())
                    .filter(p -> p.getDisplaySign() == 'n')
                    .anyMatch(p -> p.getCurrentField().line() == 0);
        } else {
            return false;
        }
    }
}
