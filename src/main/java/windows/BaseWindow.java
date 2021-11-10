package windows;

import javax.swing.*;
import java.awt.*;

public class BaseWindow {
    private JFrame frame;
    private JPanel panel;

    public BaseWindow() {
        this.frame = new JFrame("Service Tag Transfer App");
        this.panel = new JPanel();
    }

    public JFrame getFrame() {
        return frame;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void getPanelForWindow() {
        this.panel.setOpaque(false);
        this.frame.getContentPane().setBackground(Color.darkGray);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(500, 500);
        this.frame.add(panel);
        this.panel.setLayout(null);
    }

    public static JLabel addNewLabel(String text, Color foreground) {
        JLabel label = new JLabel(text, JLabel.CENTER);
        label.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        label.setBackground(Color.WHITE);
        label.setForeground(foreground);
        label.setOpaque(true);
        return label;
    }
}
