package Model.board;

import Model.board.*;
import Model.pieces.*;
import Model.player.*;

public abstract class Move {

    protected final Board board;
    protected final int destinationCoordinate;
    protected final Piece movingPiece;


    private Move(final Board board,
                 final Piece movingPiece,
                 final int destinationCoordinate) {
        this.board = board;
        this.destinationCoordinate = destinationCoordinate;
        this.movingPiece = movingPiece;

    }

    public int getDestinationCoordinate(){
        return destinationCoordinate;
    }

    public abstract Board execute();




    public static final class NormalMove extends Move{
        public NormalMove(Board board, Piece movingPiece, int destinationCoordinate){
            super(board,movingPiece, destinationCoordinate);
        }

        @Override
        public Board execute(){
            Board.Builder builder = new Board.Builder();
            for (Piece piece : this.board.getTurn().getActivePieces()){
                if (!this.movingPiece.equals(movingPiece)) {
                    builder.setPiece(piece);
                }
            }

            for (Piece piece : this.board.getTurn().getOpponent().getActivePieces()){
                builder.setPiece(piece);
            }
            builder.setPiece(null);
            builder.setTurn(this.board.getTurn().getOpponent().getAlliance());
            return builder.build();
        }
    }




    public static final class AttackMove extends Move{
        public AttackMove(Board board, Piece movingPiece, int destinationCoordinate){
            super(board,movingPiece, destinationCoordinate);
        }

        @Override
        public Board execute(){
            return null;
        }
    }
}