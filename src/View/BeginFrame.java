package View;

import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class BeginFrame extends JFrame {
    private final int frameWidth;
    private final int frameHeight;
    private final int buttonWidth = 190;
    private final int buttonHeight = 60;

    private JButton beginButton;

    private ImageIcon jungleIcon;

    private JCheckBox checkbox;

    private JDialog agreeDialog;

    private boolean isChecked;
    private boolean isAgreeDialogExist;

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
        addCheckbox();
    }

    public void addBeginButton() {
        beginButton = new JButton("Begin");
        beginButton.setFont(new Font("Monaco", Font.BOLD, 17));
        beginButton.setFocusable(false);
        beginButton.setBounds(frameWidth / 2 - buttonWidth / 2 - 5, buttonHeight - 15, buttonWidth,
                buttonHeight);
        beginButton.addActionListener(e -> {
            System.out.println("beginButton being clicked");
            if (isChecked) {
                new MainFrame(30 + 7 * 70 + 30 + 250 + 30, 30 + 9 * 70
                        + 30, jungleIcon);
                this.setVisible(false);
                if (isAgreeDialogExist) {
                    agreeDialog.setVisible(false);
                }
            } else {
                addAgreeDialog();
            }
        });
        add(beginButton);
    }
    // Assume one chess size is 70*70, Chessboard would be 7*70, 9*70
    // Let status panel be 250, 9*70
    // Let the margin of every border be 30 // MainFrame

    public void addCheckbox() {
        checkbox = new JCheckBox("Agree to the Terms & Conditions");
        checkbox.setFocusable(false);
        checkbox.setBounds(frameWidth / 2 - buttonWidth / 2 - 20, buttonHeight + 50, 250, 50);
        checkbox.setOpaque(false);
        checkbox.setContentAreaFilled(false);
        checkbox.setBorderPainted(false);
        add(checkbox);
        checkbox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1) {
                    isChecked = true;
                } else {
                    isChecked = false;
                }
            }
        });
    }

    public void addAgreeDialog() {
        agreeDialog = new JDialog();
        agreeDialog.setTitle("Jungle_CS109");
        // agreeDialog.setLayout(null);
        agreeDialog.setIconImage(jungleIcon.getImage());
        agreeDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        agreeDialog.setLocationRelativeTo(this);
        agreeDialog.setSize(300, 120);
        agreeDialog.add(new JLabel("     Please agree to the Terms & Conditions!"));
        agreeDialog.setVisible(true);
        isAgreeDialogExist = true;
    }
}
