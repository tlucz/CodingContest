package com.tl.chess.rules.piecemove;

import com.tl.chess.common.Field;
import com.tl.chess.common.Position;
import com.tl.chess.pieces.RealPiece;
import java.util.List;

public interface SimplyPieceMoveRule {

    List<Field> calculateNextPossibleField(RealPiece piece, Position position);

    boolean isAttacking();
}
