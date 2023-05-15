package Model.board;

import Model.board.*;
import Model.pieces.*;
import Model.player.*;

/* 
import com.chess.engine.Alliance;
import com.chess.engine.pieces.Piece;
import com.chess.engine.player.BlackPlayer;
import com.chess.engine.player.Player;
import com.chess.engine.player.WhitePlayer;
*/
import java.util.*;


public class Board {
    private List<Tile> gameBoard;
    private final Collection<Piece> whitePieces;
    private final Collection<Piece> blackPieces;

    private final WhitePlayer whitePlayer;
    private final BlackPlayer blackPlayer;

    private Player turn;


    public Tile getTile(int tileCoordinate){
        return gameBoard.get(tileCoordinate);
    }




    public Board(Builder builder){
        this.gameBoard = createGameBoard(builder);
        this.whitePieces = calculateActivePieces(this.gameBoard, Alliance.WHITE);
        this.blackPieces = calculateActivePieces(this.gameBoard, Alliance.BLACK);

        Collection<Move> whiteLegalMoves  = calculateLegalMoves(this.whitePieces);
        Collection<Move> blackLegalMoves = calculateLegalMoves(this.blackPieces);

        this.whitePlayer = new WhitePlayer(this, whiteLegalMoves);
        this.blackPlayer = new BlackPlayer(this, blackLegalMoves);

        if (builder.turn == null){
            builder.setTurn(Alliance.WHITE);
        } else {
            this.turn = builder.turn.choosePlayer(this.whitePlayer, this.blackPlayer);
        }

    }

    private Collection<Move> calculateLegalMoves(Collection<Piece> pieces){
        List<Move> legalMoves = new ArrayList<>();
        for (Piece piece : pieces){
            legalMoves.addAll(piece.possibleMoves(this));
        }
        return legalMoves;
    }
    private static Collection<Piece> calculateActivePieces(List<Tile> gameBoard, Alliance alliance){
        List<Piece> activePieces = new ArrayList<>();
        for (Tile tile : gameBoard){
            if(tile.isOccupied()){
                Piece pieceOnTile = tile.getPiece();
                if (pieceOnTile.getPieceAlliance() == alliance){
                    activePieces.add(pieceOnTile);
                }
            }
        }
        return activePieces;
    }

    private static List<Tile> createGameBoard(Builder builder){
        Tile[] tiles = new Tile[BoardUtils.BOARD_SIZE];
        for (int i = 0; i < BoardUtils.BOARD_SIZE; i++){
            tiles[i] = Tile.createTile(i, builder.boardConfig.get(i));
        }
        return List.of(tiles);
    }

    public static Board createDefaultBoard(){
        Builder builder = new Builder();
        builder.setPiece(new Piece(14, Alliance.WHITE, 1, "Rat"));
        builder.setPiece(new Piece(12, Alliance.WHITE, 2, "Cat"));
        builder.setPiece(new Piece(8, Alliance.WHITE, 3, "Dog"));
        builder.setPiece(new Piece(18, Alliance.WHITE, 4, "Wolf"));
        builder.setPiece(new Piece(16, Alliance.WHITE, 5, "Leopard"));
        builder.setPiece(new Piece(6, Alliance.WHITE, 6, "Tiger"));
        builder.setPiece(new Piece(0, Alliance.WHITE, 7, "Lion"));
        builder.setPiece(new Piece(20, Alliance.WHITE, 8, "Elephant"));


        builder.setPiece(new Piece(48, Alliance.BLACK, 1, "Rat"));
        builder.setPiece(new Piece(50, Alliance.BLACK, 2, "Cat"));
        builder.setPiece(new Piece(54, Alliance.BLACK, 3, "Dog"));
        builder.setPiece(new Piece(44, Alliance.BLACK, 4, "Wolf"));
        builder.setPiece(new Piece(46, Alliance.BLACK, 5, "Leopard"));
        builder.setPiece(new Piece(56, Alliance.BLACK, 6, "Tiger"));
        builder.setPiece(new Piece(62, Alliance.BLACK, 7, "Lion"));
        builder.setPiece(new Piece(42, Alliance.BLACK, 8, "Elephant"));
        /*
        add more
         */
        builder.setTurn(Alliance.WHITE);
        return builder.build();
    }

    public Player whitePlayer(){
        return this.whitePlayer;
    }

    public Player blackPlayer(){
        return this.blackPlayer;
    }

    public Player getTurn(){
        return turn;
    }
    /*public Player getOpponent(){
        if (getTurn() == whitePlayer){
            return blackPlayer;
        } else{
            return whitePlayer;
        }
    }

     */
    public Collection<Piece> getWhitePieces(){
        return whitePieces;
    }
    public Collection<Piece> getBlackPieces(){
        return blackPieces;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < BoardUtils.BOARD_SIZE; i++){
            String tileText = this.gameBoard.get(i).toString();
            builder.append(tileText);
            builder.append(" ");
            if ((i + 1) % BoardUtils.TILES_PER_ROW == 0){
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    public static class Builder{
        Map<Integer, Piece> boardConfig;
        Alliance turn;

        public Builder(){
            this.boardConfig = new HashMap<>();
        }

        public Builder setPiece(Piece piece){
            this.boardConfig.put(piece.getPiecePosition(), piece);
            return this;
        }

        /*public Builder setTileType(int coordinate, TileType type){
            this.boardProperties.put(coordinate,type);
            return this;
        }

         */

        public Builder setTurn(Alliance nextTurn){
            this.turn = nextTurn;
            return this;
        }
        public Board build(){
            return new Board(this);
        }
    }
}
