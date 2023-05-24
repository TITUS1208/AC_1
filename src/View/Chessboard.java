package View;

import Model.AudioPlayer;
import Model.board.*;
import Model.pieces.Piece;
import Model.player.MoveTransition;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static javax.swing.SwingUtilities.isLeftMouseButton;
import static javax.swing.SwingUtilities.isRightMouseButton;

public class Chessboard extends JPanel {

    private Tile selectedTile;
    private Tile destinationTile;
    private Piece selectedPiece;
    private boolean highlight;
    private Board chessBoard;
    private JFrame frame;
    private JFrame beginFrame;
    private ArrayList<TilePanel> boardTiles;
    private UsernamePassword username_pw;
    private ArrayList<Board> boardHistory;
    private ArrayList<String> moveHistory;
    private final Board initialBoard;

    public Chessboard(JFrame frame, JFrame beginFrame, UsernamePassword username_pw) {
        setLayout(new GridLayout(9, 7));
        boardHistory = new ArrayList<>();
        moveHistory = new ArrayList<>();
        this.frame = frame;
        this.beginFrame = beginFrame;
        this.username_pw = username_pw;

        //Board initialization
        chessBoard = Board.createDefaultBoard();
        //chessBoard = Board.testBoard1();
        initialBoard = chessBoard;
        boardHistory.add(chessBoard);

        boardTiles = new ArrayList<>();
        for (int i = 0; i < BoardUtils.BOARD_SIZE; i++) {
            TilePanel tile = new TilePanel(this, i);
            boardTiles.add(tile);
            add(tile);
        }
        this.highlight = false;
        validate();
    }

    public void loadPreviousBoard(ArrayList<Board> boardHistory){
        try {
            chessBoard = boardHistory.get(boardHistory.size() - 2);
            boardHistory.remove(boardHistory.size()-1);
            System.out.println("load");
            drawBoard(chessBoard);
        } catch (IndexOutOfBoundsException e){
            System.out.println("cannot load board");
        }

    }

    public ArrayList<Board> getBoardHistory(){
        return boardHistory;
    }

    public ArrayList<String> getMoveHistory(){
        return moveHistory;
    }

    public Board getInitialBoard(){
        return initialBoard;
    }

    public void drawBoard(Board board) {
        removeAll();
        for (TilePanel tilePanel : boardTiles) {
            tilePanel.drawTile(board);
            add(tilePanel);
        }
        validate();
        repaint();
    }

    public Board getBoard(){
        return chessBoard;
    }

    public void drawBoard(Board board, ArrayList<Integer> highlights) {
        removeAll();
        for (TilePanel tilePanel : boardTiles) {
            tilePanel.drawTile(board, highlights);
            add(tilePanel);
        }
        validate();
        repaint();
    }


    private class TilePanel extends JPanel {
        private int tileNum;

        public TilePanel(Chessboard chessboard, int tileNum) {
            this.tileNum = tileNum;
            setLayout(new GridLayout());
            setPreferredSize(BoardUtils.TILE_PANEL_DIMENSION);
            assignTileColor();
            assignPieceOnTile(chessBoard);
            addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // boolean gameOver = false;
                    if (isLeftMouseButton(e)) {
                        AudioPlayer.playSoundEffect("resource\\Audio\\click.wav");
                        if (selectedTile == null) {
                            // first click --> select piece
                            selectedTile = chessBoard.getTile(tileNum);
                            selectedPiece = selectedTile.getPiece();
                            if (selectedPiece == null) {
                                selectedTile = null;
                                System.out.println("Cannot select empty tile");
                                highlight = false;
                            } else if (selectedPiece.getPieceAlliance() != chessBoard.getTurn().getAlliance()) {
                                selectedTile = null;
                                System.out.println("Cannot select enemy piece");
                                highlight = false;
                            } else {
                                highlight = true;
                            }
                        } else {
                            // successful move
                            destinationTile = chessBoard.getTile(tileNum);
                            boolean isPossibleMove = false;
                            for (int i : selectedPiece.getMoves(chessBoard)) {
                                if (tileNum == i)
                                    isPossibleMove = true;
                            }
//need moveHistory, board
                            if (isPossibleMove) {
                                Move move = Move.CreateMove.createMove(chessBoard, selectedTile.getTileCoor(),
                                        destinationTile.getTileCoor());
                                MoveTransition moveTransition = chessBoard.getTurn().makeMove(move);
                                if (moveTransition.getMoveStatus().isDone()) {
                                    chessBoard = moveTransition.getBoard();
                                    System.out.println(moveCommand());

                                    boardHistory.add(chessBoard);
                                    moveHistory.add(moveCommand());
                                }
                            }

                            // TODO move log
                            clearSelection();
                        }

                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                drawBoard(chessBoard);

                                // TODO check game status
                                if (chessBoard.isGameOver()) {
                                    // System.out.println("Game Over");
                                    String winner = "";
                                    if (chessBoard.getWhitePlayer().checkDen()) {
                                        // System.out.println("Black wins");
                                        winner = "player1";
                                    } else if (chessBoard.getBlackPlayer().checkDen()) {
                                        // System.out.println("White wins");
                                        winner = "player2";
                                    }

                                    BeginFrame beginFrame = null;
                                    try {
                                        beginFrame = new BeginFrame(Constant.BEGIN_FRAME_WIDTH,
                                                Constant.BEGIN_FRAME_HEIGHT);
                                    } catch (FileNotFoundException ex) {
                                        throw new RuntimeException(ex);
                                    }

                                    while (true) {
                                        int result = JOptionPane.showConfirmDialog(null,
                                                String.format("%s wins, congratulation!", winner)
                                                        + "\n Do you wanna continue?",
                                                "Jungle_CS109", JOptionPane.YES_NO_OPTION,
                                                JOptionPane.QUESTION_MESSAGE);

                                        if (result == JOptionPane.YES_OPTION) {
                                            frame.dispose();
                                            new MainFrame(Constant.MAIN_FRAME_WIDTH, Constant.MAIN_FRAME_HEIGHT,
                                                    Constant.JUNGLE_ICON, beginFrame, username_pw);

                                            break;
                                        } else if (result == JOptionPane.NO_OPTION) {
                                            frame.dispose();
                                            beginFrame.setVisible(true);
                                            break;
                                        } else {
                                            continue;
                                        }
                                    }

                                }
                                // System.out.println(chessBoard);
                            }
                        });

