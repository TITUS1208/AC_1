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
import java.io.File;
import java.io.FileNotFoundException;
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

    public String tileInfo(int tileCoordinate){
        if (getTile(tileCoordinate).getPiece() == null) {
            return null;
        }
        String piece = getTile(tileCoordinate).getPiece().toString();
        return String.format("%d %s%s", tileCoordinate, getTile(tileCoordinate).getPiece().getPieceAlliance().toString().substring(0,1), piece);
    }

    public ArrayList<Piece> getAllActivePiece(){
        ArrayList<Piece> pieces = new ArrayList<>();
        pieces.addAll(whitePieces);
        pieces.addAll(blackPieces);
        return pieces;
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
            builder.setTurn(Alliance.BLACK);
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
        builder.setTurn(Alliance.BLACK);
        return builder.build();
    }

    public Player whitePlayer(){
        return this.whitePlayer;
    }

    public Player blackPlayer(){
        return this.blackPlayer;
    }
    public Collection<Move> getAllPossibleMoves(){
        Collection<Move> allLegalmoves = this.whitePlayer.getLegalMoves();
        allLegalmoves.addAll(this.blackPlayer.getLegalMoves());
        return allLegalmoves;
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

    public String formatToText(){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < BoardUtils.BOARD_SIZE; i++){
            if (this.gameBoard.get(i).getPiece() != null) {
                String tileText = this.gameBoard.get(i).getPiece().getPieceAlliance().toString().substring(0,1) + this.gameBoard.get(i).getPiece().toString();
                builder.append(tileText);
            } else{
                builder.append("---");
            }
            builder.append(" ");
            if ((i + 1) % BoardUtils.TILES_PER_ROW == 0){
                builder.append("\n");
            }
        }
        return builder.toString();
    }


    public static Board testBoard1(){
        Builder builder = new Builder();
        builder.setPiece(new Piece(36, Alliance.WHITE, 1, "Rat"));
        builder.setPiece(new Piece(12, Alliance.WHITE, 2, "Cat"));
        builder.setPiece(new Piece(9, Alliance.WHITE, 3, "Dog"));
        builder.setPiece(new Piece(18, Alliance.WHITE, 4, "Wolf"));
        builder.setPiece(new Piece(2, Alliance.WHITE, 5, "Leopard"));
        builder.setPiece(new Piece(6, Alliance.WHITE, 6, "Tiger"));
        builder.setPiece(new Piece(16, Alliance.WHITE, 7, "Lion"));
        builder.setPiece(new Piece(58, Alliance.WHITE, 8, "Elephant"));


        builder.setPiece(new Piece(29, Alliance.BLACK, 1, "Rat"));
        builder.setPiece(new Piece(50, Alliance.BLACK, 2, "Cat"));
        builder.setPiece(new Piece(54, Alliance.BLACK, 3, "Dog"));
        builder.setPiece(new Piece(44, Alliance.BLACK, 4, "Wolf"));
        builder.setPiece(new Piece(46, Alliance.BLACK, 5, "Leopard"));
        builder.setPiece(new Piece(56, Alliance.BLACK, 6, "Tiger"));
        builder.setPiece(new Piece(4, Alliance.BLACK, 7, "Lion"));
        builder.setPiece(new Piece(35, Alliance.BLACK, 8, "Elephant"));
        /*
        add more
         */
        builder.setTurn(Alliance.BLACK);
        return builder.build();
    }

    public boolean isGameOver(){
        if (whitePlayer.checkDen() || blackPlayer.checkDen()){
            return true;
        }
        return false;
    }

    public Player getWhitePlayer(){
        return whitePlayer;
    }
    public Player getBlackPlayer(){
        return blackPlayer;
    }

    public static ArrayList<Board> loadBoards(String path){

        ArrayList<Board> boards = new ArrayList<>();
        ArrayList<Integer> indices = new ArrayList<>();
        ArrayList<String> piecesInfo = new ArrayList<>();
        try{
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            //String ally = myReader.nextLine();
            //Alliance turn = ally.equals("WHITE") ? Alliance.WHITE : Alliance.BLACK;
            while (myReader.hasNextLine()) {
                try {
                    int index = Integer.valueOf(myReader.nextLine());
                    indices.add(index);
                    for (int i = 0; i < index; i++) {
                        try {
                            piecesInfo.add(myReader.nextLine());
                        } catch (NoSuchElementException q){
                            BoardUtils.LOAD_ERROR_MESSAGE = "Index length mismatch";
                            return null;
                        }
                    }
                } catch (NumberFormatException e){
                    BoardUtils.LOAD_ERROR_MESSAGE = "Index length mismatch";
                    return null;
                }

            }
        }catch (FileNotFoundException q){
            q.printStackTrace();
        }

        //System.out.println(indices);
        //System.out.println(piecesInfo);
        int sum = 0;
        for (int i : indices){
            sum += i;
        }
        if (sum != piecesInfo.size()) return null;

        int indexSum = 0;

        for (int i = 0; i < indices.size(); i++){
            Builder builder = new Builder();
            int prevIndexSum = indexSum;
            indexSum += indices.get(i);
            for (int j = prevIndexSum; j < indexSum; j++){
                builder.setPiece(textToPiece(piecesInfo.get(j)));
            }
            builder.setTurn((i%2==1) ? Alliance.WHITE : Alliance.BLACK);
            boards.add(builder.build());
        }

        for (Board board : boards){
            if (!board.isValid()) return null;
        }

        return boards;
    }

    public static Piece textToPiece(String data){
        String[] split = data.split(" ");
        int tileCoordinate = Integer.valueOf(split[0]);
        String ally = split[1].substring(0,1);
        String animal = split[1].substring(1,3);

        Alliance alliance = ally.equals("W") ? Alliance.WHITE : Alliance.BLACK;
        int rank = 0;
        String name = null;

        switch (animal){
            case("RA"):
                name = "Rat";
                rank = 1;
                break;
            case("CA"):
                name = "Cat";
                rank = 2;
                break;
            case("DO"):
                name = "Dog";
                rank = 3;
                break;
            case("WO"):
                name = "Wolf";
                rank = 4;
                break;
            case("LE"):
                name = "Leopard";
                rank = 5;
                break;
            case("TI"):
                name = "Tiger";
                rank = 6;
                break;
            case("LI"):
                name = "Lion";
                rank = 7;
                break;
            case("EL"):
                name = "Elephant";
                rank = 8;
                break;
        }

        Piece piece = new Piece(tileCoordinate, alliance, rank, name);


        return piece;
    }

    public boolean isValid(){
        if (getAllActivePiece().size() > 16) {
            BoardUtils.LOAD_ERROR_MESSAGE = "More than 16 pieces on board";
            return false;
        }

        ArrayList<Piece> blackPieces = new ArrayList<>(getBlackPieces());
        ArrayList<Piece> whitePieces = new ArrayList<>(getWhitePieces());

        for (int i = 0; i < blackPieces.size()-1; i++){
            for(int j  = i+1; j < blackPieces.size(); j++){
                if (blackPieces.get(i).getRank() == blackPieces.get(j).getRank()){
                    BoardUtils.LOAD_ERROR_MESSAGE = "Duplicate pieces";
                    return  false;
                }
            }
        }


        for (int i = 0; i < whitePieces.size()-1; i++){
            for(int j  = i+1; j < whitePieces.size(); j++){
                if (whitePieces.get(i).getRank() == whitePieces.get(j).getRank()){
                    BoardUtils.LOAD_ERROR_MESSAGE = "Duplicate pieces";
                    return  false;
                }
            }
        }


        //check pieces in river and dens
        for (Piece piece : getAllActivePiece()){
            if (BoardUtils.IN_WATER(piece.getPiecePosition()) && piece.getRank() != 1){
                BoardUtils.LOAD_ERROR_MESSAGE = "Invalid piece in water";
                return false;
            }

            if (piece.getPiecePosition() == BoardUtils.TOP_DEN && piece.getPieceAlliance() == Alliance.WHITE){
                BoardUtils.LOAD_ERROR_MESSAGE = "Invalid piece in den";
                return false;
            }

            if (piece.getPiecePosition() == BoardUtils.BOTTOM_DEN && piece.getPieceAlliance() == Alliance.BLACK){
                BoardUtils.LOAD_ERROR_MESSAGE = "Invalid piece in den";
                return false;
            }
        }



        return true;
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
