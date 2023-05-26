package View;

import javax.swing.*;

import Model.AudioPlayer;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SettingsFrame extends JDialog {
    private final int buttonWidth = 190;
    private final int buttonHeight = 60;

    private JButton changeThemeButton;
    private JButton turnOnOffButton;
    private JButton rankingButton;
    private JButton backButton;

    private JLabel titleLabel;
    private JPanel rankingPanel;

    private ImageIcon jungleIcon;
    private UsernamePassword username_pw;
    private int count = 2;
    private MainFrame mainFrame;

    public SettingsFrame(ImageIcon jungleIcon, UsernamePassword username_pw, MainFrame mainFrame)
            throws FileNotFoundException {
        this.jungleIcon = jungleIcon;
        this.username_pw = username_pw;
        this.mainFrame = mainFrame;
        setTitle("Jungle_CS109");
        setSize(300, 490);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(null);
        setVisible(true);
        setResizable(false);
        setIconImage(jungleIcon.getImage());

        addChangeThemeButton();
        addTurnOnOffButton();
        addRankingButton();
        addbackButton();
        addTitleLabel();

        layoutControl();
    }

    public void layoutControl() {
        changeThemeButton.setBounds(300 / 2 - buttonWidth / 2 - 5, 30 + buttonHeight, buttonWidth,
                buttonHeight);
        turnOnOffButton.setBounds(300 / 2 - buttonWidth / 2 - 5, 30 * 2 + buttonHeight * 2, buttonWidth,
                buttonHeight);
        rankingButton.setBounds(300 / 2 - buttonWidth / 2 - 5, 30 * 3 + buttonHeight * 3, buttonWidth,
                buttonHeight);
        backButton.setBounds(300 / 2 - buttonWidth / 2 - 5, 30 * 4 + buttonHeight * 4, buttonWidth,
                buttonHeight);
        titleLabel.setBounds(300 / 2 - 55, 20, 200, 50);
    }

    public void addChangeThemeButton() {
        changeThemeButton = new JButton("Change Theme");
        changeThemeButton.setFont(new Font("Serif", Font.BOLD, 16));
        changeThemeButton.setFocusable(false);
        changeThemeButton.addActionListener(e -> {
            AudioPlayer.playSoundEffect("resource\\Audio\\click.wav");
            setVisible(false);
            try {
                mainFrame.changeBackground();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        add(changeThemeButton);
    }

    public void addTurnOnOffButton() {
        turnOnOffButton = new JButton("Turn OFF BGM");
        turnOnOffButton.setFont(new Font("Serif", Font.BOLD, 16));
        turnOnOffButton.setFocusable(false);
        turnOnOffButton.addActionListener(e -> {
            AudioPlayer.playSoundEffect("resource\\Audio\\click.wav");
            if (count % 2 == 0) {
                turnOnOffButton.setText("Play BGM");
                AudioPlayer.stopPlaying();
            } else {
                turnOnOffButton.setText("Turn OFF BGM");
                AudioPlayer.continuePlaying();
                AudioPlayer.playBgm("resource\\Audio\\Bunny.wav");
            }
            count++;
        });
        add(turnOnOffButton);
    }

    public void addRankingButton() throws FileNotFoundException {
        new UsernamePassword();
        rankingButton = new JButton("Ranking List");
        rankingButton.setFont(new Font("Serif", Font.BOLD, 16));
        rankingButton.setFocusable(false);
        rankingButton.addActionListener(e -> {
            AudioPlayer.playSoundEffect("resource\\Audio\\click.wav");
            JOptionPane.showMessageDialog(this, rankingPanel(), "Jungle_CS109",
                    JOptionPane.INFORMATION_MESSAGE);
        });
        add(rankingButton);
    }

    public void addbackButton() {
        backButton = new JButton("Back");
        backButton.setFont(new Font("Serif", Font.BOLD, 16));
        backButton.setFocusable(false);
        backButton.addActionListener(e -> {
            AudioPlayer.playSoundEffect("resource\\Audio\\click.wav");
            dispose();
        });
        add(backButton);
    }

    private void addTitleLabel() {
        titleLabel = new JLabel("SETTINGS");
        titleLabel.setHorizontalTextPosition(JLabel.CENTER);
        titleLabel.setVerticalTextPosition(JLabel.CENTER);
        titleLabel.setFont(new Font("Comic Sans", Font.BOLD, 18));
        add(titleLabel);
    }

    private JPanel rankingPanel() {
        rankingPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        rankingPanel.add(new JLabel("Ranking List"));
        for (int i = 0; i < username_pw.getUsername_score().size(); i++) {
            JLabel tempLabel = new JLabel(i + 1 + ". " + username_pw.getUsername_score().keySet().toArray()[i]
                    + " " + username_pw.getUsername_score().values().toArray()[i]);
            rankingPanel.add(tempLabel);
            // System.out.println(i + 1 + ". " +
            // username_pw.getUsername_score().keySet().toArray()[i]
            // + " " + username_pw.getUsername_score().values().toArray()[i]);
        }
        return rankingPanel;
    }
}