                        /*
                         * public void addRestartButton() {
                         * restartButton = new JButton("RESTART");
                         * restartButton.setFont(new Font("Monaco", Font.BOLD, 17));
                         * restartButton.setFocusable(false);
                         * 
                         * restartButton.addActionListener(e -> {
                         * System.out.println("restartButton being clicked");
                         * int temp = JOptionPane.showConfirmDialog(this, "Are you sure to restart?");
                         * if (temp == JOptionPane.YES_OPTION) {
                         * setVisible(false);
                         * new MainFrame(frameWidth, frameHeight, jungleIcon);
                         * }
                         * });
                         * add(restartButton);
                         * }
                         */

                    } else if (isRightMouseButton(e)) {
                        selectedTile = null;
                        destinationTile = null;
                        selectedPiece = null;
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });

        }

        public void clearSelection() {
            selectedTile = null;
            selectedPiece = null;
            destinationTile = null;
        }

        public void highlightLegalMove(Board board) {
            if (highlight && selectedPiece != null) {
                for (int position : selectedPiece.getMoves(board)) {
                    if (position == tileNum) {
                        setBackground(Color.ORANGE);
                    }
                }
            }
        }

        public void drawTile(Board board) {
            assignTileColor();
            highlightLegalMove(board);
            assignPieceOnTile(board);
            validate();
            repaint();
        }

        public void drawTile(Board board, ArrayList<Integer> highlights) {
            assignTileColor(highlights);
            assignPieceOnTile(board);
            validate();
            repaint();
        }

        private void assignPieceOnTile(Board board) {
            // System.out.println(board.getTile(this.tileNum).getPiece().getPieceAlliance().toString());
            this.removeAll();
            String pieceImagePath = BoardUtils.getSkinDir();
            if (board.getTile(tileNum).isOccupied()) {
                try {
                    String path = pieceImagePath +
                            board.getTile(this.tileNum).getPiece().getPieceAlliance().toString().substring(0, 1) +
                            board.getTile(this.tileNum).getPiece().toString() + ".png";
                    // System.out.println(path);
                    BufferedImage image = ImageIO.read(new File(path));

                    Image img = new ImageIcon(image).getImage();
                    img = img.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                    add(new JLabel(new ImageIcon(img)));
                    // add(new JLabel(new ImageIcon(image)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void assignTileColor() {
            Terrain terrain = BoardUtils.TERRAIN_BOARD.get(tileNum);
            setBackground(tileNum % 2 == 0 ? terrain.getTileColor().brighter() : terrain.getTileColor().darker());
        }

        private void assignTileColor(ArrayList<Integer> highlights) {
            Terrain terrain = BoardUtils.TERRAIN_BOARD.get(tileNum);
            setBackground(tileNum % 2 == 0 ? terrain.getTileColor().brighter() : terrain.getTileColor().darker());

            boolean isHighlighted = false;
            for (int i : highlights) {
                if (tileNum == i) {
                    isHighlighted = true;
                }
            }
            if (isHighlighted) {
                System.out.println("ORANGE");
                setBackground(Color.ORANGE);
            }
        }

        public String moveCommand() {
            return String.format("%s %d %d",
                    selectedPiece.getPieceAlliance().toString().substring(0, 1)
                            + selectedPiece.getName().substring(0, 2).toUpperCase(),
                    selectedTile.getTileCoor(), destinationTile.getTileCoor());
        }
    }
}
