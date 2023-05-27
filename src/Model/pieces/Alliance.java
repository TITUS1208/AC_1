package Model.pieces;

import Model.board.*;
import Model.pieces.*;
import Model.player.*;

public enum Alliance {
    BLACK{
        public Player choosePlayer(WhitePlayer whitePlayer, BlackPlayer blackPlayer){
            return blackPlayer;
        }
        public boolean isBlack(){return true;}
        public boolean isWhite(){return false;}
        public String toString(){
            return "BLACK";
        }
    },
    WHITE{
        public Player choosePlayer(WhitePlayer whitePlayer, BlackPlayer blackPlayer){
            return whitePlayer;
        }
        public boolean isBlack(){return false;}
        public boolean isWhite(){return true;}
        public String toString(){
            return "WHITE";
        }
    };

    public abstract Player choosePlayer(WhitePlayer whitePlayer, BlackPlayer blackPlayer);

}
