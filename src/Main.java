import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.swing.SwingUtilities;

import Model.AudioPlayer;
import Model.board.Board;
import View.BeginFrame;
import View.Constant;

public class Main {
    public static void main(String[] args) {

        //ArrayList<Board> boards =  Board.loadBoards("src/Save/test2.txt");
        //System.out.println(boards);

        SwingUtilities.invokeLater(() -> {
            try {
                new BeginFrame(Constant.BEGIN_FRAME_WIDTH, Constant.BEGIN_FRAME_HEIGHT);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            AudioPlayer.playBgm("resource\\Audio\\Bunny.wav");
        });
    }
}
