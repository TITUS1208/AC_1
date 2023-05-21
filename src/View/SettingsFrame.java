package View;

import javax.swing.*;

import Model.AudioPlayer;
import java.awt.Font;

public class SettingsFrame extends JDialog {
    private final int buttonWidth = 190;
    private final int buttonHeight = 60;

    private JButton changeThemeButton;
    // private JButton turnOnButton;
    // private JButton turnOffButton;
    private JButton turnOnOffButton;
    private JButton backButton;

    private JLabel titleLabel;

    private ImageIcon jungleIcon;
    int count = 2;

    public SettingsFrame(ImageIcon jungleIcon) {
        this.jungleIcon = jungleIcon;
        setTitle("Jungle_CS109");
        setSize(300, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(null);
        setVisible(true);
        setResizable(false);
        setIconImage(jungleIcon.getImage());

        addChangeThemeButton();
        addTurnOnOffButton();
        addbackButton();
        addTitleLabel();

        layoutControl();
    }

    public void layoutControl() {
        changeThemeButton.setBounds(300 / 2 - buttonWidth / 2 - 5, 30 + buttonHeight, buttonWidth,
                buttonHeight);
        turnOnOffButton.setBounds(300 / 2 - buttonWidth / 2 - 5, 30 * 2 + buttonHeight * 2, buttonWidth,
                buttonHeight);
        backButton.setBounds(300 / 2 - buttonWidth / 2 - 5, 30 * 3 + buttonHeight * 3, buttonWidth,
                buttonHeight);
        titleLabel.setBounds(300 / 2 - 55, 20, 200, 50);
    }

    public void addChangeThemeButton() {
        changeThemeButton = new JButton("Change Theme");
        changeThemeButton.setFont(new Font("Serif", Font.BOLD, 16));
        changeThemeButton.setFocusable(false);

        changeThemeButton.addActionListener(e -> {
            System.out.println("changeThemeButton being clicked");
            // Change theme here
        });
        add(changeThemeButton);
    }

    public void addTurnOnOffButton() {
        turnOnOffButton = new JButton("Turn OFF BGM");
        turnOnOffButton.setFont(new Font("Serif", Font.BOLD, 16));
        turnOnOffButton.setFocusable(false);

        turnOnOffButton.addActionListener(e -> {
            if (count % 2 == 0) {
                System.out.println("BGM is turned off");
                turnOnOffButton.setText("Play BGM");
                AudioPlayer.stopPlaying();
            } else {
                System.out.println("BGM is being played again");
                turnOnOffButton.setText("Turn OFF BGM");
                AudioPlayer.continuePlaying();
                AudioPlayer.playBgm("resource\\Audio\\Bunny.wav");
            }
            count++;
        });
        add(turnOnOffButton);
    }

    public void addbackButton() {
        backButton = new JButton("Back");
        backButton.setFont(new Font("Serif", Font.BOLD, 16));
        backButton.setFocusable(false);

        backButton.addActionListener(e -> {
            System.out.println("backButton being clicked");
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
}
