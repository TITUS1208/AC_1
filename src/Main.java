import javax.swing.SwingUtilities;

import Model.AudioPlayer;
import Model.board.*;
import View.BeginFrame;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new BeginFrame(300, 220);
            AudioPlayer.playBgm("resource\\Audio\\Bunny.wav");

            Board.Builder builder = new Board.Builder();
            Board board = new Board(builder);
            System.out.println(board);
            System.out.println(BoardUtils.TERRAIN_BOARD.get(10));

            // GameController gameController = new
            // GameController(mainFrame.getChessboardComponent(), new Chessboard());

        });
    }
}
