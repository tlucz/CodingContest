package com.tl.chess.pieces;

import com.tl.chess.common.Field;
import com.tl.chess.common.Position;
import java.util.List;

public interface SimplyPieceMoveRule {

    List<Field> calculateNextPossibleField(RealPiece piece, Position position);

    boolean isAttacking();
}
