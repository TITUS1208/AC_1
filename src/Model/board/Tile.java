package Model.board;


import Model.board.*;
import Model.pieces.*;
import Model.player.*;

import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

public abstract class Tile {
    protected final int tileCoor;

    private static final Map<Integer, EmptyTile> EMPTY_TILE_CACHE = createAllPossibleEmptyTile();

    protected Alliance tileAlliance;

    private static Map<Integer, EmptyTile> createAllPossibleEmptyTile(){
        final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();
        for (int i = 0; i < 63; i++){
            emptyTileMap.put(i, new EmptyTile(i));
        }
        return Collections.unmodifiableMap(emptyTileMap);
    }

    public static Tile createTile(final int coordinate, final Piece piece){
        return piece != null ? new OccupiedTile(coordinate,piece) : EMPTY_TILE_CACHE.get(coordinate);
    }

    private Tile(int tileCoor){
        this.tileCoor = tileCoor;
    }

    public abstract boolean isOccupied();

    public abstract Piece getPiece();
    public int getTileCoor(){return tileCoor;}

    public static final class EmptyTile extends Tile{
        private EmptyTile(final int coordinate){
            super(coordinate);
        }

        @Override
        public boolean isOccupied(){
            return false;
        }

        @Override
        public Piece getPiece(){
            return null;
        }

        @Override
        public String toString(){
            return "--";
        }
    }

    public static final class OccupiedTile extends Tile{
        private final Piece piece;
        private OccupiedTile(int coordinate, Piece piece){
            super(coordinate);
            this.piece = piece;
        }

        @Override
        public boolean isOccupied(){
            return true;
        }

        @Override
        public Piece getPiece(){
            return piece;
        }

        @Override
        public String toString(){
            return getPiece().isBlack() ? getPiece().toString().toLowerCase() :
                    getPiece().toString().toUpperCase();
        }

    }
}
