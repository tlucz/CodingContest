package com.tl.chess.pieces;

import com.tl.chess.rules.piecemove.BishopMoveRuleSimply;
import com.tl.chess.rules.piecemove.KingMoveRuleSimply;
import com.tl.chess.rules.piecemove.KnightMoveRuleSimply;
import com.tl.chess.rules.piecemove.Pawn1MoveRuleSimply;
import com.tl.chess.rules.piecemove.Pawn2MoveRuleSimply;
import com.tl.chess.rules.piecemove.PawnTakeMoveRuleSimply;
import com.tl.chess.rules.piecemove.RookMoveRuleSimply;
import java.util.List;

public class PieceDefnitionRepository {
    public static PieceDefinition ROOK_DEFINITION = new PieceDefinition('R', true, List.of(new RookMoveRuleSimply()));
    public static PieceDefinition KNIGHT_DEFINITION = new PieceDefinition('N', true,
            List.of(new KnightMoveRuleSimply()));
    public static PieceDefinition BISHOP_DEFINITION = new PieceDefinition('B', true, List.of(new BishopMoveRuleSimply()));
    public static PieceDefinition QUEEN_DEFINITION = new PieceDefinition('Q', true, List.of(new RookMoveRuleSimply(),
            new BishopMoveRuleSimply()));
    public static PieceDefinition KING_DEFINITION = new PieceDefinition('K', false, List.of(new KingMoveRuleSimply()));
    public static PieceDefinition PAWN_DEFINITION = new PieceDefinition('P', true, List.of(
            new Pawn1MoveRuleSimply(),
            new Pawn2MoveRuleSimply(),
            new PawnTakeMoveRuleSimply()
    ));
}
