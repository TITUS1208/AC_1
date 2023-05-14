import javax.swing.SwingUtilities;

import Model.Board;
import View.MainFrame;

public class Main {
    public static void main(String[] args) {
        // Board board = new Board();
        // System.out.println(board);
        SwingUtilities.invokeLater(() -> {
            // Assume one chess size is 70*70, Chessboard would be 7*70, 9*70
            // Let status panel be 250, 9*70
            // Let the margin of every border be 30
            MainFrame mainFrame = new MainFrame(30 + 7 * 70 + 30 + 250 + 30, 30 + 9 * 70 + 30);
            // GameController gameController = new
            // GameController(mainFrame.getChessboardComponent(), new Chessboard());
        });
    }
}
