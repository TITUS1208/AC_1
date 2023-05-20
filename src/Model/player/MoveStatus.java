package Model.player;

import Model.board.*;
import Model.pieces.*;
import Model.player.*;

public enum MoveStatus {
    DONE{
        @Override
        public boolean isDone(){
            return true;
        }
    },
    ILLEGAL_MOVE{
        @Override
        public boolean isDone(){
            return false;
        }
    };
    public abstract boolean isDone();
}
