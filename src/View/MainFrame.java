package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.*;

public class MainFrame extends JFrame {
    private final int frameWidth;
    private final int frameHeight;
    private final int buttonWidth = 190;
    private final int buttonHeight = 60;

    private JButton restartButton;
    private JButton undoButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton settingsButton;

    private JLabel statusLabel;

    private Chessboard chessboard;

    private Icon settingsIcon;
    private ImageIcon jungleIcon;

    public MainFrame(int frameWidth, int frameHeight) {
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

        addRestartButton();
        addUndoButton();
        addSaveButton();
        addLoadButton();
        addSettingsButton();
        addStatusLabel();
        addChessboard();
    }

    public void addRestartButton() {
        restartButton = new JButton("RESTART");
        restartButton.setFont(new Font("Monaco", Font.BOLD, 17));
        restartButton.setFocusable(false);
        restartButton.setBounds(30 + 7 * 70 + 55, 80, buttonWidth,
                buttonHeight);
        restartButton.addActionListener(e -> {
            System.out.println("restartButton being clicked");
            // Prompts restart action here
        });
        add(restartButton);
    }

    public void addUndoButton() {
        undoButton = new JButton("UNDO");
        undoButton.setFont(new Font("Monaco", Font.BOLD, 17));
        undoButton.setFocusable(false);
        undoButton.setBounds(30 + 7 * 70 + 55, 80 + buttonHeight + 30, buttonWidth,
                buttonHeight);
        undoButton.addActionListener(e -> {
            System.out.println("undoButton being clicked");
            // Prompts undo action here
        });
        add(undoButton);
    }

    public void addSaveButton() {
        saveButton = new JButton("SAVE");
        saveButton.setFont(new Font("Monaco", Font.BOLD, 17));
        saveButton.setFocusable(false);
        saveButton.setBounds(30 + 7 * 70 + 55, 80 + buttonHeight * 2 + 30 * 2, buttonWidth,
                buttonHeight);
        saveButton.addActionListener(e -> {
            System.out.println("saveButton being clicked");
            // Prompts save action here
        });
        add(saveButton);
    }

    public void addLoadButton() {
        loadButton = new JButton("LOAD");
        loadButton.setFont(new Font("Monaco", Font.BOLD, 17));
        loadButton.setFocusable(false);
        loadButton.setBounds(30 + 7 * 70 + 55, 80 + buttonHeight * 3 + 30 * 3, buttonWidth,
                buttonHeight);
        loadButton.addActionListener(e -> {
            System.out.println("loadButton being clicked");
            // Prompts undo action here
        });
        add(loadButton);
    }

    public void addSettingsButton() {
        settingsIcon = new ImageIcon("resource/settingsIcon.png");
        settingsButton = new JButton(settingsIcon);
        settingsButton.setFocusable(false);
        settingsButton.setBounds(0, 0, 36, 36);

        settingsButton.setOpaque(false);
        settingsButton.setContentAreaFilled(false);
        settingsButton.setBorderPainted(false);
        settingsButton.addActionListener(e -> {
            System.out.println("settingsButton being clicked");
            new SettingsFrame(300, 500);
        });
        add(settingsButton);
    }

    private void addStatusLabel() {
        statusLabel = new JLabel("It is BLUE's turn!\n (Round 1)"); // Get changed every move in controller using
                                                                    // setText()
        statusLabel.setBounds(30 + 7 * 70 + 30, 30, 300, 50);
        statusLabel.setHorizontalTextPosition(JLabel.CENTER);
        statusLabel.setFont(new Font("Comic Sans", Font.BOLD, 18));
        add(statusLabel);
    }

    private void addChessboard() {
        // chessboardComponent = new ChessboardComponent();
        // chessboardComponent.setLocation(frameHeight / 5, frameHeight / 10);
        // add(chessboardComponent);
    }
}
