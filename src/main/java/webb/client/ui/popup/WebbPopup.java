package webb.client.ui.popup;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import webb.client.ui.components.RoundedJPanel;
import webb.client.ui.components.WebbBackButton;
import webb.client.ui.components.WebbButton;
import webb.client.ui.constants.WebbColors;
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

        // Create the rounded panel illusion
        RoundedJPanel roundedJPanel = new RoundedJPanel();
        this.setContentPane(roundedJPanel);
        this.setUndecorated(true);
        this.setBackground(new Color(0, 0, 0, 0));
        this.setModal(true);

        populateComponents(roundedJPanel, layout);

        this.add(new WebbBackButton(roundedJPanel, layout, this::close));

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

    protected abstract void populateComponents(JPanel contentPane, SpringLayout layout);
}
