import javax.swing.SwingUtilities;

import Model.AudioPlayer;
import Model.board.*;
import View.BeginFrame;
import View.Chessboard;
import View.Constant;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new BeginFrame(Constant.BEGIN_FRAME_WIDTH, Constant.BEGIN_FRAME_HEIGHT);
            // AudioPlayer.playBgm("resource\\Audio\\Bunny.wav");

            // new Table();
            // Board.Builder builder = new Board.Builder();
            // Board board = new Board(builder);
            // board = Board.testBoard1();
            // board.getTile(16).getPiece().printPossibleMoves(board);
            // System.out.println(board);
            // System.out.println(BoardUtils.TERRAIN_BOARD.get(10));

            // GameController gameController = new
            // GameController(mainFrame.getChessboardComponent(), new Chessboard());

        });
    }
}
