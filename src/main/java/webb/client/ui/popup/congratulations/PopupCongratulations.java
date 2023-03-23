package webb.client.ui.popup.congratulations;

import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import webb.client.ui.components.WebbButton;
import webb.client.ui.components.WebbProgressBar;
import webb.client.ui.components.WebbRoundedJPanel;
import webb.client.ui.components.WebbSimpleImage;
import webb.client.ui.constants.WebbColors;
import webb.client.ui.constants.WebbFonts;
import webb.client.ui.constants.WebbImages;
import webb.client.ui.helpers.WebbTextUtilities;
import webb.client.ui.popup.WebbPopup;

public class PopupCongratulations extends WebbPopup {

    private final long TIME;
    private final int PROGRESS_MIN;
    private final int PROGRESS_MAX;
    private final int PROGRESS_CURRENT;
    private final String CURRENT_TITLE;
    private final String NEXT_TITLE;

    public PopupCongratulations(long time, int progressMin, int progressMax, int progressCurrent, String currentTitle, String nextTitle) {
        super("Congratulations!");
        this.setExitButton(false);
        this.TIME = time;
        this.PROGRESS_MIN = progressMin;
        this.PROGRESS_MAX = progressMax;
        this.PROGRESS_CURRENT = progressCurrent;
        this.CURRENT_TITLE = currentTitle;
        this.NEXT_TITLE = nextTitle;
    }

    @Override
    protected void populateComponents(JPanel contentPane, SpringLayout layout) {

        //------------ Stars ------------
        WebbSimpleImage starBigLeft = new WebbSimpleImage(WebbImages.POPUP_CONGRATULATIONS_STAR, 59, 59);
        layout.putConstraint(SpringLayout.NORTH, starBigLeft, 0, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, starBigLeft, -175, SpringLayout.HORIZONTAL_CENTER, contentPane);
        contentPane.add(starBigLeft);

        WebbSimpleImage starBigRight = new WebbSimpleImage(WebbImages.POPUP_CONGRATULATIONS_STAR, 59, 59);
        layout.putConstraint(SpringLayout.NORTH, starBigRight, 0, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, starBigRight, 175, SpringLayout.HORIZONTAL_CENTER, contentPane);
        contentPane.add(starBigRight);

        WebbSimpleImage starSmallLeft = new WebbSimpleImage(WebbImages.POPUP_CONGRATULATIONS_STAR_ROTATED, 31, 31);
        layout.putConstraint(SpringLayout.NORTH, starSmallLeft, 20, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, starSmallLeft, -220, SpringLayout.HORIZONTAL_CENTER, contentPane);
        contentPane.add(starSmallLeft);

        WebbSimpleImage starSmallRight = new WebbSimpleImage(WebbImages.POPUP_CONGRATULATIONS_STAR_ROTATED, 31, 31);
        layout.putConstraint(SpringLayout.NORTH, starSmallRight, 20, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, starSmallRight, 220, SpringLayout.HORIZONTAL_CENTER, contentPane);
        contentPane.add(starSmallRight);


        //------------ Solve Time ------------
        WebbRoundedJPanel solveTimePanel = new WebbRoundedJPanel();
        SpringLayout solveTimePanelLayout = new SpringLayout();
        solveTimePanel.setLayout(solveTimePanelLayout);
        solveTimePanel.setBackground(WebbColors.B7);
        layout.putConstraint(SpringLayout.NORTH, solveTimePanel, 100, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, solveTimePanel, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
        solveTimePanel.setPreferredSize(new Dimension(244, 30));
        contentPane.add(solveTimePanel);

        JLabel solveTimeLabel = new JLabel("Solve Time");
        solveTimeLabel.setForeground(WebbColors.TEXT_COLOR_BLACK);
        solveTimeLabel.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_24);
        solveTimePanelLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, solveTimeLabel, 0, SpringLayout.HORIZONTAL_CENTER, solveTimePanel);
        solveTimePanelLayout.putConstraint(SpringLayout.VERTICAL_CENTER, solveTimeLabel, 0, SpringLayout.VERTICAL_CENTER, solveTimePanel);
        solveTimePanel.add(solveTimeLabel);

        JLabel solveTimeValue = new JLabel(WebbTextUtilities.formatMinSec(TIME));
        solveTimeValue.setForeground(WebbColors.TEXT_COLOR_BLACK);
        solveTimeValue.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_48);

        layout.putConstraint(SpringLayout.NORTH, solveTimeValue, 50, SpringLayout.NORTH, solveTimePanel);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, solveTimeValue, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
        contentPane.add(solveTimeValue);


        //------------ Progress Bar ------------
        WebbProgressBar progressBar = new WebbProgressBar(WebbColors.c90, WebbColors.D9);
        progressBar.setMinimum(PROGRESS_MIN);
        progressBar.setMaximum(PROGRESS_MAX);
        progressBar.setValue(PROGRESS_CURRENT);
        progressBar.setPreferredSize(new Dimension(400, 40));
        layout.putConstraint(SpringLayout.NORTH, progressBar, 200, SpringLayout.SOUTH, solveTimePanel);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, progressBar, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
        contentPane.add(progressBar);

        JLabel currentRankLabel = new JLabel(CURRENT_TITLE);
        currentRankLabel.setForeground(WebbColors.TEXT_COLOR_BLACK);
        currentRankLabel.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_20);
        layout.putConstraint(SpringLayout.SOUTH, currentRankLabel, -10, SpringLayout.NORTH, progressBar);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, currentRankLabel, -160, SpringLayout.HORIZONTAL_CENTER, contentPane);
        contentPane.add(currentRankLabel);

        JLabel nextRankLabel = new JLabel(NEXT_TITLE);
        nextRankLabel.setForeground(WebbColors.TEXT_COLOR_BLACK);
        nextRankLabel.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_20);
        layout.putConstraint(SpringLayout.SOUTH, nextRankLabel, -10, SpringLayout.NORTH, progressBar);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, nextRankLabel, 160, SpringLayout.HORIZONTAL_CENTER, contentPane);
        contentPane.add(nextRankLabel);


        //------------ Buttons ------------
        WebbButton restartButton = new WebbButton("Restart", () -> {
            System.out.println("Restart button clicked");
        });
        restartButton.setPreferredSize(new Dimension(142, 43));
        layout.putConstraint(SpringLayout.SOUTH, restartButton, -20, SpringLayout.SOUTH, contentPane);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, restartButton, -75, SpringLayout.HORIZONTAL_CENTER, contentPane);
        contentPane.add(restartButton);

        WebbButton exitButton = new WebbButton("Exit", () -> {
            System.out.println("Exit button clicked");
            this.close();
        });
        exitButton.setPreferredSize(new Dimension(142, 43));
        layout.putConstraint(SpringLayout.SOUTH, exitButton, -20, SpringLayout.SOUTH, contentPane);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, exitButton, 75, SpringLayout.HORIZONTAL_CENTER, contentPane);
        contentPane.add(exitButton);


    }
}
