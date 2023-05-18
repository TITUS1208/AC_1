package Model.board;

import Model.board.*;
import Model.pieces.*;
import Model.player.*;
import java.util.List;


public class BoardUtils {
    public static final int BOARD_SIZE = 63;
    public static final int TILES_PER_ROW = 7;
    public static final int[] WATER = {22, 23, 29, 30, 36, 37, 25, 26, 32, 33, 39, 40};
    public static final int[] LEFT_WATER = {22, 23, 29, 30, 36, 37};
    public static final int[] RIGHT_WATER = {25, 26, 32, 33, 39, 40};
    public static final int[] TOP_TRAP = {2, 4, 10};
    public static final int[] BOTTOM_TRAP = {52, 58, 60};
    public static final int TOP_DEN = 3;
    public static final int BOTTOM_DEN = 59;
    public static final List<Terrain> TERRAIN_BOARD = createTerrainBoard();







    public static boolean IN_LEFT_WATER(int position){
        for(int i : LEFT_WATER){
            if (i == position) {
                System.out.println("Piece in left river (BoardUtils 30)");
                return true;
            }
        }
        return false;
    }

    public static boolean IN_RIGHT_WATER(int position){
        for(int i : RIGHT_WATER){
            if (i == position) {
                System.out.println("Piece in right river (BoardUtils 40)");
                return true;
            }
        }
        return false;
    }

    public static boolean PIECE_IN_TILES(Board board, int[] positions){
        for(int i : positions){
            if (board.getTile(i).getPiece() != null){
                System.out.println("Piece in river (BoardUtils 50)");
                return true;
            }
        }
        return false;
    }

    public static List<Terrain> createTerrainBoard(){
        int[] leftWater = {22,23,29,30};
        Terrain[] terrains = new Terrain[BoardUtils.BOARD_SIZE];
        for (int i = 0; i < BoardUtils.BOARD_SIZE; i++){
            terrains[i] = new Terrain(Terrain.TileType.GRASS);
        }

        //left river
        for (int i : BoardUtils.LEFT_WATER){
            terrains[i] = new Terrain(Terrain.TileType.WATER);
        }
        //right river
        for (int i : BoardUtils.RIGHT_WATER){
            terrains[i] = new Terrain(Terrain.TileType.WATER);
        }

        //top side
        //den
        terrains[BoardUtils.TOP_DEN] = new Terrain(Terrain.TileType.DEN, Alliance.WHITE);
        //traps
        for (int i : BoardUtils.TOP_TRAP){
            terrains[i] = new Terrain(Terrain.TileType.TRAP, Alliance.WHITE);
        }

        //bottom side
        //den
        terrains[BoardUtils.BOTTOM_DEN] = new Terrain(Terrain.TileType.DEN, Alliance.BLACK);
        //traps
        for (int i : BoardUtils.BOTTOM_TRAP){
            terrains[i] = new Terrain(Terrain.TileType.TRAP, Alliance.BLACK);
        }
        return List.of(terrains);
    }
}
