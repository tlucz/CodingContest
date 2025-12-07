package com.tl.chess.common;

import com.tl.chess.pieces.PieceDefinition;
import com.tl.chess.pieces.PieceDefinitionRepository;
import com.tl.chess.pieces.RealPiece;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PositionConverter {

    public static List<RealPiece> getPiecesFromTxt(String text) {
        String[] lines = text.split("\n");
        List<RealPiece> pieces = new ArrayList<>();

        for (int rowIdx = 0; rowIdx < lines.length; rowIdx++) {
            int lineNumber = 7 - rowIdx;
            for (int columnIdx = 0; columnIdx < lines[rowIdx].length(); columnIdx++) {
                char pieceName = lines[rowIdx].charAt(columnIdx);
                Optional<PieceDefinition> pieceDefinition = PieceDefinitionRepository.getPieceDefinitionFor(pieceName);
                if (pieceDefinition.isPresent()) {
                    pieces.add(new RealPiece(
                            pieces.size() + 1,
                            Character.isUpperCase(pieceName),
                            pieceDefinition.get(),
                            new Field(lineNumber, columnIdx)));
                }
            }
        }
        return pieces;
    }
}
