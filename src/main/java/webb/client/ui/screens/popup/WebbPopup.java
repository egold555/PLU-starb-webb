package webb.client.ui.screens.popup;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import webb.client.ui.components.WebbButton;
import webb.client.ui.constants.WebbImages;
import webb.client.ui.screens.Screen;

public abstract class WebbPopup extends JDialog {

    private JFrame parent;
    private JPanel glassPane;

    public void show(Screen screen) {
        this.parent = (JFrame) SwingUtilities.getRootPane(screen).getParent();

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

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                close();
            }
        });

        parent.setEnabled(false);
        parent.setAlwaysOnTop(false);
        parent.setFocusableWindowState(false);

        this.setAlwaysOnTop(true);

        SpringLayout layout = new SpringLayout();
        populateComponents(this, layout);
        WebbButton closeButton = new WebbButton(WebbImages.ARROW_PAGE_BACK, 42, 42);

        closeButton.addActionListener(e -> {
            close();
        });

        layout.putConstraint(SpringLayout.NORTH, closeButton, 10, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, closeButton, 10, SpringLayout.WEST, this);
        this.add(closeButton);

        this.setLayout(layout);

        this.setSize(parent.getWidth() / 2, parent.getHeight() / 2);
        this.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));

        // Call after setting the size
        this.setLocationRelativeTo(this.parent);
        this.setVisible(true);
    }

    private void close() {
        this.dispose();
        parent.getGlassPane().setVisible(false);
        parent.setEnabled(true);
        parent.setAlwaysOnTop(true);
        parent.setFocusableWindowState(true);
    }

    protected abstract void populateComponents(JDialog contentPane, SpringLayout layout);
}
