package com.tl.chess.rules.piecemove;

import com.tl.chess.common.Field;
import com.tl.chess.common.Position;
import com.tl.chess.pieces.RealPiece;
import java.util.List;
import java.util.Optional;

public interface ComplicatedPieceMoveRule {

    List<Position> calculateNextPossiblePositions(RealPiece piece, Position position);
}
