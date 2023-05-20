package View;

import Model.board.Board;
import Model.board.BoardUtils;
import Model.board.Terrain;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Table {

    private Board chessBoard;
    private JFrame gameFrame;
    private BoardPanel boardPanel;

    public Table(){
        this.chessBoard = Board.createDefaultBoard();
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
            assignPieceOnTile(chessBoard);
            validate();
        }

        private void assignPieceOnTile(Board board){
            //System.out.println(board.getTile(this.tileNum).getPiece().getPieceAlliance().toString());
            this.removeAll();
            String pieceImagePath = BoardUtils.getSkinDir();
            if (board.getTile(tileNum).isOccupied()){
                try {
                    String path = pieceImagePath +
                            board.getTile(this.tileNum).getPiece().getPieceAlliance().toString().substring(0,1) +
                            board.getTile(this.tileNum).getPiece().toString() + ".png";

                    System.out.println(path);

                    BufferedImage image = ImageIO.read(new File(path));

                    Image img = new ImageIcon(image).getImage();
                    img = img.getScaledInstance(80,80, Image.SCALE_SMOOTH);

                    add(new JLabel(new ImageIcon(img)));
                    //add(new JLabel(new ImageIcon(image)));
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
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
