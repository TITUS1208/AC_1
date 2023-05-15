package View;

import javax.swing.*;
import java.awt.Font;
import java.awt.Image;

public class SettingsFrame extends JDialog {
    private final int buttonWidth = 190;
    private final int buttonHeight = 60;

    private JButton changeThemeButton;
    // private JButton turnOnButton;
    // private JButton turnOffButton;
    private JButton changeBGMButton;
    private JButton backButton;

    private JLabel titleLabel;

    private ImageIcon jungleIcon;

    public SettingsFrame(ImageIcon jungleIcon) {
        this.jungleIcon = jungleIcon;
        setTitle("Jungle_CS109");
        setSize(300, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(null);
        setVisible(true);
        setResizable(false);
        jungleIcon = new ImageIcon("resource/jungleIcon.png");
        setIconImage(jungleIcon.getImage());

        addChangeThemeButton();
        // addTurnOnButton();
        // addTurnOffButton();
        addChangeBGMButton();
        addbackButton();
        addTitleLabel();
    }

    public void addChangeThemeButton() {
        changeThemeButton = new JButton("Change Theme");
        changeThemeButton.setFont(new Font("Serif", Font.BOLD, 16));
        changeThemeButton.setFocusable(false);
        changeThemeButton.setBounds(300 / 2 - buttonWidth / 2 - 5, 30 + buttonHeight, buttonWidth,
                buttonHeight);
        changeThemeButton.addActionListener(e -> {
            System.out.println("changeThemeButton being clicked");
            // Change BGM here
        });
        add(changeThemeButton);
    }

    // public void addTurnOnButton() {
    // turnOnButton = new JButton("Turn On BGM");
    // turnOnButton.setFont(new Font("Serif", Font.BOLD, 16));
    // turnOnButton.setFocusable(false);
    // turnOnButton.setBounds(frameWidth / 2 - buttonWidth / 2 - 5, 30 * 2 +
    // buttonHeight * 2, buttonWidth,
    // buttonHeight);
    // turnOnButton.addActionListener(e -> {
    // System.out.println("turnOnButton being clicked");
    // //
    // });
    // add(turnOnButton);
    // }

    // public void addTurnOffButton() {
    // turnOffButton = new JButton("Turn Off BGM");
    // turnOffButton.setFont(new Font("Serif", Font.BOLD, 16));
    // turnOffButton.setFocusable(false);
    // turnOffButton.setBounds(frameWidth / 2 - buttonWidth / 2 - 5, 30 * 3 +
    // buttonHeight * 3, buttonWidth,
    // buttonHeight);
    // turnOffButton.addActionListener(e -> {
    // System.out.println("turnOffButton being clicked");
    // //
    // });
    // add(turnOffButton);
    // }

    public void addChangeBGMButton() {
        changeBGMButton = new JButton("Change BGM");
        changeBGMButton.setFont(new Font("Serif", Font.BOLD, 16));
        changeBGMButton.setFocusable(false);
        changeBGMButton.setBounds(300 / 2 - buttonWidth / 2 - 5, 30 * 2 + buttonHeight * 2, buttonWidth,
                buttonHeight);
        changeBGMButton.addActionListener(e -> {
            System.out.println("changeBGMButton being clicked");
            //
        });
        add(changeBGMButton);
    }

    public void addbackButton() {
        backButton = new JButton("Back");
        backButton.setFont(new Font("Serif", Font.BOLD, 16));
        backButton.setFocusable(false);
        backButton.setBounds(300 / 2 - buttonWidth / 2 - 5, 30 * 3 + buttonHeight * 3, buttonWidth,
                buttonHeight);
        backButton.addActionListener(e -> {
            System.out.println("backButton being clicked");
            this.setVisible(false);
        });
        add(backButton);
    }

    private void addTitleLabel() {
        titleLabel = new JLabel("SETTINGS");
        titleLabel.setBounds(300 / 2 - 55, 20, 200, 50);
        titleLabel.setHorizontalTextPosition(JLabel.CENTER);
        titleLabel.setFont(new Font("Comic Sans", Font.BOLD, 18));
        add(titleLabel);
    }
}
