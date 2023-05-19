package View;

import Model.board.Board;
import Model.board.BoardUtils;
import Model.board.Tile;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Chessboard extends JPanel {
    /*ArrayList<TilePanel> boardTiles;
    public Chessboard(){
        super(new GridLayout(7,9));
        boardTiles = new ArrayList<>();
        for (int i = 0; i < BoardUtils.BOARD_SIZE; i++){
            TilePanel tile = new TilePanel(this, i);
            boardTiles.add(tile);
            add(tile);
        }
        setVisible(true);
        setPreferredSize(BoardUtils.CHESS_BOARD_DIMENSION);
        validate();
    }


    /*public class TilePanel extends JPanel{
        private int tileNum;
        public TilePanel (Chessboard chessboard, int tileNum){
            super(new GridLayout());
            this.tileNum = tileNum;
            setPreferredSize(BoardUtils.TILE_PANEL_DIMENSION);
            assignTileColor();
            validate();
        }

        private void assignTileColor(){
            setBackground(Color.green);
        }
    }

     */

}


