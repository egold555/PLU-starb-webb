package webb.client.ui.screens;

import java.awt.Container;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import webb.client.ui.WebbWindow;
import webb.client.ui.components.WebbButton;
import webb.client.ui.constants.WebbColors;
import webb.client.ui.constants.WebbFonts;
import webb.client.ui.constants.WebbImages;

public class CreditsScreen extends Screen {

    public CreditsScreen() {
        super();
    }

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

        JLabel authorEric = new JLabel("Eric");
        authorEric.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_32);
        authorEric.setForeground(WebbColors.TEXT_COLOR_BLACK);
        layout.putConstraint(SpringLayout.NORTH, authorEric, 50, SpringLayout.SOUTH, madeBy2);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, authorEric, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
        this.add(authorEric);

        JLabel authorChris = new JLabel("Chris");
        authorChris.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_32);
        authorChris.setForeground(WebbColors.TEXT_COLOR_BLACK);
        layout.putConstraint(SpringLayout.NORTH, authorChris, 0, SpringLayout.SOUTH, authorEric);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, authorChris, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
        this.add(authorChris);

        JLabel authorBrandon = new JLabel("Brandon");
        authorBrandon.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_32);
        authorBrandon.setForeground(WebbColors.TEXT_COLOR_BLACK);
        layout.putConstraint(SpringLayout.NORTH, authorBrandon, 0, SpringLayout.SOUTH, authorChris);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, authorBrandon, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
        this.add(authorBrandon);

        JLabel authorSeth = new JLabel("Seth");
        authorSeth.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_32);
        authorSeth.setForeground(WebbColors.TEXT_COLOR_BLACK);
        layout.putConstraint(SpringLayout.NORTH, authorSeth, 0, SpringLayout.SOUTH, authorBrandon);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, authorSeth, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
        this.add(authorSeth);

        WebbButton backButton = new WebbButton(WebbImages.ARROW_PAGE_BACK, 42, 42);
        layout.putConstraint(SpringLayout.NORTH, backButton, 10, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST, backButton, 10, SpringLayout.WEST, contentPane);
        backButton.addActionListener(e -> {
            System.out.println("Back button pressed");
        });
        this.add(backButton);
    }
}
