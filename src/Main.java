import javax.swing.SwingUtilities;

import Model.board.*;
import View.BeginFrame;
import View.MainFrame;

public class Main {
    public static void main(String[] args) {
        // Board board = new Board();
        // System.out.println(board);
        SwingUtilities.invokeLater(() -> {
            // MainFrame mainFrame = new MainFrame(30 + 7 * 70 + 30 + 250 + 30, 30 + 9 * 70
            // + 30);
            // SettingsFrame settingsFrame = new SettingsFrame(300, 500);
            BeginFrame beginFrame = new BeginFrame(300, 200);

            Board.Builder builder = new Board.Builder();
            Board board = new Board(builder);
            System.out.println(board);
            System.out.println(BoardUtils.TERRAIN_BOARD.get(10));

            //
            // GameController gameController = new
            // GameController(mainFrame.getChessboardComponent(), new Chessboard());
        });
    }
}
