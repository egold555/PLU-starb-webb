package webb.client.ui.screens.creditsscreen;

import java.awt.Container;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import webb.client.ui.components.WebbBackButton;
import webb.client.ui.constants.WebbColors;
import webb.client.ui.constants.WebbFonts;
import webb.client.ui.screens.Screen;
import webb.client.ui.screens.ScreenType;

/**
 * The credits screen.
 */
public class CreditsScreen extends Screen {

    @Override
    protected void populateComponents(Container contentPane, SpringLayout layout) {
        JLabel titleText = new JLabel("Credits");
        titleText.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_64);
        titleText.setForeground(WebbColors.TEXT_COLOR_BLACK);
        layout.putConstraint(SpringLayout.NORTH, titleText, 20, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, titleText, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
        this.add(titleText);

        JLabel madeBy1 = new JLabel("Made by the Webb Team");
        madeBy1.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_32);
        madeBy1.setForeground(WebbColors.TEXT_COLOR_BLACK);
        layout.putConstraint(SpringLayout.NORTH, madeBy1, 20, SpringLayout.SOUTH, titleText);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, madeBy1, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
        this.add(madeBy1);

        JLabel madeBy2 = new JLabel("during Spring 2023 for CS390");
        madeBy2.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_32);
        madeBy2.setForeground(WebbColors.TEXT_COLOR_BLACK);
        layout.putConstraint(SpringLayout.NORTH, madeBy2, 0, SpringLayout.SOUTH, madeBy1);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, madeBy2, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
        this.add(madeBy2);

        AuthorCreditsLabel authorEric = new AuthorCreditsLabel("Eric", 50, contentPane, madeBy2, layout);
        this.add(authorEric);

        AuthorCreditsLabel authorChris = new AuthorCreditsLabel("Chris", contentPane, authorEric, layout);
        this.add(authorChris);

        AuthorCreditsLabel authorBrandon = new AuthorCreditsLabel("Brandon", contentPane, authorChris, layout);
        this.add(authorBrandon);

        AuthorCreditsLabel authorSeth = new AuthorCreditsLabel("Seth", contentPane, authorBrandon, layout);
        this.add(authorSeth);

        this.add(new WebbBackButton(contentPane, layout, () -> {
            System.out.println("Back button pressed");
            switchScreenTo(ScreenType.MAIN_MENU);
        }));

    }
}
