import java.io.FileNotFoundException;
import javax.swing.SwingUtilities;

import Model.AudioPlayer;
import View.BeginFrame;
import View.Constant;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new BeginFrame(Constant.BEGIN_FRAME_WIDTH, Constant.BEGIN_FRAME_HEIGHT);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            // AudioPlayer.playBgm("resource\\Audio\\Bunny.wav");
        });
    }
}
