package View;

import java.awt.Font;

import javax.swing.*;

public class MainFrame extends JFrame {
    private final int frameWidth;
    private final int frameHeight;
    private final int buttonWidth = 70;
    private final int buttonHeight = 100;

    private JButton restartButton;

    public MainFrame(int frameWidth, int frameHeight) {
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        setTitle("Jungle_CS109");
        setSize(frameWidth, frameHeight);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);

        addRestartButton();
    }

    public void addRestartButton() {
        restartButton = new JButton("RESTART");
        restartButton.setSize(100, 70);
        restartButton.setFont(new Font("Serif", Font.BOLD, 10));
        restartButton.setFocusable(false);
        restartButton.addActionListener(e -> {
            System.out.println("restartButton being clicked");
            // chessboardComponent.getGameController().restart();
        });
        add(restartButton);
        restartButton.setLocation(0 + 10, frameHeight - buttonHeight - 10);
    }
}
