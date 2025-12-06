package com.tl.chess.pieces;

import com.tl.chess.Field;
import com.tl.chess.Position;
import java.util.List;

public interface SimplyPieceMoveRule {

    List<Field> calculateNextPossibleField(RealPiece piece, Position position);

    boolean isAttacking();
}
