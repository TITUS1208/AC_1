package View;

import Model.board.BoardUtils;
import Model.board.Terrain;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Table {
    private JFrame gameFrame;
    private BoardPanel boardPanel;

    public Table(){
        this.gameFrame = new JFrame("Animal chess");
        this.boardPanel = new BoardPanel();
        this.gameFrame.setSize(BoardUtils.OUTER_DIMENSION);
        this.gameFrame.add(this.boardPanel, BorderLayout.CENTER);
        this.gameFrame.setVisible(true);

    }


    private class BoardPanel extends JPanel{
        ArrayList<TilePanel> boardTiles;

        BoardPanel() {
            super(new GridLayout(9, 7));
            boardTiles = new ArrayList<>();
            for (int i = 0; i < BoardUtils.BOARD_SIZE; i++) {
                TilePanel tile = new TilePanel(this, i);
                boardTiles.add(tile);
                add(tile);
            }
            setVisible(true);
            setPreferredSize(BoardUtils.CHESS_BOARD_DIMENSION);
            validate();
        }
    }

    private class TilePanel extends JPanel{
        private int tileNum;
        public TilePanel (BoardPanel chessboard, int tileNum){
            super(new GridLayout());
            this.tileNum = tileNum;
            setPreferredSize(BoardUtils.TILE_PANEL_DIMENSION);
            assignTileColor();
            validate();
        }

        private void assignTileColor(){
            Terrain terrain = BoardUtils.TERRAIN_BOARD.get(tileNum);
            setBackground(tileNum % 2 == 0 ? terrain.getTileColor().brighter() : terrain.getTileColor().darker());


            /*
            if (!terrain.isGrass()){
                setBackground(terrain.getTileColor());
            } else {
                setBackground(tileNum % 2 == 0 ? Color.GRAY : Color.DARK_GRAY);
            }
            */


        }
    }
}
