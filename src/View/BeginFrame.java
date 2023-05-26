package View;

import javax.swing.*;

import Model.AudioPlayer;
import Model.board.BoardUtils;
import Model.board.Terrain;

import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BeginFrame extends JFrame {
    private final int frameWidth;
    private final int frameHeight;
    private final int buttonWidth = 190;
    private final int buttonHeight = 60;

    private JButton loginButton;
    private JButton registerButton;

    private JLabel usernameLabel;
    private JLabel passwordLabel;

    private JTextField usernameField;
    private JPasswordField passwordField;

    private JCheckBox checkbox;
    private ImageIcon jungleIcon;

    private boolean isChecked;
    private boolean isLogin;

    private UsernamePassword username_pw;

    public BeginFrame(int frameWidth, int frameHeight) throws FileNotFoundException {
        username_pw = new UsernamePassword();
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        setTitle("Jungle_CS109");
        setSize(frameWidth, frameHeight);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
        setResizable(false);
        jungleIcon = new ImageIcon("resource/Icon/jungleIcon.png");
        setIconImage(jungleIcon.getImage());

        addUsernameLabel();
        addPasswordLabel();
        addUsernameField();
        addPasswordField();
        addLoginButton();
        addRegisterButton();
        addCheckbox();

        layoutControl();
    }

    public void layoutControl() {
        usernameLabel.setBounds(50, 30, 100, 30);
        passwordLabel.setBounds(50, 80, 100, 30);
        usernameField.setBounds(180, 30, 190, 30);
        passwordField.setBounds(180, 80, 190, 30);
        loginButton.setBounds(80, 130, 120, 40);
        registerButton.setBounds(220, 130, 120, 40);
        checkbox.setBounds(frameWidth / 2 - buttonWidth / 2 - 20, 170, 250, 50);
    }

    private void addUsernameLabel() {
        usernameLabel = new JLabel("Username: ");
        usernameLabel.setFont(new Font("Sans Serif", Font.BOLD, 18));
        add(usernameLabel);
    }

    private void addPasswordLabel() {
        passwordLabel = new JLabel("Password: ");
        passwordLabel.setFont(new Font("Sans Serif", Font.BOLD, 18));
        add(passwordLabel);
    }

    private void addUsernameField() {
        usernameField = new JTextField();
        add(usernameField);
    }

    private void addPasswordField() {
        passwordField = new JPasswordField();
        add(passwordField);
    }

    private void addLoginButton() {
        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Monaco", Font.BOLD, 17));
        loginButton.addActionListener((e) -> {
            AudioPlayer.playSoundEffect("resource\\Audio\\click.wav");
            login();
            if (isLogin) {
                if (isChecked) {
                    JOptionPane.showMessageDialog(this, "Successful login!", "Jungle_CS109",
                            JOptionPane.INFORMATION_MESSAGE);
                    try {
                        for (int i = 0; i < 63; i++) {
                            Terrain terrain = BoardUtils.TERRAIN_BOARD.get(i);
                            terrain.changeGrassColor();
                        }
                        new MainFrame(Constant.MAIN_FRAME_WIDTH, Constant.MAIN_FRAME_HEIGHT, jungleIcon, this,
                                username_pw);
                        AudioPlayer.playSoundEffect("resource\\Audio\\welcome.wav");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    this.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(this, "Please agree to the Terms & Conditions!", "Jungle_CS109",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        add(loginButton);
    }

    private void addRegisterButton() {
        registerButton = new JButton("Register");
        registerButton.addActionListener((e) -> {
            AudioPlayer.playSoundEffect("resource\\Audio\\click.wav");
            dispose();
            new RegisterFrame(jungleIcon, username_pw);
        });
        registerButton.setFont(new Font("Monaco", Font.BOLD, 17));
        add(registerButton);
    }

    public void addCheckbox() {
        checkbox = new JCheckBox("Agree to the Terms & Conditions");
        checkbox.setFocusable(false);
        checkbox.setOpaque(false);
        checkbox.setContentAreaFilled(false);
        checkbox.setBorderPainted(false);
        checkbox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1) {
                    isChecked = true;
                } else {
                    isChecked = false;
                }
            }
        });
        add(checkbox);
    }

    public void login() {
        String username = usernameField.getText();
        String password = String.valueOf(passwordField.getPassword());
        if (username.equals("")) {
            JOptionPane.showMessageDialog(null, "Username must not be blank", "Login",
                    JOptionPane.ERROR_MESSAGE);
        } else if (password.equals("")) {
            JOptionPane.showMessageDialog(null, "Password must not be blank", "Login",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            if (username_pw.getUsername_pw().containsKey(username)) {
                if (username_pw.getUsername_pw().get(username).equals(password)) {
                    isLogin = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Username and password does not match", "Login",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Such username does not exist", "Login",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
