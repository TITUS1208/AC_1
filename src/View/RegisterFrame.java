package View;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;

public class RegisterFrame extends JFrame {
    private JButton registerButton;
    private JButton backButton;

    private JLabel usernameLabel;
    private JLabel passwordLabel;

    private JTextField usernameField;
    private JPasswordField passwordField;

    private UsernamePassword username_pw;

    public RegisterFrame(ImageIcon jungleIcon, UsernamePassword username_pw) {
        this.username_pw = username_pw;
        setTitle("Jungle_CS109");
        setSize(Constant.BEGIN_FRAME_WIDTH, Constant.BEGIN_FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        getContentPane().setBackground(Color.LIGHT_GRAY); // Change soon
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(null);
        setIconImage(jungleIcon.getImage());

        addUsernameLabel();
        addPasswordLabel();
        addUsernameField();
        addPasswordField();
        addRegisterButton();
        addBackButton();

        layoutControl();
    }

    public void layoutControl() {
        usernameLabel.setBounds(50, 30, 100, 30);
        passwordLabel.setBounds(50, 80, 100, 30);
        usernameField.setBounds(180, 30, 190, 30);
        passwordField.setBounds(180, 80, 190, 30);
        registerButton.setBounds(80, 130, 120, 40);
        backButton.setBounds(220, 130, 120, 40);
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

    private void addRegisterButton() {
        registerButton = new JButton("Register");
        registerButton.setFont(new Font("Monaco", Font.BOLD, 17));
        registerButton.addActionListener((e) -> {
            String username = usernameField.getText();
            String password = String.valueOf(passwordField.getPassword());
            if (username.equals("")) {
                JOptionPane.showMessageDialog(null, "Username must not be blank", "Registration",
                        JOptionPane.ERROR_MESSAGE);
            } else if (password.equals("")) {
                JOptionPane.showMessageDialog(null, "Password must not be blank", "Registration",
                        JOptionPane.ERROR_MESSAGE);
            } else if (username_pw.getUsername_pw().containsKey(username)) {
                JOptionPane.showMessageDialog(null, "Username has already existed", "Registration",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                username_pw.setUsername_pw(username, password);
                JOptionPane.showMessageDialog(null, "Successful registration", "Registration",
                        JOptionPane.INFORMATION_MESSAGE);
                setVisible(false);
                try {
                    FileWriter fileWriter = new FileWriter("src\\User\\users.txt", true);
                    fileWriter.write("\n" + username + " " + password);
                    // fileWriter.write("\n");
                    fileWriter.close();
                } catch (Exception exp) {
                    System.out.println(exp);
                }
            }
            // System.out.println(username_pw.getUsername_pw());
        });
        add(registerButton);
    }

    private void addBackButton() {
        backButton = new JButton("Back");
        backButton.setFont(new Font("Monaco", Font.BOLD, 17));
        backButton.addActionListener((e) -> {
            dispose();
        });
        add(backButton);
    }
}
