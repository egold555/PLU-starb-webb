package webb.client.ui.screens.selectpuzzle;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import webb.client.ui.components.WebbBackButton;
import webb.client.ui.components.WebbButton;
import webb.client.ui.constants.WebbColors;
import webb.client.ui.constants.WebbFonts;
import webb.client.ui.constants.WebbImages;
import webb.client.ui.helpers.http.WebbWebUtilities;
import webb.client.ui.popup.leaderboard.PopupLeaderboard;
import webb.client.ui.popup.statistics.PopupStatistics;
import webb.client.ui.screens.Screen;
import webb.client.ui.screens.ScreenType;
import webb.shared.dtos.leaderboard.LeaderboardDTO;
import webb.shared.dtos.leaderboard.LeaderboardEntryDTO;
import webb.shared.dtos.puzzle.PuzzleLevelDTO;
import webb.shared.dtos.user.UserDTO;
import webb.shared.dtos.user.UserStatsDTO;

/**
 * The screen that allows the user to select a puzzle to play.
 */
public class SelectPuzzleScreen extends Screen {

        private static final LeaderboardDTO DEFAULT_LEADERBOARD_SCORE = new LeaderboardDTO(List.of(
                new LeaderboardEntryDTO("Error fetching leaderboard data.", 0)
        ));
        private LeaderboardDTO leaderboardScores = DEFAULT_LEADERBOARD_SCORE;

        private static final UserStatsDTO DEFAULT_STATISTICS_DATA = new UserStatsDTO(0, 0, 0, 0, 0, "Error fetching statistics data.");
        private UserStatsDTO statisticsData = DEFAULT_STATISTICS_DATA;

        public static final PuzzleLevelDTO[] DEFAULT_LEVELS = new PuzzleLevelDTO[]{new PuzzleLevelDTO(-1, null, null, 0, 0, 0)};
        private PuzzleLevelDTO[] levels = DEFAULT_LEVELS;

        private List<List<PuzzleLevelDTO>> levelPages = new ArrayList<>();

        private int currentPage = 0;

        private JPanel puzzlePanel;

        private WebbButton puzzleForward;
        private WebbButton puzzleBack;

        private JLabel pageNumberLabel;

        private int[] completedLevels = new int[0];

        @Override
        protected void populateComponents(Container contentPane, SpringLayout layout) {

                JLabel titleText = new JLabel("Select Puzzle");
                titleText.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_64);
                titleText.setForeground(WebbColors.TEXT_COLOR_BLACK);
                layout.putConstraint(SpringLayout.NORTH, titleText, 20, SpringLayout.NORTH, contentPane);
                layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, titleText, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
                this.add(titleText);

                puzzlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
                puzzlePanel.setOpaque(false);
                //puzzlePanel.setBackground(WebbColors.B7);
                //puzzlePanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                layout.putConstraint(SpringLayout.NORTH, puzzlePanel, 20, SpringLayout.SOUTH, titleText);
                layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, puzzlePanel, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
                layout.putConstraint(SpringLayout.EAST, puzzlePanel, -20, SpringLayout.EAST, contentPane);
                layout.putConstraint(SpringLayout.WEST, puzzlePanel, 20, SpringLayout.WEST, contentPane);
                layout.putConstraint(SpringLayout.SOUTH, puzzlePanel, -70, SpringLayout.SOUTH, contentPane);

                // Default error level
                puzzlePanel.add(new PuzzleButton(DEFAULT_LEVELS[0]));

                this.add(puzzlePanel);


                JPanel bottomBar = new JPanel();
                SpringLayout bottomBarLayout = new SpringLayout();
                bottomBar.setBackground(WebbColors.D9);
                layout.putConstraint(SpringLayout.NORTH, bottomBar, 20, SpringLayout.SOUTH, puzzlePanel);
                layout.putConstraint(SpringLayout.EAST, bottomBar, 0, SpringLayout.EAST, contentPane);
                layout.putConstraint(SpringLayout.WEST, bottomBar, 0, SpringLayout.WEST, contentPane);
                layout.putConstraint(SpringLayout.SOUTH, bottomBar, 0, SpringLayout.SOUTH, contentPane);



