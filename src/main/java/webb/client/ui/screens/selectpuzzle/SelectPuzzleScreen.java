package webb.client.ui.screens.selectpuzzle;

import java.awt.Container;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import webb.client.ui.components.WebbBackButton;
import webb.client.ui.components.WebbButton;
import webb.client.ui.constants.WebbColors;
import webb.client.ui.constants.WebbFonts;
import webb.client.ui.constants.WebbImages;
import webb.client.ui.helpers.WebbWebUtilities;
import webb.client.ui.popup.leaderboard.LeaderboardScore;
import webb.client.ui.popup.leaderboard.PopupLeaderboard;
import webb.client.ui.popup.statistics.PopupStatistics;
import webb.client.ui.popup.statistics.StatisticsData;
import webb.client.ui.screens.Screen;
import webb.client.ui.screens.ScreenType;

/**
 * The screen that allows the user to select a puzzle to play.
 */
public class SelectPuzzleScreen extends Screen {

        private static final LeaderboardScore[] DEFAULT_LEADERBOARD_SCORE = new LeaderboardScore[]{new LeaderboardScore("Error fetching leaderboard data.", 0, 0)};
        private LeaderboardScore[] leaderboardScores = DEFAULT_LEADERBOARD_SCORE;

        private static final StatisticsData DEFAULT_STATISTICS_DATA = new StatisticsData("Error fetching statistics data.", 0, 0, 0, 0, 0);
        private StatisticsData statisticsData = DEFAULT_STATISTICS_DATA;

        public static final Level[] DEFAULT_LEVELS = new Level[]{new Level("Error fetching", 0, false, null)};
        private Level[] levels = DEFAULT_LEVELS;

        private JPanel puzzlePanel;

        @Override
        protected void populateComponents(Container contentPane, SpringLayout layout) {

                JLabel titleText = new JLabel("Select Puzzle");
                titleText.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_64);
                titleText.setForeground(WebbColors.TEXT_COLOR_BLACK);
                layout.putConstraint(SpringLayout.NORTH, titleText, 20, SpringLayout.NORTH, contentPane);
                layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, titleText, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
                this.add(titleText);

                puzzlePanel = new JPanel();
                puzzlePanel.setOpaque(false);
                //puzzlePanel.setBackground(WebbColors.B7);
                //puzzlePanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                layout.putConstraint(SpringLayout.NORTH, puzzlePanel, 20, SpringLayout.SOUTH, titleText);
                layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, puzzlePanel, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
                layout.putConstraint(SpringLayout.EAST, puzzlePanel, -20, SpringLayout.EAST, contentPane);
                layout.putConstraint(SpringLayout.WEST, puzzlePanel, 20, SpringLayout.WEST, contentPane);
                layout.putConstraint(SpringLayout.SOUTH, puzzlePanel, -70, SpringLayout.SOUTH, contentPane);
                //puzzlePanel.setLayout(new GridLayout(10, 10));

                // Default error level
                puzzlePanel.add(new PuzzleButton(DEFAULT_LEVELS[0]));

                this.add(puzzlePanel);


                JPanel bottomBar = new JPanel();
                SpringLayout bottomBarLayout = new SpringLayout();
                bottomBar.setBackground(WebbColors.D9);
                layout.putConstraint(SpringLayout.NORTH, bottomBar, 20, SpringLayout.SOUTH, puzzlePanel);
                layout.putConstraint(SpringLayout.EAST, bottomBar, -20, SpringLayout.EAST, contentPane);
                layout.putConstraint(SpringLayout.WEST, bottomBar, 20, SpringLayout.WEST, contentPane);
                layout.putConstraint(SpringLayout.SOUTH, bottomBar, 0, SpringLayout.SOUTH, contentPane);



                WebbButton puzzleBack = new WebbButton(WebbImages.PUZZLE_SELECTION_ARROW_BACK, 42, 42, (self, rightClicked) -> {
                        System.out.println("Puzzle back button pressed");
                });
                bottomBarLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, puzzleBack, -200, SpringLayout.HORIZONTAL_CENTER, bottomBar);
                bottomBarLayout.putConstraint(SpringLayout.VERTICAL_CENTER, puzzleBack, 0, SpringLayout.VERTICAL_CENTER, bottomBar);

                bottomBar.add(puzzleBack);


                WebbButton puzzleForward = new WebbButton(WebbImages.PUZZLE_SELECTION_ARROW_FORWARD, 42, 42, (self, rightClicked) -> {
                        System.out.println("Puzzle forward button pressed");
                });
                bottomBarLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, puzzleForward, 200, SpringLayout.HORIZONTAL_CENTER, bottomBar);
                bottomBarLayout.putConstraint(SpringLayout.VERTICAL_CENTER, puzzleForward, 0, SpringLayout.VERTICAL_CENTER, bottomBar);

                bottomBar.add(puzzleForward);


                WebbButton trophy = new WebbButton(WebbImages.PUZZLE_SELECTION_BUTTON_TROPHY, 42, 42, (self, rightClicked) -> {
                        System.out.println("Trophy button pressed");

                        //TODO: Show actual leaderboard data
                        showPopup(new PopupLeaderboard(
                                leaderboardScores
                        ));
                });
                bottomBarLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, trophy, -32, SpringLayout.HORIZONTAL_CENTER, bottomBar);
                bottomBarLayout.putConstraint(SpringLayout.VERTICAL_CENTER, trophy, 0, SpringLayout.VERTICAL_CENTER, bottomBar);

                bottomBar.add(trophy);


                WebbButton stats = new WebbButton(WebbImages.PUZZLE_SELECTION_BUTTON_STATS, 42, 42, (self, rightClicked) -> {
                        System.out.println("Stats button pressed");

                        //TODO: Replace with actual stats
                        showPopup(new PopupStatistics(
                                statisticsData
                        ));
                });
                bottomBarLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, stats, 32, SpringLayout.HORIZONTAL_CENTER, bottomBar);
                bottomBarLayout.putConstraint(SpringLayout.VERTICAL_CENTER, stats, 0, SpringLayout.VERTICAL_CENTER, bottomBar);

                bottomBar.add(stats);


                bottomBar.setLayout(bottomBarLayout);
                this.add(bottomBar);


                this.add(new WebbBackButton(contentPane, layout, (self, rightClicked) -> {
                        System.out.println("Back button pressed");
                        switchScreenTo(ScreenType.MAIN_MENU);
                }));
        }

        @Override
        public void onShow() {
                WebbWebUtilities.getRequest(
                        "leaderboard.json",
                        LeaderboardScore[].class,
                        DEFAULT_LEADERBOARD_SCORE,
                        reply -> {leaderboardScores = reply;}
                );

                WebbWebUtilities.getRequest(
                        "user-statistics.json",
                        StatisticsData.class,
                        DEFAULT_STATISTICS_DATA,
                        reply -> {statisticsData = reply;}
                );

                repopulateLevels();
        }

        public void setLevels(Level[] levels) {
                this.levels = levels;
        }

        private void repopulateLevels() {
                puzzlePanel.removeAll();
                for (Level level : levels) {
                        PuzzleButton button = new PuzzleButton(level);
                        puzzlePanel.add(button);
                }
                puzzlePanel.revalidate();
        }

        public boolean hasPopulatedLevelsYet() {
                return levels != DEFAULT_LEVELS;
        }
}
