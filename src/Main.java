import javax.swing.SwingUtilities;

import Model.Board;
import View.MainFrame;

public class Main {
    public static void main(String[] args) {
        // Board board = new Board();
        // System.out.println(board);
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame(7 * 70 + 20 + 20, 9 * 70 + 50 + 50); // 530,730
            // GameController gameController = new
            // GameController(mainFrame.getChessboardComponent(), new Chessboard());
        });
    }
}
