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

    private boolean isChecked;

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
        jungleIcon = new ImageIcon("resource/Icon/jungleIcon.png");
        setIconImage(jungleIcon.getImage());

        addBeginButton();
        addCheckbox();

        layoutControl();
    }

    public void layoutControl() {
        beginButton.setBounds(frameWidth / 2 - buttonWidth / 2 - 5, buttonHeight - 15, buttonWidth,
                buttonHeight);
        checkbox.setBounds(frameWidth / 2 - buttonWidth / 2 - 20, buttonHeight + 50, 250, 50);
    }

    public void addBeginButton() {
        beginButton = new JButton("Begin");
        beginButton.setFont(new Font("Monaco", Font.BOLD, 17));
        beginButton.setFocusable(false);

        beginButton.addActionListener(e -> {
            System.out.println("beginButton being clicked");
            if (isChecked) {
                new Chessboard();
                new MainFrame(Constant.MAIN_FRAME_WIDTH, Constant.MAIN_FRAME_HEIGHT, jungleIcon);
                this.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this, "Please agree to the Terms & Conditions!", "Jungle_CS109",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
        add(beginButton);
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
}
