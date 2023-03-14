package webb.client.uitest.screens.selectpuzzle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TestModal extends JDialog {
    private final JFrame parent;
    private final JPanel glassPane;
    TestModal(JFrame parent) {
        this.parent = parent;

        glassPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                g.setColor(new Color(0, 0, 0, 128)); // semi-transparent black
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        glassPane.setOpaque(false);
        parent.setGlassPane(glassPane);
        glassPane.setVisible(true);

        this.setLocationRelativeTo(this.parent);

        this.setSize(100, 100);
        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                parent.getGlassPane().setVisible(false);
            }
        });
    }
}
