package com.tl.chess.pieces;

import com.tl.chess.rules.piecemove.BishopMoveRuleSimply;
import com.tl.chess.rules.piecemove.EnPassantRule;
import com.tl.chess.rules.piecemove.KingMoveRuleSimply;
import com.tl.chess.rules.piecemove.KnightMoveRuleSimply;
import com.tl.chess.rules.piecemove.Pawn1MoveRuleSimply;
import com.tl.chess.rules.piecemove.Pawn2MoveRuleSimply;
import com.tl.chess.rules.piecemove.PawnTakeMoveRuleSimply;
import com.tl.chess.rules.piecemove.RookMoveRuleSimply;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

public class PieceDefinitionRepository {

    public static final PieceDefinition ROOK_DEFINITION =
            new PieceDefinition(PieceType.Rook, 'R', true,
                    List.of(new RookMoveRuleSimply()),
                    List.of());
    public static final PieceDefinition KNIGHT_DEFINITION =
            new PieceDefinition(PieceType.Knight, 'N', true,
                    List.of(new KnightMoveRuleSimply()),
                    List.of());
    public static final PieceDefinition BISHOP_DEFINITION =
            new PieceDefinition(PieceType.Bishop, 'B', true,
                    List.of(new BishopMoveRuleSimply()),
                    List.of());
    public static final PieceDefinition QUEEN_DEFINITION =
            new PieceDefinition(PieceType.Queen, 'Q', true,
                    List.of(new RookMoveRuleSimply(),
                            new BishopMoveRuleSimply()),
                    List.of());
    public static final PieceDefinition KING_DEFINITION =
            new PieceDefinition(PieceType.King, 'K', false,
                    List.of(new KingMoveRuleSimply()),
                    List.of());
    public static final PieceDefinition PAWN_DEFINITION =
            new PieceDefinition(PieceType.Pawn, 'P', true,
                    List.of(new Pawn1MoveRuleSimply(),
                            new Pawn2MoveRuleSimply(),
                            new PawnTakeMoveRuleSimply()),
                    List.of(
                            new EnPassantRule()
                    ));

    public static Map<PieceType, PieceDefinition> REPOSITORY = Map.of(
            PieceType.Rook, ROOK_DEFINITION,
            PieceType.Knight, KNIGHT_DEFINITION,
            PieceType.Bishop, BISHOP_DEFINITION,
            PieceType.Queen, QUEEN_DEFINITION,
            PieceType.King, KING_DEFINITION,
            PieceType.Pawn, PAWN_DEFINITION
    );

    public static Optional<PieceDefinition> getPieceDefinitionFor(char pieceSign) {
        return REPOSITORY.entrySet().stream()
                .filter(entry -> entry.getValue().getDisplaySign() == Character.toUpperCase(pieceSign))
                .map(Entry::getValue)
                .findAny();
    }
}
