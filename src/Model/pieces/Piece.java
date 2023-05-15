package Model.pieces;

import java.util.ArrayList;
import java.util.List;

import Model.board.*;
import Model.pieces.*;
import Model.player.*;

public class Piece {
    private final int position;
    private final Alliance alliance;
    private final int rank;
    private String name;
    private static int[] MOVE_OFFSET = {-1, 1, -7, 7};


    public Piece(final int position, final Alliance alliance, int rank, String name){
        this.position  = position;
        this.alliance = alliance;
        this.rank = rank;
        this.name = name;
    }

    public List<Move> possibleMoves(final Board board){
        int candidateMoveCoordinate;
        List<Move> legalMoves = new ArrayList<>();
        for (int currentOffset : MOVE_OFFSET){
            candidateMoveCoordinate = this.position + currentOffset;

            //might have to move
            if (currentOffset == 1 || currentOffset == -1){
                int column = this.position%BoardUtils.TILES_PER_ROW + currentOffset;
                if (column < 0 || column > 6){
                    continue;
                }
            }

            //check rank

            //check exceptions

            //check water

            //check if the tile is empty
            if (candidateMoveCoordinate >= 0 && candidateMoveCoordinate < 63){
                Tile candidateTile = board.getTile(candidateMoveCoordinate);
                Terrain candidateTerrain = BoardUtils.TERRAIN_BOARD.get(candidateMoveCoordinate);



                boolean isValid = false;
                if(candidateTile.getPiece() == null){ ////////////NO PIECE IN THE TILE//////////////////
                    if (rank != 1 && candidateTerrain.isWater()){
                        continue;
                    }
                    if (candidateTerrain.isDen() && this.getPieceAlliance() == candidateTerrain.getAlliance()){
                        continue;
                    }
                    //legalMoves.add(new Move());
                    isValid = true;
                    legalMoves.add(new Move.NormalMove(board, board.getTile(this.position).getPiece(), candidateMoveCoordinate));


                } else{ ///////////////PIECE IN THE TILE////////////////
                    Alliance destinationTilePieceAlliance = candidateTile.getPiece().getPieceAlliance();
                    Piece pieceOnDestination = candidateTile.getPiece();

                    if (this.alliance != destinationTilePieceAlliance){
                        if(this.getRank() == 1){ //mouse
                            if(mouseValidMove(pieceOnDestination)){
                                //legalMoves.add(new Move());
                                isValid = true;
                            }
                        } else if (this.getRank() == 6 || this.getRank() == 7){ //tiger and lion
                            if(lionValidMove(board, currentOffset,position)){
                                //legalMoves.add(new Move());
                                isValid = true;
                            }
                        } else{
                            if(generalValidMove(board, currentOffset, position)){
                                //legalMoves.add(new Move());
                                isValid = true;
                            }
                        }


                    }

                }
            }


        }
        return legalMoves;
    }

    private boolean mouseValidMove(Piece pieceOnDestination){
        if (!inWater() && pieceOnDestination.getRank() == 8){
            return true;
        } else if (pieceOnDestination.inTrap()){
            return true;
        }
        return false;
    }

    private boolean lionValidMove(Board board, int currentOffset, int position){
        int counter = 1;
        int candidatePosition = position + currentOffset;


        ///////////////////check if there's a mouse in the water//////////////////////////
        if (BoardUtils.TERRAIN_BOARD.get(candidatePosition).isWater()){
            if(BoardUtils.IN_LEFT_WATER(candidatePosition)){
                //check mouse in left water
                if(BoardUtils.PIECE_IN_TILES(board, BoardUtils.LEFT_WATER)){
                    return false;
                }
            }
            if(BoardUtils.IN_RIGHT_WATER(candidatePosition)){
                //check mouse in right water
                if(BoardUtils.PIECE_IN_TILES(board, BoardUtils.RIGHT_WATER)){
                    return false;
                }
            }
        }

        ///////////////////jump across water////////////////////////
        while(BoardUtils.TERRAIN_BOARD.get(candidatePosition).isWater()){
            counter++;
            currentOffset *= counter;
            candidatePosition = position + currentOffset;
        }

        if (this.getRank() >= board.getTile(candidatePosition).getPiece().getRank()){
            return true;
        } else if (board.getTile(candidatePosition).getPiece().inTrap()){
            return true;
        }
        return false;
    }

    private boolean generalValidMove(Board board, int currentOffset, int position){
        Tile candidateTile = board.getTile(position+currentOffset);
        Terrain candidateTerrain = BoardUtils.TERRAIN_BOARD.get(position+currentOffset);
        if (!candidateTerrain.isWater()){
            if(candidateTile.getPiece().inTrap()){
                return true;
            }
            if (rank == 8){
                if (candidateTile.getPiece().getRank() != 1){
                    return true;
                }
            } else{
                if (rank >= candidateTile.getPiece().getRank()){
                    return true;
                }
            }
        }
        return false;
    }


    public Alliance getPieceAlliance(){
        return alliance;
    }

    public int getPiecePosition(){
        return position;
    }

    public String getName(){
        return name;
    }
    public int getRank(){return rank;}

    public boolean isBlack(){
        if (alliance == Alliance.BLACK) return true;
        return false;
    }

    public boolean inWater(){
        for (int i : BoardUtils.WATER){
            if (i == position){
                return true;
            }
        }
        return false;
    }

    public boolean inTrap(){
        Terrain terrain = BoardUtils.TERRAIN_BOARD.get(position);
        if ( terrain.getTileType() == Terrain.TileType.TRAP){
            if (this.alliance != terrain.getAlliance()){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString(){
        return String.valueOf(getName().charAt(0));
    }
}
/*
- not abstract class
- possibleMoves
    - check (three cases: rat, tiger, others)
        - check tile --> empty,taken by enemy/ally, rank)
        - row and column exclusions (for river)
    - return arrays of legal moves of type Move
 */