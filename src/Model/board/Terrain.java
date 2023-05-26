package Model.board;

import Model.board.*;
import Model.pieces.*;
import Model.player.*;

import java.awt.*;

public class Terrain {
    private TileType tileType;
    private Alliance alliance;
    public Color grassColor = Color.GRAY;
    public Color waterColor = new Color(116, 204, 244);

    public Terrain(TileType tileType) {
        this.tileType = tileType;
    }

    public Terrain(TileType tileType, Alliance alliance) {
        this.tileType = tileType;
        this.alliance = alliance;
    }

    public enum TileType {
        GRASS, WATER, TRAP, DEN;
    }

    public TileType getTileType() {
        return tileType;
    }

    public Alliance getAlliance() {
        return alliance;
    }

    public boolean isWater() {
        return tileType == TileType.WATER;
    }

    public boolean isDen() {
        return tileType == TileType.DEN;
    }

    public boolean isTrap() {
        return tileType == TileType.TRAP;
    }

    public boolean isGrass() {
        return tileType == TileType.GRASS;
    }

    public Color getTileColor() {
        if (isDen()) {
            return Color.WHITE;

        } else if (isTrap()) {
            return Color.WHITE;

        } else if (isWater()) {
            return waterColor;

        } else if (isGrass()) {
            return grassColor;
        }
        return null;
    }

    public void changeGrassColor() {
        grassColor = Color.GRAY;
    }

    public void changeGrassColorPink() {
        grassColor = new Color(255, 182, 193);
    }
}