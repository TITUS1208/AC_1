package View;

import java.awt.Font;

import javax.swing.*;

public class MainFrame extends JFrame {
    private final int frameWidth;
    private final int frameHeight;
    private final int buttonWidth = 100;
    private final int buttonHeight = 60;

    private JButton restartButton;
    private Chessboard chessboard;

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
        addLabel();
        addChessboard();
    }

    public void addRestartButton() {
        restartButton = new JButton("RESTART");
        // restartButton.setSize(100, 70);
        restartButton.setFont(new Font("Serif", Font.BOLD, 10));
        restartButton.setFocusable(false);
        restartButton.setBounds((frameWidth - buttonWidth) / 2, frameHeight - buttonHeight - 50, buttonWidth,
                buttonHeight);
        restartButton.addActionListener(e -> {
            System.out.println("restartButton being clicked");
            // chessboardComponent.getGameController().restart();
        });
        add(restartButton);
    }

    private void addChessboard() {
        // chessboardComponent = new ChessboardComponent();
        // chessboardComponent.setLocation(frameHeight / 5, frameHeight / 10);
        // add(chessboardComponent);
    }

    private void addLabel() {
        JLabel statusLabel = new JLabel("Hello there");
        statusLabel.setBounds((frameWidth - buttonWidth) / 2, 0, 200, 50);
        statusLabel.setHorizontalTextPosition(JLabel.CENTER);
        statusLabel.setFont(new Font("Comic Sans", Font.BOLD, 18));
        add(statusLabel);
    }
}
