package View;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

public class TimerTasks extends TimerTask {
    private static int max_time = 30;
    private static int counter = max_time;
    private JLabel timerLabel;
    private Chessboard chessboard;

    public TimerTasks(JLabel timerLabel, Chessboard chessboard) {
        this.chessboard = chessboard;
        this.timerLabel = timerLabel;
    }

    @Override
    public void run() {
        if (counter > 0) {
            // System.out.println(counter + " seconds");
            timerLabel.setText("Round ends in: " + counter + " seconds");
            counter--;
        } else {
            // Prompts switch player action here
            counter = max_time;
            chessboard.nextTurn();
        }
    }

    public static void main(JLabel timerLabel, Chessboard chessboard) {
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTasks(timerLabel, chessboard), 0, 1000);
    }

    public static void resetTime(){
        counter = max_time;
    }


};
