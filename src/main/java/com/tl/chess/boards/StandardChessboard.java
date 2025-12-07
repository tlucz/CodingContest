package com.tl.chess.boards;

import com.tl.chess.common.Field;

public class StandardChessboard implements Chessboard {

    private final int BOARD_SIZE = 8;

    @Override
    public int getFilesNumber() {
        return BOARD_SIZE;
    }

    @Override
    public int getLinesNumber() {
        return BOARD_SIZE;
    }

    @Override
    public boolean isInBoard(Field field) {
        return field.file() >= 0 && field.file() < BOARD_SIZE && field.line() >= 0 && field.line() < BOARD_SIZE;
    }
}
