package View;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

public class TimerTasks extends TimerTask {

    private int counter = 30;
    private JLabel timerLabel;

    public TimerTasks(JLabel timerLabel) {
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
            counter = 30;
        }
    }

    public static void main(JLabel timerLabel) {
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTasks(timerLabel), 0, 1000);
    }
};
