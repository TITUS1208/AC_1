package Model.player;

import Model.board.*;
import Model.pieces.*;
import Model.player.*;

public class MoveTransition {

    private final Board board;
    private final Board transitionBoard;
    private final Move move;
    private final MoveStatus moveStatus;

    public MoveTransition(Board board, Board newBoard, Move move, MoveStatus moveStatus){
        this.board = board;
        this.transitionBoard = newBoard;
        this.move = move;
        this.moveStatus = moveStatus;

    }

    public MoveStatus getMoveStatus(){
        return moveStatus;
    }

    public Board getBoard(){
        return this.transitionBoard;
    }

    public Board getOriginalBoard(){
        return board;
    }

}
