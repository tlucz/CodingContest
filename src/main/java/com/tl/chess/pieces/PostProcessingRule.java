package com.tl.chess.pieces;

import com.tl.chess.Position;
import java.util.List;

public interface PostProcessingRule {
    List<Position> calculatePossibleProcessedPositions(Position position);
}
