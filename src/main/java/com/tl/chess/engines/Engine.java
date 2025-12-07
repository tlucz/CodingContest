package com.tl.chess.engines;

import com.tl.chess.common.Position;
import java.util.List;

public interface Engine {

    List<Position> calculateNextPositions(Position position);

    boolean isCheckmate(Position position);

}
