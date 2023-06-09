package Model.player;

import Model.board.*;
import Model.pieces.*;
import Model.player.*;

import java.util.Collection;
import java.util.List;

public abstract class Player {
    protected final Board board;
    protected final Collection<Move> legalMoves;
    protected boolean denIsTaken;
    protected Alliance ally;
    protected Alliance enemy;


    Player(final Board board, final Collection<Move> legalMoves){
        this.board = board;
        this.legalMoves = legalMoves;
        this.denIsTaken = checkDen();
    }

    public abstract Collection<Piece> getActivePieces();
    public abstract Alliance getAlliance();
    public abstract Player getOpponent();
    public abstract boolean checkDen();

    public boolean isMoveLegal(final Move move){
        return this.legalMoves.contains(move);
    }
    public Collection<Move> getLegalMoves(){
        return legalMoves;
    }

    //TODO
    public MoveTransition makeMove(Move move){
        if (!this.legalMoves.contains(move)) {
            return new MoveTransition(this.board, this.board, move, MoveStatus.ILLEGAL_MOVE);
        }
        final Board transitionedBoard = move.execute();
        return new MoveTransition(this.board, transitionedBoard, move, MoveStatus.DONE);

    }






}
