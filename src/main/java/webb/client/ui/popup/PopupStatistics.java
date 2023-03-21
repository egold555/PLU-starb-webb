package webb.client.ui.popup;

import java.awt.Dimension;
import java.util.concurrent.TimeUnit;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import webb.client.ui.components.RoundedJPanel;
import webb.client.ui.constants.WebbColors;
import webb.client.ui.constants.WebbFonts;

public class PopupStatistics extends WebbPopup {

    private final String currentTitle;
    private final int gamesCompleted;
    private final int gamesMax;

    private final long solveTimeMin;
    private final long solveTimeMax;
    private final long solveTimeAverage;

    /**
     * Creates a new popup with the given title.
     *
     * @param currentTitle Title of the player
     * @param gamesCompleted Number of games completed
     * @param gamesMax Number of games max
     * @param solveTimeMin Minimum time to solve a puzzle in milliseconds
     * @param solveTimeMax Maximum time to solve a puzzle in milliseconds
     * @param solveTimeAverage Average time to solve a puzzle
     *
     */
    public PopupStatistics(String currentTitle, int gamesCompleted, int gamesMax, long solveTimeMin, long solveTimeMax, long solveTimeAverage) {
        super("My Statistics");
        this.currentTitle = currentTitle;
        this.gamesCompleted = gamesCompleted;
        this.gamesMax = gamesMax;
        this.solveTimeMin = solveTimeMin;
        this.solveTimeMax = solveTimeMax;
        this.solveTimeAverage = solveTimeAverage;
    }

    @Override
    protected void populateComponents(JPanel contentPane, SpringLayout layout) {

        RoundedJPanel panel = new RoundedJPanel();
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

        JLabel currentTitle = new JLabel(this.currentTitle);
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

        JLabel gamesCompleted = new JLabel(this.gamesCompleted + "/" + this.gamesMax);
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

        JLabel solveTimeMin = new JLabel(formatTime(this.solveTimeMin));
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

        JLabel solveTimeAvgerage = new JLabel(formatTime(this.solveTimeAverage));
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

        JLabel solveTimeMax = new JLabel(formatTime(this.solveTimeMax));
        solveTimeMax.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_32);
        solveTimeMax.setForeground(WebbColors.TEXT_COLOR_BLACK);
        panelLayout.putConstraint(SpringLayout.NORTH, solveTimeMax, 10, SpringLayout.SOUTH, labelSolveTimeMax);
        panelLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, solveTimeMax, 200, SpringLayout.HORIZONTAL_CENTER, panel);
        panel.add(solveTimeMax);
    }

    private static String formatTime(long time) {
        long minutes = TimeUnit.MILLISECONDS.toMinutes(time);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(time) - TimeUnit.MINUTES.toSeconds(minutes);
        return String.format("%02d:%02d", minutes, seconds);
    }

}
