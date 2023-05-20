package Model.board;

import Model.board.*;
import Model.pieces.*;
import Model.player.*;

public abstract class Move {

    protected final Board board;
    protected final int destinationCoordinate;
    protected final Piece movingPiece;
    public static final Move NULL_MOVE = new NullMove();

    public boolean isAttacking(){
        return false;
    }
    public Piece getAttackedPiece(){
        return null;
    }

    public boolean equals(Object other){
        if (!(other instanceof Move)){
            return false;
        }
        Move otherMove = (Move) other;
        return getCurrentCoordinate() == otherMove.getCurrentCoordinate() && getDestinationCoordinate() == otherMove.getDestinationCoordinate()
                && getMovingPiece().equals(otherMove.getMovingPiece());
    }


    private Move(final Board board,
                 final Piece movingPiece,
                 final int destinationCoordinate) {
        this.board = board;
        this.destinationCoordinate = destinationCoordinate;
        this.movingPiece = movingPiece;

    }
    public int getCurrentCoordinate(){return movingPiece.getPiecePosition();}
    public int getDestinationCoordinate(){
        return destinationCoordinate;
    }
    public Piece getMovingPiece(){return movingPiece;}

    public Board execute(){
        Board.Builder builder = new Board.Builder();
        for (Piece piece : this.board.getTurn().getActivePieces()){
            if (!this.movingPiece.equals(piece)) {
                builder.setPiece(piece);
            }
        }

        for (Piece piece : this.board.getTurn().getOpponent().getActivePieces()){
            builder.setPiece(piece);
        }

        builder.setPiece(this.movingPiece.movePiece(this));
        builder.setTurn(this.board.getTurn().getOpponent().getAlliance());
        return builder.build();
    }








    public static final class NormalMove extends Move{
        public NormalMove(Board board, Piece movingPiece, int destinationCoordinate){
            super(board,movingPiece, destinationCoordinate);
        }



    }

    public static final class NullMove extends Move{
        public NullMove(){
            super(null, null,-1);
        }

        @Override
        public Board execute(){
            throw new RuntimeException("Cannot execute null move");
        }
    }




    public static final class AttackMove extends Move{

        Piece attackedPiece;
        public AttackMove(Board board, Piece movingPiece, int destinationCoordinate, Piece attackedPiece){
            super(board,movingPiece, destinationCoordinate);
            this.attackedPiece = attackedPiece;
        }

        /*@Override
        public Board execute(){
            return null;
        }

         */

        @Override
        public boolean isAttacking(){
            return true;
        }

        @Override
        public Piece getAttackedPiece(){
            return this.attackedPiece;
        }

        @Override
        public boolean equals(Object other){
            if (!(other instanceof AttackMove)){
                return false;
            }
            Move otherMove = (Move) other;
            return super.equals(otherMove) && getAttackedPiece().equals(otherMove.getAttackedPiece());
        }
    }

    public static class CreateMove{
        public static Move createMove(Board board, int currentCoordinate, int destinationCoordinate){
            for (Move move : board.getAllPossibleMoves()){
                if (move.getDestinationCoordinate() == destinationCoordinate && move.getCurrentCoordinate() == currentCoordinate){
                    return move;
                }
            }
            return NULL_MOVE;
        }
    }


}

