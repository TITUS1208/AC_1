package View;

import javax.swing.*;

public class MainFrame extends JFrame {
    private final int WIDTH;
    private final int HEIGHT;

    public MainFrame(int width, int height) {
        setTitle("Jungle_CS109");
        this.WIDTH = width;
        this.HEIGHT = height;
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
    }
}