                puzzleBack = new WebbButton(WebbImages.PUZZLE_SELECTION_ARROW_BACK, 42, 42, (self, rightClicked) -> {
                        System.out.println("Puzzle back button pressed");
                        if(currentPage > 0) {
                                currentPage--;
                                showPuzzlePanelPage(currentPage);
                        }
                });
                bottomBarLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, puzzleBack, -200, SpringLayout.HORIZONTAL_CENTER, bottomBar);
                bottomBarLayout.putConstraint(SpringLayout.VERTICAL_CENTER, puzzleBack, 0, SpringLayout.VERTICAL_CENTER, bottomBar);

                bottomBar.add(puzzleBack);


                puzzleForward = new WebbButton(WebbImages.PUZZLE_SELECTION_ARROW_FORWARD, 42, 42, (self, rightClicked) -> {
                        System.out.println("Puzzle forward button pressed");
                        if(currentPage < levelPages.size() - 1) {
                                currentPage++;
                                showPuzzlePanelPage(currentPage);
                        }
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
                bottomBarLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, trophy, -92, SpringLayout.HORIZONTAL_CENTER, bottomBar);
                bottomBarLayout.putConstraint(SpringLayout.VERTICAL_CENTER, trophy, 0, SpringLayout.VERTICAL_CENTER, bottomBar);

                bottomBar.add(trophy);

                pageNumberLabel = new JLabel("0");
                pageNumberLabel.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_32);
                pageNumberLabel.setForeground(WebbColors.c6C);
                bottomBarLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, pageNumberLabel, 0, SpringLayout.HORIZONTAL_CENTER, bottomBar);
                bottomBarLayout.putConstraint(SpringLayout.VERTICAL_CENTER, pageNumberLabel, 0, SpringLayout.VERTICAL_CENTER, bottomBar);
                bottomBar.add(pageNumberLabel);

                WebbButton stats = new WebbButton(WebbImages.PUZZLE_SELECTION_BUTTON_STATS, 42, 42, (self, rightClicked) -> {
                        System.out.println("Stats button pressed");

                        //TODO: Replace with actual stats
                        showPopup(new PopupStatistics(
                                statisticsData
                        ));
                });
                bottomBarLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, stats, 92, SpringLayout.HORIZONTAL_CENTER, bottomBar);
                bottomBarLayout.putConstraint(SpringLayout.VERTICAL_CENTER, stats, 0, SpringLayout.VERTICAL_CENTER, bottomBar);

                bottomBar.add(stats);


                bottomBar.setLayout(bottomBarLayout);
                this.add(bottomBar);


                this.add(new WebbBackButton(contentPane, layout, (self, rightClicked) -> {
                        System.out.println("Back button pressed");
                        switchScreenTo(ScreenType.MAIN_MENU);
                }));

                //Calculate how many pages we need given the current size of the puzzle panel
                puzzlePanel.addComponentListener(new ComponentAdapter() {
                        @Override
                        public void componentResized(ComponentEvent e) {
                                super.componentResized(e);
                                int newWidth = puzzlePanel.getSize().width;
                                int newHeight = puzzlePanel.getSize().height;
                                System.out.println("Puzzle panel width: " + newWidth + ", height: " + newHeight);

                                final int horiz = (int) Math.floor(newWidth / 180);
                                final int vert = (int) Math.floor(newHeight / 180);

                                final int totalButtons = levels.length;
                                final int perPage = horiz * vert;
                                final int pages = (int) Math.ceil(totalButtons / (double) perPage);

                                System.out.println("Horiz: " + horiz + ", vert: " + vert + ", total buttons: " + totalButtons + ", per page: " + perPage + ", pages: " + pages);

                                levelPages.clear();
                                for(int page = 0; page < pages; page++) {
                                        List<PuzzleLevelDTO> tmpPage = new ArrayList<>();
                                        for(int i = 0; i < perPage; i++) {
                                                int index = page * perPage + i;
                                                if(index < totalButtons) {
                                                        tmpPage.add(levels[index]);
                                                }
                                        }
                                        levelPages.add(tmpPage);
                                }

                                currentPage = 0;
                                showPuzzlePanelPage(0);

                        }
                });

        }

        @Override
        public void onShow() {

                WebbWebUtilities.getRequestAsync(
                        "/leaderboards/users/",
                        LeaderboardDTO.class,
                        DEFAULT_LEADERBOARD_SCORE,
                        reply -> {leaderboardScores = reply;}
                );

                WebbWebUtilities.getRequestAsync(
                        "users/USERNAME", //TODO: Replace with actual username
                        UserDTO.class,
                        new UserDTO(null, DEFAULT_STATISTICS_DATA),
                        reply -> { statisticsData = reply.getStats();}
                );

                showPuzzlePanelPage(0);
        }

        public void setLevels(PuzzleLevelDTO[] levels) {
                System.out.println("Setting levels: " + levels.length);
                this.levels = levels;
        }

        public void setCompletedLevels(int[] completedLevels) {
                this.completedLevels = completedLevels;
        }

        private void showPuzzlePanelPage(int page) {
                if(page < 0 || page > levelPages.size() - 1) {
                        System.out.println("Invalid page number: " + page);
                        return;
                }

                this.pageNumberLabel.setText((page + 1) + "/" + levelPages.size());
               // System.out.println("Repopulating levels for page " + page);
               // System.out.println("total: " + levelPages.size());

                puzzlePanel.removeAll();
                List<PuzzleLevelDTO> levels = levelPages.get(page);

                for (PuzzleLevelDTO level : levels) {
                        PuzzleButton button = new PuzzleButton(level);
                        if(Arrays.stream(completedLevels).anyMatch(x -> x == level.getId())) {
                                button.setCompleted(true);
                        }
                        puzzlePanel.add(button);
                }
                puzzlePanel.revalidate();
                puzzlePanel.repaint();

                //disable the back button if we're on the first page
                if(page > 0) {
                        puzzleBack.setImage(WebbImages.PUZZLE_SELECTION_ARROW_BACK);
                } else {
                        puzzleBack.setImage(WebbImages.PUZZLE_SELECTION_ARROW_BACK_DISABLED);
                }

                //disable the forward button if we're on the last page
                if(page < levelPages.size() - 1) {
                        puzzleForward.setImage(WebbImages.PUZZLE_SELECTION_ARROW_FORWARD);
                } else {
                        puzzleForward.setImage(WebbImages.PUZZLE_SELECTION_ARROW_FORWARD_DISABLED);
                }

                puzzleBack.repaint();
                puzzleForward.repaint();
        }

        public boolean hasPopulatedLevelsYet() {
                return levels != DEFAULT_LEVELS;
        }
}
