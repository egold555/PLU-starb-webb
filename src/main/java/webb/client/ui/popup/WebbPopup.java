package webb.client.ui.popup;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import webb.client.ui.WebbWindow;
import webb.client.ui.components.WebbButton;
import webb.client.ui.components.WebbRoundedJPanel;
import webb.client.ui.constants.WebbColors;
import webb.client.ui.constants.WebbFonts;
import webb.client.ui.constants.WebbImages;
import webb.client.ui.screens.Screen;

/**
 * A popup that is displayed on top of the screen.
 */
public abstract class WebbPopup extends JDialog {

    private JFrame parent;
    private final String title;
    private boolean exitButton = true;

    /**
     * Creates a new popup with no title.
     */
    public WebbPopup() {
        this(null);
    }

    /**
     * Creates a new popup with the given title.
     *
     * @param title The title of the popup.
     */
    public WebbPopup(String title) {
        this.title = title;
    }

    /**
     * Shows the popup on top of the screen.
     * @param screen The screen to show the popup on top of.
     *               use {@link #showPopup()} instead.
     */
    @Deprecated
    public final void show(Screen screen) {
        this.showPopup();
    }
    public final void showPopup() {
        this.parent = WebbWindow.getInstance();

        JPanel glassPane = new JPanel() {
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
        WebbRoundedJPanel roundedJPanel = new WebbRoundedJPanel();
        this.setContentPane(roundedJPanel);
        this.setUndecorated(true);
        this.setBackground(new Color(0, 0, 0, 0));
        this.setModal(true);

        this.setSize(parent.getWidth() - 100, parent.getHeight() - 100);
        this.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));

        populateComponents(roundedJPanel, layout);

        if(exitButton) {
            WebbButton dialogCloseButton = new WebbButton(WebbImages.POPUP_CLOSE, 42, 42, (self, rightClicked) -> close());
            layout.putConstraint(SpringLayout.NORTH, dialogCloseButton, 10, SpringLayout.NORTH, roundedJPanel);
            layout.putConstraint(SpringLayout.EAST, dialogCloseButton, -10, SpringLayout.EAST, roundedJPanel);
            this.add(dialogCloseButton);
        }

        if(title != null) {
            JLabel titleLabel = new JLabel(title);
            titleLabel.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_32);
            titleLabel.setForeground(WebbColors.TEXT_COLOR_BLACK);
            layout.putConstraint(SpringLayout.NORTH, titleLabel, 10, SpringLayout.NORTH, roundedJPanel);
            layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, titleLabel, 0, SpringLayout.HORIZONTAL_CENTER, roundedJPanel);
            this.add(titleLabel);
        }

        this.setLayout(layout);



        // Call after setting the size
        this.setLocationRelativeTo(this.parent);


        this.setVisible(true);
    }

    /**
     * Closes the popup.
     */
    protected void close() {
        this.dispose();
        parent.getGlassPane().setVisible(false);
        parent.setEnabled(true);
        parent.toFront();
        parent.setFocusableWindowState(true);
    }

    /**
     * Populates the components of the popup.
     *
     * @param contentPane The content pane of the popup.
     * @param layout The layout of the popup.
     */
    protected abstract void populateComponents(JPanel contentPane, SpringLayout layout);

    /**
     * Sets whether the popup should have an exit button.
     *
     * @param exitButton Whether the popup should have an exit button.
     */
    public void setExitButton(boolean exitButton) {this.exitButton = exitButton;}
}
