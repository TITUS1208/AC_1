package Model.player;

import Model.board.*;
import Model.pieces.*;
import Model.player.*;
import java.util.Collection;



public class BlackPlayer extends Player{
    protected final Alliance ally = Alliance.BLACK;
    protected final Alliance enemy = Alliance.WHITE;
    public BlackPlayer(Board board, Collection<Move> legalMoves){
        super(board, legalMoves);
        this.denIsTaken = checkDen();
    }

    @Override
    public Collection<Piece> getActivePieces(){
        return this.board.getBlackPieces();
    }

    @Override
    public Alliance getAlliance(){return ally;}

    @Override
    public Player getOpponent(){return this.board.whitePlayer();}
    @Override
    public boolean checkDen(){
        if (this.board.getTile(BoardUtils.TOP_DEN).getPiece() != null) {
            if (this.board.getTile(BoardUtils.BOTTOM_DEN).getPiece().getPieceAlliance() == enemy) {
                return true;
            }
        }
        return false;
    }
}
