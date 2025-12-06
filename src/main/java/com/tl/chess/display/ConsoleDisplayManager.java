package com.tl.chess.display;

import com.tl.chess.Position;
import com.tl.chess.pieces.RealPiece;

public class ConsoleDisplayManager implements DisplayManager{

    @Override
    public void printPosition(Position position) {
        int files = position.getChessboard().getFilesNumber();
        int lines = position.getChessboard().getLinesNumber();
        char[][] board = new char[lines][files];
        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < files; j++) {
                board[i][j] = ' ';
            }
        }
        for (RealPiece piece : position.getPieces()) {
            board[piece.getCurrentField().line()][piece.getCurrentField().file()] =
                    piece.getDisplaySign();
        }
        for (int i = lines-1; i >=0 ; i--) {
            for (int j = 0; j < files; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }
}
