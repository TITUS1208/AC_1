import javax.swing.SwingUtilities;

import Model.Board;
import View.BeginFrame;
import View.MainFrame;
import View.SettingsFrame;

public class Main {
    public static void main(String[] args) {
        // Board board = new Board();
        // System.out.println(board);
        SwingUtilities.invokeLater(() -> {
            // MainFrame mainFrame = new MainFrame(30 + 7 * 70 + 30 + 250 + 30, 30 + 9 * 70
            // + 30);
            // SettingsFrame settingsFrame = new SettingsFrame(300, 500);
            BeginFrame beginFrame = new BeginFrame(300, 200);


//
            // GameController gameController = new
            // GameController(mainFrame.getChessboardComponent(), new Chessboard());
        });
    }
}
