package com.tl.chess.boards;

import com.tl.chess.common.Field;

public interface Chessboard {

    int getFilesNumber();

    int getLinesNumber();

    boolean isInBoard(Field field);
}
