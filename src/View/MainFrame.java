package View;

import Model.AudioPlayer;
import Model.board.Board;
import Model.board.BoardUtils;
import Model.board.Terrain;
import View.Chessboard.TilePanel;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
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
    private JButton backButton;
    private JButton settingsButton;

    private JLabel playerLabel;
    private JLabel roundLabel;
    private JLabel timerLabel;
    private JLabel background;
    private JLabel blackBG;
    private JLabel pinkBG;

    private JFileChooser loadFC;

    private Chessboard chessboard;

    private Icon whiteSettingsIcon;
    private Icon blackSettingsIcon;

    private ImageIcon jungleIcon;

    private BeginFrame beginFrame;
    private UsernamePassword username_pw;
    private boolean isBlack;

    public MainFrame(int frameWidth, int frameHeight, ImageIcon jungleIcon, BeginFrame beginFrame,
            UsernamePassword username_pw) throws IOException {
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.jungleIcon = jungleIcon;
        this.beginFrame = beginFrame;
        this.username_pw = username_pw;
        isBlack = true;
        whiteSettingsIcon = new ImageIcon("resource/Icon/whiteSettingsIcon.png");
        blackSettingsIcon = new ImageIcon("resource/Icon/blackSettingsIcon.png");
        setTitle("Jungle_CS109");
        setSize(frameWidth, frameHeight);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
        setResizable(false);
        setIconImage(jungleIcon.getImage());

        addRestartButton();
        addUndoButton();
        addSaveButton();
        addLoadButton();
        addBackButton();
        addSettingsButton();
        addPlayerLabel();
        addRoundLabel();
        addTimerLabel();
        addChessboard();
        addBackground();

        layoutControl();
        TimerTasks.main(timerLabel);
    }

    public void layoutControl() {
        playerLabel.setBounds(30 + 7 * 70 + 115, 30, 300, 50);
        roundLabel.setBounds(30 + 7 * 70 + 155, 60, 300, 50);
        timerLabel.setBounds(30 + 7 * 70 + 80, 90, 300, 50);

        restartButton.setBounds(30 + 7 * 70 + 100, 100 + 50, buttonWidth,
                buttonHeight);
        undoButton.setBounds(30 + 7 * 70 + 100, 100 + buttonHeight + 50 * 2, buttonWidth,
                buttonHeight);
        saveButton.setBounds(30 + 7 * 70 + 100, 100 + buttonHeight * 2 + 50 * 3, buttonWidth,
                buttonHeight);
        loadButton.setBounds(30 + 7 * 70 + 100, 100 + buttonHeight * 3 + 50 * 4, buttonWidth,
                buttonHeight);
        backButton.setBounds(30 + 7 * 70 + 100, 100 + buttonHeight * 4 + 50 * 5, buttonWidth,
                buttonHeight);
        settingsButton.setBounds(0, 0, 36, 36);
        chessboard.setBounds(36, 36, 75 * 7, 75 * 9);
    }

    public void addRestartButton() {
        restartButton = new JButton("RESTART");
        restartButton.setFont(new Font("Monaco", Font.BOLD, 17));
        restartButton.setFocusable(false);
        restartButton.addActionListener(e -> {
            AudioPlayer.playSoundEffect("resource\\Audio\\click.wav");
            int temp = JOptionPane.showConfirmDialog(this, "Are you sure to restart?");
            if (temp == JOptionPane.YES_OPTION) {
                setVisible(false);
                try {
                    if (!isBlack) {
                        for (int i = 0; i < 63; i++) {
                            Terrain terrain = BoardUtils.TERRAIN_BOARD.get(i);
                            terrain.changeGrassColor();
                        }
                    }
                    new MainFrame(frameWidth, frameHeight, jungleIcon, beginFrame, username_pw);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        add(restartButton);
    }

    public void addUndoButton() {
        undoButton = new JButton("UNDO");
        undoButton.setFont(new Font("Monaco", Font.BOLD, 17));
        undoButton.setFocusable(false);

        undoButton.addActionListener(e -> {
            AudioPlayer.playSoundEffect("resource\\Audio\\click.wav");
            chessboard.loadPreviousBoard(chessboard.getBoardHistory());
            /*
             * ArrayList<String> moveHistory = chessboard.moveHistory;
             * Board board = chessboard.getBoard();
             * String previousMove = moveHistory.remove(moveHistory.size()-1);
             * String[] moveInfo = previousMove.split(" ");
             * String piece = moveInfo[0];
             * int currentCoordinate = Integer.valueOf(moveInfo[1]);
             * int destinationCoordinate = Integer.valueOf(moveInfo[2]);
             * 
             */
        });
        add(undoButton);
    }

    public void addSaveButton() {
        saveButton = new JButton("SAVE");
        saveButton.setFont(new Font("Monaco", Font.BOLD, 17));
        saveButton.setFocusable(false);
        saveButton.addActionListener(e -> {
            AudioPlayer.playSoundEffect("resource\\Audio\\click.wav");
            String fileName = JOptionPane.showInputDialog("File's name to save: ");
            while (fileName.equals("")) {
                JOptionPane.showMessageDialog(this, "File's name must not be blank.");
                fileName = JOptionPane.showInputDialog("File's name to save:");
            }
            String path = "src/Save/" + fileName + ".txt";
            //System.out.println("File name; " + path);
            try{
                File obj = new File(path);

                if (obj.createNewFile()){
                    FileWriter writer = new FileWriter(path);
                    //writer.write("Hello world");
                    //writer.close();

                    obj.createNewFile();
                    //System.out.println("created New File");
                    StringBuilder sBuilder = new StringBuilder();
                    //sBuilder.append(chessboard.getBoard().getTurn().getAlliance().toString());
                    int boardCount = chessboard.getBoardHistory().size();
                    System.out.println(boardCount);
                    for (int j = 0; j < boardCount; j++) {
                        //System.out.println(chessboard.getBoardHistory().get(j));
                        String text = chessboard.getBoardHistory().get(j).getAllActivePiece().size() + "\n";
                        sBuilder.append(text);
                        for (int k = 0; k < BoardUtils.BOARD_SIZE; k++){
                            text = chessboard.getBoardHistory().get(j).tileInfo(k);
                            if (text != null) sBuilder.append(text + "\n");
                        }
                    }
                    //System.out.println();
                    writer.write(sBuilder.toString());
                    //System.out.println(sBuilder);
                    writer.close();


                } else{
                    System.out.println("same name TODO");
                    JOptionPane.showMessageDialog(null, "File already exist", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch(IOException q){
                q.printStackTrace();;
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
            AudioPlayer.playSoundEffect("resource\\Audio\\click.wav");
            loadFC = new JFileChooser("./src/Save");
            loadFC.setDialogTitle("Load a file");
            int temp = loadFC.showOpenDialog(this);
            if (temp == JFileChooser.APPROVE_OPTION) {
                String path = loadFC.getSelectedFile().getAbsolutePath();
                // System.out.println(path);
                // Call load method from controller

                ArrayList<Board> boards = Board.loadBoards(path);

                if (boards == null){
                    System.out.println("invalid board cannot load");
                    String text = "Cannot load board\nInvalid board: " + BoardUtils.LOAD_ERROR_MESSAGE;
                    JOptionPane.showMessageDialog(null, text, "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    chessboard.setBoardHistory(boards);
                    chessboard.setBoard(boards.get(boards.size() - 1));
                    chessboard.drawBoard(chessboard.getBoard());
                }
            }
        });
        add(loadButton);
    }

    public void addBackButton() {
        backButton = new JButton("BACK");
        backButton.setFont(new Font("Monaco", Font.BOLD, 17));
        backButton.setFocusable(false);
        backButton.addActionListener(e -> {
            AudioPlayer.playSoundEffect("resource\\Audio\\click.wav");
            int temp = JOptionPane.showConfirmDialog(this, "Are you leaving?\nMake sure you save before you leave!");
            if (temp == JOptionPane.YES_OPTION) {
                setVisible(false);
                beginFrame.setVisible(true);
            }
        });
        add(backButton);
    }

    public void addSettingsButton() {
        settingsButton = new JButton(whiteSettingsIcon);
        settingsButton.setFocusable(false);
        settingsButton.setOpaque(false);
        settingsButton.setContentAreaFilled(false);
        settingsButton.setBorderPainted(false);
        settingsButton.addActionListener(e -> {
            AudioPlayer.playSoundEffect("resource\\Audio\\click.wav");
            try {
                new SettingsFrame(jungleIcon, username_pw, this);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        });
        add(settingsButton);
    }

    private void addPlayerLabel() {
        playerLabel = new JLabel("It is BLUE's turn!"); // Get changed every move in controller using setText()
        playerLabel.setHorizontalTextPosition(JLabel.CENTER);
        playerLabel.setVerticalTextPosition(JLabel.CENTER);
        playerLabel.setFont(new Font("Comic Sans", Font.BOLD, 19));
        playerLabel.setForeground(Color.white);
        add(playerLabel);
    }

    private void addRoundLabel() {
        roundLabel = new JLabel("Round 1"); // Get changed every move in controller using setText()
        roundLabel.setHorizontalTextPosition(JLabel.CENTER);
        roundLabel.setVerticalTextPosition(JLabel.CENTER);
        roundLabel.setFont(new Font("Comic Sans", Font.BOLD, 19));
        roundLabel.setForeground(Color.white);
        add(roundLabel);
    }

    private void addTimerLabel() {
        timerLabel = new JLabel("Round ends in: 60 seconds");
        timerLabel.setHorizontalTextPosition(JLabel.CENTER);
        timerLabel.setVerticalTextPosition(JLabel.CENTER);
        timerLabel.setFont(new Font("Comic Sans", Font.BOLD, 19));
        timerLabel.setForeground(Color.white);
        add(timerLabel);
    }

    private void addChessboard() {
        chessboard = new Chessboard(this, beginFrame, username_pw);
        add(chessboard);
    }

    private void addBackground() {
        Image image = new ImageIcon("resource/Background/black.jpg").getImage();
        image = image.getScaledInstance(Constant.MAIN_FRAME_WIDTH, Constant.MAIN_FRAME_HEIGHT, Image.SCALE_DEFAULT);
        ImageIcon icon = new ImageIcon(image);
        blackBG = new JLabel(icon);
        blackBG.setSize(Constant.MAIN_FRAME_WIDTH, Constant.MAIN_FRAME_HEIGHT);
        blackBG.setLocation(0, 0);

        image = new ImageIcon("resource/Background/pink.jpg").getImage();
        image = image.getScaledInstance(Constant.MAIN_FRAME_WIDTH, Constant.MAIN_FRAME_HEIGHT, Image.SCALE_DEFAULT);
        icon = new ImageIcon(image);
        pinkBG = new JLabel(icon);
        pinkBG.setSize(Constant.MAIN_FRAME_WIDTH, Constant.MAIN_FRAME_HEIGHT);
        pinkBG.setLocation(0, 0);

        background = blackBG;
        add(background);
    }

    public void changeBackground() throws IOException {
        if (isBlack) {
            remove(background);
            isBlack = false;
            background = pinkBG;
            add(background);
            playerLabel.setForeground(Color.black);
            roundLabel.setForeground(Color.black);
            timerLabel.setForeground(Color.black);
            settingsButton.setIcon(blackSettingsIcon);
            for (int i = 0; i < 63; i++) {
                Terrain terrain = BoardUtils.TERRAIN_BOARD.get(i);
                terrain.changeGrassColorPink();
            }
        } else {
            remove(background);
            isBlack = true;
            background = blackBG;
            add(background);
            playerLabel.setForeground(Color.white);
            roundLabel.setForeground(Color.white);
            timerLabel.setForeground(Color.white);
            settingsButton.setIcon(whiteSettingsIcon);
            for (int i = 0; i < 63; i++) {
                Terrain terrain = BoardUtils.TERRAIN_BOARD.get(i);
                terrain.changeGrassColor();
            }
        }
        chessboard.drawBoard(Board.testBoard1());
        repaint();
        revalidate();
        try {
            click(480, 300);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public static void click(int x, int y) throws AWTException {
        Robot bot = new Robot();
        bot.mouseMove(x, y);
        bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }
}
