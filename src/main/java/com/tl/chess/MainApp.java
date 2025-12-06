package com.tl.chess;

import com.tl.chess.boards.Chessboard;
import com.tl.chess.boards.StandardChessboard;
import com.tl.chess.display.ConsoleDisplayManager;
import com.tl.chess.display.DisplayManager;
import com.tl.chess.engines.Day6Engine;
import com.tl.chess.engines.Engine;
import com.tl.chess.pieces.KnightMoveRuleSimply;
import com.tl.chess.pieces.Pawn1MoveRuleSimply;
import com.tl.chess.pieces.Pawn2MoveRuleSimply;
import com.tl.chess.pieces.PieceDefinition;
import com.tl.chess.pieces.RealPiece;
import java.util.List;

public class MainApp {

    public static void main(String[] args) {
        Chessboard chessboard = new StandardChessboard();
        PieceDefinition rookDefinition = new PieceDefinition('R', List.of());
        PieceDefinition knightDefinition = new PieceDefinition('N',
                List.of(new KnightMoveRuleSimply()));
        PieceDefinition bishopDefinition = new PieceDefinition('B', List.of());
        PieceDefinition queenDefinition = new PieceDefinition('Q', List.of());
        PieceDefinition kingDefinition = new PieceDefinition('K', List.of());
        PieceDefinition pawnDefinition = new PieceDefinition('P', List.of(
                new Pawn1MoveRuleSimply(),
                new Pawn2MoveRuleSimply()
        ));

        Position initialPosition = new Position(chessboard, List.of(
                new RealPiece(1, true, rookDefinition, new Field(0, 0)),
                new RealPiece(2, true, knightDefinition, new Field(0, 1)),
                new RealPiece(3, true, bishopDefinition, new Field(0, 2)),
                new RealPiece(4, true, queenDefinition, new Field(0, 3)),
                new RealPiece(5, true, kingDefinition, new Field(0, 4)),
                new RealPiece(6, true, bishopDefinition, new Field(0, 5)),
                new RealPiece(7, true, knightDefinition, new Field(0, 6)),
                new RealPiece(8, true, rookDefinition, new Field(0, 7)),
                new RealPiece(17, false, rookDefinition, new Field(7, 0)),
                new RealPiece(18, false, knightDefinition, new Field(7, 1)),
                new RealPiece(19, false, bishopDefinition, new Field(7, 2)),
                new RealPiece(20, false, queenDefinition, new Field(7, 3)),
                new RealPiece(21, false, kingDefinition, new Field(7, 4)),
                new RealPiece(22, false, bishopDefinition, new Field(7, 5)),
                new RealPiece(23, false, knightDefinition, new Field(7, 6)),
                new RealPiece(24, false, rookDefinition, new Field(7, 7)),
                new RealPiece(9, true, pawnDefinition, new Field(1, 0)),
                new RealPiece(10, true, pawnDefinition, new Field(1, 1)),
                new RealPiece(11, true, pawnDefinition, new Field(1, 2)),
                new RealPiece(12, true, pawnDefinition, new Field(1, 3)),
                new RealPiece(13, true, pawnDefinition, new Field(1, 4)),
                new RealPiece(14, true, pawnDefinition, new Field(1, 5)),
                new RealPiece(15, true, pawnDefinition, new Field(1, 6)),
                new RealPiece(16, true, pawnDefinition, new Field(1, 7)),
                new RealPiece(25, false, pawnDefinition, new Field(6, 0)),
                new RealPiece(26, false, pawnDefinition, new Field(6, 1)),
                new RealPiece(27, false, pawnDefinition, new Field(6, 2)),
                new RealPiece(28, false, pawnDefinition, new Field(6, 3)),
                new RealPiece(29, false, pawnDefinition, new Field(6, 4)),
                new RealPiece(30, false, pawnDefinition, new Field(6, 5)),
                new RealPiece(31, false, pawnDefinition, new Field(6, 6)),
                new RealPiece(32, false, pawnDefinition, new Field(6, 7))
        ));
        DisplayManager displayManager = new ConsoleDisplayManager();
        displayManager.printPosition(initialPosition);
        System.out.println("---------------");

        Engine engine = new Day6Engine();
        List<Position> nextPositions = engine.calculateNextPositions(initialPosition);
        for(Position position: nextPositions) {
            displayManager.printPosition(position);
            System.out.println("---------------");
            List<Position> secondMovePositions = engine.calculateNextPositions(position);
            for (Position position2: secondMovePositions) {
                displayManager.printPosition(position2);
                System.out.println("---------------");
            }
        }
    }

}
