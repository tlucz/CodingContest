package com.tl.chess.common;

public record Field(int line, int file) {

    @Override
    public String toString() {
        return String.format("%s%d", (char) (file + 65), line + 1);
    }
}
