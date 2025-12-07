package com.tl.chess.days.year2025day6;

import com.tl.chess.Position;
import java.util.function.Predicate;

public class Day6PositionFilter implements Predicate<Position> {

    @Override
    public boolean test(Position position) {
        if(position.getMoves().size() > 10) {
            return false;
        }
        int blackMoves = (position.getMoves().size()) / 2;
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
