package com.tl.chess.rules;

import com.tl.chess.common.Position;
import java.util.List;

public interface PostProcessingRule {
    List<Position> calculatePossibleProcessedPositions(Position position);
}
