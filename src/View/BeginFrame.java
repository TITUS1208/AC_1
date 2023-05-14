package View;

import javax.swing.*;

import java.awt.Color;
import java.awt.Font;

public class BeginFrame extends JFrame {
    private final int frameWidth;
    private final int frameHeight;
    private final int buttonWidth = 190;
    private final int buttonHeight = 60;

    private JButton beginButton;

    // private JLabel statusLabel;

    private ImageIcon jungleIcon;

    public BeginFrame(int frameWidth, int frameHeight) {
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        setTitle("Jungle_CS109");
        setSize(frameWidth, frameHeight);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
        setResizable(false);
        getContentPane().setBackground(Color.LIGHT_GRAY); // Change soon
        jungleIcon = new ImageIcon("resource/jungleIcon.png");
        setIconImage(jungleIcon.getImage());

        addBeginButton();
    }

    public void addBeginButton() {
        beginButton = new JButton("Begin");
        beginButton.setFont(new Font("Monaco", Font.BOLD, 17));
        beginButton.setFocusable(false);
        beginButton.setBounds(frameWidth / 2 - buttonWidth / 2 - 5, buttonHeight - 15, buttonWidth,
                buttonHeight);
        beginButton.addActionListener(e -> {
            System.out.println("beginButton being clicked");
            new MainFrame(30 + 7 * 70 + 30 + 250 + 30, 30 + 9 * 70
                    + 30);
            this.setVisible(false);
        });
        add(beginButton);
    }
}
// Assume one chess size is 70*70, Chessboard would be 7*70, 9*70
// Let status panel be 250, 9*70
// Let the margin of every border be 30 // MainFrame
