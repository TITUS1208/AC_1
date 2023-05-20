package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.File;

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

    private JLabel playerLabel;
    private JLabel roundLabel;

    private JFileChooser loadFC;

    private Chessboard chessboard;

    private Icon settingsIcon;
    private ImageIcon jungleIcon;

    public MainFrame(int frameWidth, int frameHeight, ImageIcon jungleIcon) {
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.jungleIcon = jungleIcon;
        setTitle("Jungle_CS109");
        setSize(frameWidth, frameHeight);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
        setResizable(false);
        getContentPane().setBackground(Color.LIGHT_GRAY); // Change soon
        setIconImage(jungleIcon.getImage());

        addRestartButton();
        addUndoButton();
        addSaveButton();
        addLoadButton();
        addSettingsButton();
        addPlayerLabel();
        addRoundLabel();
        addChessboard();

        layoutControl();
    }

    public void layoutControl() {
        playerLabel.setBounds(30 + 7 * 70 + 115, 30, 300, 50);
        roundLabel.setBounds(30 + 7 * 70 + 155, 60, 300, 50);
        restartButton.setBounds(30 + 7 * 70 + 100, 80 + 50, buttonWidth,
                buttonHeight);
        undoButton.setBounds(30 + 7 * 70 + 100, 80 + buttonHeight + 50 * 2, buttonWidth,
                buttonHeight);
        saveButton.setBounds(30 + 7 * 70 + 100, 80 + buttonHeight * 2 + 50 * 3, buttonWidth,
                buttonHeight);
        loadButton.setBounds(30 + 7 * 70 + 100, 80 + buttonHeight * 3 + 50 * 4, buttonWidth,
                buttonHeight);
        settingsButton.setBounds(0, 0, 36, 36);
        chessboard.setBounds(36, 36, 75 * 7, 75 * 9);
    }

    public void addRestartButton() {
        restartButton = new JButton("RESTART");
        restartButton.setFont(new Font("Monaco", Font.BOLD, 17));
        restartButton.setFocusable(false);

        restartButton.addActionListener(e -> {
            System.out.println("restartButton being clicked");
            // Prompts restart action here via controller
        });
        add(restartButton);
    }

    public void addUndoButton() {
        undoButton = new JButton("UNDO");
        undoButton.setFont(new Font("Monaco", Font.BOLD, 17));
        undoButton.setFocusable(false);

        undoButton.addActionListener(e -> {
            System.out.println("undoButton being clicked");
            // Prompts undo action here via controller
        });
        add(undoButton);
    }

    public void addSaveButton() {
        saveButton = new JButton("SAVE");
        saveButton.setFont(new Font("Monaco", Font.BOLD, 17));
        saveButton.setFocusable(false);

        saveButton.addActionListener(e -> {
            System.out.println("saveButton being clicked");
            String fileName = JOptionPane.showInputDialog("File's name to save: ");
            while (fileName.equals("")) {
                JOptionPane.showMessageDialog(this, "File's name must not be blank.");
                fileName = JOptionPane.showInputDialog("File's name to save:");
            }
            // Call save method from controller
        });
        add(saveButton);
    }

    public void addLoadButton() {
        loadButton = new JButton("LOAD");
        loadButton.setFont(new Font("Monaco", Font.BOLD, 17));
        loadButton.setFocusable(false);

        loadButton.addActionListener(e -> {
            System.out.println("loadButton being clicked");
            loadFC = new JFileChooser("./src/Save");
            loadFC.setDialogTitle("Load a file");
            int temp = loadFC.showOpenDialog(this);
            if (temp == JFileChooser.APPROVE_OPTION) {
                String path = loadFC.getSelectedFile().getAbsolutePath();
                // System.out.println(path);
                // Call load method from controller
            }
        });
        add(loadButton);
    }

    public void addSettingsButton() {
        settingsIcon = new ImageIcon("resource/Icon/settingsIcon.png");
        settingsButton = new JButton(settingsIcon);
        settingsButton.setFocusable(false);
        settingsButton.setOpaque(false);
        settingsButton.setContentAreaFilled(false);
        settingsButton.setBorderPainted(false);
        settingsButton.addActionListener(e -> {
            System.out.println("settingsButton being clicked");
            new SettingsFrame(jungleIcon);
        });
        add(settingsButton);
    }

    private void addPlayerLabel() {
        playerLabel = new JLabel("It is BLUE's turn!"); // Get changed every move in controller using setText()
        playerLabel.setHorizontalTextPosition(JLabel.CENTER);
        playerLabel.setVerticalTextPosition(JLabel.CENTER);
        playerLabel.setFont(new Font("Comic Sans", Font.BOLD, 19));
        add(playerLabel);
    }

    private void addRoundLabel() {
        roundLabel = new JLabel("Round 1"); // Get changed every move in controller using setText()
        roundLabel.setHorizontalTextPosition(JLabel.CENTER);
        playerLabel.setVerticalTextPosition(JLabel.CENTER);
        roundLabel.setFont(new Font("Comic Sans", Font.BOLD, 19));
        add(roundLabel);
    }

    private void addChessboard() {
        chessboard = new Chessboard();
        add(chessboard);
    }
}
