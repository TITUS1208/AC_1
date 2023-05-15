package Model.player;

import Model.board.*;
import Model.pieces.*;
import Model.player.*;

import java.util.Collection;

public class WhitePlayer extends Player {
    protected final Alliance ally = Alliance.WHITE;
    protected final Alliance enemy = Alliance.BLACK;

    public WhitePlayer(Board board, Collection<Move> legalMoves){
        super(board, legalMoves);
        this.denIsTaken = checkDen();
    }

    @Override
    public Collection<Piece> getActivePieces(){
        return this.board.getWhitePieces();

    }

    @Override
    public Alliance getAlliance(){return ally;}

    @Override
    public Player getOpponent(){return  this.board.blackPlayer();}

    @Override
    public boolean checkDen(){
        if (this.board.getTile(BoardUtils.TOP_DEN).getPiece() != null) {
            if (this.board.getTile(BoardUtils.TOP_DEN).getPiece().getPieceAlliance() == enemy) {
                return true;
            }
        }
        return false;
    }
}
