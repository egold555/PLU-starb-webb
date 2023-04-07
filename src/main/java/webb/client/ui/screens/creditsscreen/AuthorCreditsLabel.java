package webb.client.ui.screens.creditsscreen;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import webb.client.ui.constants.WebbColors;
import webb.client.ui.constants.WebbFonts;

/**
 * A simple label for author credits.
 */
public class AuthorCreditsLabel extends JLabel {

    /**
     * Creates a new AuthorCreditsLabel.
     * @param text The text to display.
     * @param contentPane The content pane of the screen.
     * @param componentAbove The component above this one.
     * @param layout The layout of the screen.
     */
    public AuthorCreditsLabel(String text, Component contentPane, Component componentAbove, SpringLayout layout) {
        this(text, 0, contentPane, componentAbove, layout);
    }

    /**
     * Creates a new AuthorCreditsLabel.
     * @param text The text to display.
     * @param northOffset The offset from the component above.
     * @param contentPane The content pane of the screen.
     * @param componentAbove The component above this one.
     * @param layout The layout of the screen.
     */
    public AuthorCreditsLabel(String text, int northOffset, Component contentPane, Component componentAbove, SpringLayout layout) {
        super(text);
        this.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_32);
        this.setForeground(WebbColors.TEXT_COLOR_BLACK);
        layout.putConstraint(SpringLayout.NORTH, this, northOffset, SpringLayout.SOUTH, componentAbove);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, this, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
    }
}
