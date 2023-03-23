package webb.client.ui.screens.puzzlescreen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import webb.client.ui.components.WebbRoundedJPanel;
import webb.client.ui.components.WebbSimpleImage;
import webb.client.ui.constants.WebbColors;
import webb.client.ui.constants.WebbFonts;
import webb.client.ui.constants.WebbImages;

public class PuzzleSideScreen extends WebbRoundedJPanel {

    public PuzzleSideScreen() {
        this.setBackground(WebbColors.c90);
        SpringLayout layout = new SpringLayout();

        this.setPreferredSize( new Dimension(230, 0) );

        //----------------- Cut between panels -----------------
        JPanel cutPanel = new JPanel();
        cutPanel.setBackground(WebbColors.c90);
        cutPanel.setPreferredSize(new Dimension(0, 10));
        layout.putConstraint(SpringLayout.NORTH, cutPanel, 100, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, cutPanel, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, cutPanel, -5, SpringLayout.EAST, this);
        this.add(cutPanel);

        //----------------- Inner panel -----------------
        JPanel innerPanel = new WebbRoundedJPanel();
        SpringLayout innerLayout = new SpringLayout();
        innerPanel.setBackground(WebbColors.D9);

        layout.putConstraint(SpringLayout.NORTH, innerPanel, 10, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.EAST, innerPanel, -10, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.WEST, innerPanel, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.SOUTH, innerPanel, -10, SpringLayout.SOUTH, this);
        this.add(innerPanel);

        //----------------- Upper Panel -----------------

        JLabel starLabel = new JLabel("1");
        starLabel.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_32);
        starLabel.setForeground(WebbColors.TEXT_COLOR_WHITE);
        innerLayout.putConstraint(SpringLayout.NORTH, starLabel, 25, SpringLayout.NORTH, innerPanel);
        innerLayout.putConstraint(SpringLayout.WEST, starLabel, 31, SpringLayout.WEST, innerPanel);
        innerPanel.add(starLabel);

        WebbSimpleImage starImage = new WebbSimpleImage(WebbImages.PLAY_PUZZLE_STAR, 51, 51);
        innerLayout.putConstraint(SpringLayout.NORTH, starImage, 15, SpringLayout.NORTH, innerPanel);
        innerLayout.putConstraint(SpringLayout.WEST, starImage, 15, SpringLayout.WEST, innerPanel);
        innerPanel.add(starImage);

        JLabel puzzleNumber = new JLabel("Puzzle 4");
        puzzleNumber.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_32);
        puzzleNumber.setForeground(WebbColors.TEXT_COLOR_BLACK);
        innerLayout.putConstraint(SpringLayout.NORTH, puzzleNumber, 25, SpringLayout.NORTH, innerPanel);
        innerLayout.putConstraint(SpringLayout.WEST, puzzleNumber, 20, SpringLayout.EAST, starLabel);
        innerPanel.add(puzzleNumber);




        innerPanel.setLayout(innerLayout);
        this.setLayout(layout);
    }
}
