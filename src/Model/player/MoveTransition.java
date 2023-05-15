package Model.player;

import Model.board.*;
import Model.pieces.*;
import Model.player.*;

public class MoveTransition {
    private final Board transitionBoard;
    private final Move move;
    private final MoveStatus moveStatus;

    public MoveTransition(Board board, Move move, MoveStatus moveStatus){
        this.transitionBoard = board;
        this.move = move;
        this.moveStatus = moveStatus;

    }
}
