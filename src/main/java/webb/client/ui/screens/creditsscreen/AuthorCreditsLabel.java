package webb.client.ui.screens.creditsscreen;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import webb.client.ui.constants.WebbColors;
import webb.client.ui.constants.WebbFonts;

public class AuthorCreditsLabel extends JLabel {

    public AuthorCreditsLabel(String text, Component contentPane, Component componentAbove, SpringLayout layout) {
        this(text, 0, contentPane, componentAbove, layout);
    }

    public AuthorCreditsLabel(String text, int northOffset, Component contentPane, Component componentAbove, SpringLayout layout) {
        super(text);
        this.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_32);
        this.setForeground(WebbColors.TEXT_COLOR_BLACK);
        layout.putConstraint(SpringLayout.NORTH, this, northOffset, SpringLayout.SOUTH, componentAbove);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, this, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
    }
}
