package webb.client.ui.popup.statistics;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import webb.client.ui.components.WebbRoundedJPanel;
import webb.client.ui.constants.WebbColors;
import webb.client.ui.constants.WebbFonts;
import webb.client.ui.helpers.WebbTextUtilities;
import webb.client.ui.popup.WebbPopup;

/**
 * Popup that displays the statistics of the player.
 */
public class PopupStatistics extends WebbPopup {

    private final StatisticsData statisticsData;

    /**
     * Creates a new popup with the given title.
     *
     * @param statisticsData StatisticsData object containing the statistics of the player
     *
     */
    public PopupStatistics(@JsonProperty StatisticsData statisticsData) {
        super("My Statistics");
        this.statisticsData = statisticsData;
    }

    @Override
    protected void populateComponents(JPanel contentPane, SpringLayout layout) {

        WebbRoundedJPanel panel = new WebbRoundedJPanel();
        SpringLayout panelLayout = new SpringLayout();
        panel.setLayout(panelLayout);
        panel.setBackground(WebbColors.B7);

        // Set the size of the panel to be 100px smaller than the popup
        int width = this.getWidth() - 100;
        int height = this.getHeight() - 100;
        panel.setPreferredSize(new Dimension(width, height));


        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, panel, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, panel, 0, SpringLayout.VERTICAL_CENTER, contentPane);

        add(panel);

        //------------ Current Title ------------
        JLabel labelCurrentTitle = new JLabel("Current Title");
        labelCurrentTitle.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_32);
        labelCurrentTitle.setForeground(WebbColors.TEXT_COLOR_BLACK);
        panelLayout.putConstraint(SpringLayout.NORTH, labelCurrentTitle, 20, SpringLayout.NORTH, panel);
        panelLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, labelCurrentTitle, 0, SpringLayout.HORIZONTAL_CENTER, panel);
        panel.add(labelCurrentTitle);

        JLabel currentTitle = new JLabel(this.statisticsData.getCurrentTitle());
        currentTitle.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_32);
        currentTitle.setForeground(WebbColors.TEXT_COLOR_BLACK);
        panelLayout.putConstraint(SpringLayout.NORTH, currentTitle, 10, SpringLayout.SOUTH, labelCurrentTitle);
        panelLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, currentTitle, 0, SpringLayout.HORIZONTAL_CENTER, panel);
        panel.add(currentTitle);

        //------------ Games Completed ------------
        JLabel labelGamesCompleted = new JLabel("Games Completed");
        labelGamesCompleted.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_32);
        labelGamesCompleted.setForeground(WebbColors.TEXT_COLOR_BLACK);
        panelLayout.putConstraint(SpringLayout.NORTH, labelGamesCompleted, 50, SpringLayout.SOUTH, currentTitle);
        panelLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, labelGamesCompleted, 0, SpringLayout.HORIZONTAL_CENTER, panel);
        panel.add(labelGamesCompleted);

        JLabel gamesCompleted = new JLabel(this.statisticsData.getGamesCompleted() + "/" + this.statisticsData.getGamesMax());
        gamesCompleted.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_32);
        gamesCompleted.setForeground(WebbColors.TEXT_COLOR_BLACK);
        panelLayout.putConstraint(SpringLayout.NORTH, gamesCompleted, 10, SpringLayout.SOUTH, labelGamesCompleted);
        panelLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, gamesCompleted, 0, SpringLayout.HORIZONTAL_CENTER, panel);
        panel.add(gamesCompleted);

        //------------ Solve Times ------------
        JLabel labelSolveTime = new JLabel("Solve Times");
        labelSolveTime.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_32);
        labelSolveTime.setForeground(WebbColors.TEXT_COLOR_BLACK);
        panelLayout.putConstraint(SpringLayout.NORTH, labelSolveTime, 50, SpringLayout.SOUTH, gamesCompleted);
        panelLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, labelSolveTime, 0, SpringLayout.HORIZONTAL_CENTER, panel);
        panel.add(labelSolveTime);

        //----------------- Min -----------------
        JLabel labelSolveTimeMin = new JLabel("Min");
        labelSolveTimeMin.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_32);
        labelSolveTimeMin.setForeground(WebbColors.TEXT_COLOR_BLACK);
        panelLayout.putConstraint(SpringLayout.NORTH, labelSolveTimeMin, 10, SpringLayout.SOUTH, labelSolveTime);
        panelLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, labelSolveTimeMin, -200, SpringLayout.HORIZONTAL_CENTER, panel);
        panel.add(labelSolveTimeMin);

        JLabel solveTimeMin = new JLabel(WebbTextUtilities.formatMinSec(this.statisticsData.getSolveTimeMin()));
        solveTimeMin.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_32);
        solveTimeMin.setForeground(WebbColors.TEXT_COLOR_BLACK);
        panelLayout.putConstraint(SpringLayout.NORTH, solveTimeMin, 10, SpringLayout.SOUTH, labelSolveTimeMin);
        panelLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, solveTimeMin, -200, SpringLayout.HORIZONTAL_CENTER, panel);
        panel.add(solveTimeMin);


        //----------------- Abg -----------------
        JLabel labelSolveTimeAverage = new JLabel("Avg");
        labelSolveTimeAverage.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_32);
        labelSolveTimeAverage.setForeground(WebbColors.TEXT_COLOR_BLACK);
        panelLayout.putConstraint(SpringLayout.NORTH, labelSolveTimeAverage, 10, SpringLayout.SOUTH, labelSolveTime);
        panelLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, labelSolveTimeAverage, 0, SpringLayout.HORIZONTAL_CENTER, panel);
        panel.add(labelSolveTimeAverage);

        JLabel solveTimeAvgerage = new JLabel(WebbTextUtilities.formatMinSec(this.statisticsData.getSolveTimeAverage()));
        solveTimeAvgerage.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_32);
        solveTimeAvgerage.setForeground(WebbColors.TEXT_COLOR_BLACK);
        panelLayout.putConstraint(SpringLayout.NORTH, solveTimeAvgerage, 10, SpringLayout.SOUTH, labelSolveTimeAverage);
        panelLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, solveTimeAvgerage, 0, SpringLayout.HORIZONTAL_CENTER, panel);
        panel.add(solveTimeAvgerage);

        //----------------- Max -----------------
        JLabel labelSolveTimeMax = new JLabel("Max");
        labelSolveTimeMax.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_32);
        labelSolveTimeMax.setForeground(WebbColors.TEXT_COLOR_BLACK);
        panelLayout.putConstraint(SpringLayout.NORTH, labelSolveTimeMax, 10, SpringLayout.SOUTH, labelSolveTime);
        panelLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, labelSolveTimeMax, 200, SpringLayout.HORIZONTAL_CENTER, panel);
        panel.add(labelSolveTimeMax);

        JLabel solveTimeMax = new JLabel(WebbTextUtilities.formatMinSec(this.statisticsData.getSolveTimeMax()));
        solveTimeMax.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_32);
        solveTimeMax.setForeground(WebbColors.TEXT_COLOR_BLACK);
        panelLayout.putConstraint(SpringLayout.NORTH, solveTimeMax, 10, SpringLayout.SOUTH, labelSolveTimeMax);
        panelLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, solveTimeMax, 200, SpringLayout.HORIZONTAL_CENTER, panel);
        panel.add(solveTimeMax);
    }

}
