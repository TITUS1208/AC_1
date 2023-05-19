package Model.board;

import Model.board.*;
import Model.pieces.*;
import Model.player.*;

import java.awt.*;

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
    public boolean isGrass(){ return tileType == TileType.GRASS;}

    public Color getTileColor(){
        if (isDen()){
            return Color.YELLOW;
        } else if (isTrap()){
            return Color.PINK;
        } else if (isWater()){
            return Color.CYAN;
        } else if (isGrass()){
            return Color.GREEN;
        }
        return null;
    }
}
