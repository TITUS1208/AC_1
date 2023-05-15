package Model.board;

import Model.board.*;
import Model.pieces.*;
import Model.player.*;

public class Terrain {
    private TileType tileType;
    private Alliance alliance;
    public Terrain(TileType tileType){
        this.tileType = tileType;
    }

    public Terrain(TileType tileType, Alliance alliance){
        this.tileType = tileType;
        this.alliance = alliance;
    }


    public enum TileType {
        GRASS, WATER, TRAP, DEN;
    }

    public TileType getTileType(){
        return tileType;
    }

    public Alliance getAlliance(){
        return alliance;
    }

    public boolean isWater(){
        return tileType == TileType.WATER;
    }

    public boolean isDen(){
        return tileType == TileType.DEN;
    }

    public boolean isTrap(){
        return tileType == TileType.TRAP;
    }
}
