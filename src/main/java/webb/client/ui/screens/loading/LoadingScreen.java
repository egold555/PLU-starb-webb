package webb.client.ui.screens.loading;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import webb.client.ui.components.WebbProgressBar;
import webb.client.ui.constants.WebbColors;
import webb.client.ui.constants.WebbFonts;
import webb.client.ui.helpers.http.HTTPRequestOptions;
import webb.client.ui.helpers.http.WebbWebUtilities;
import webb.client.ui.screens.Screen;
import webb.client.ui.screens.ScreenType;
import webb.client.ui.screens.selectpuzzle.SelectPuzzleScreen;
import webb.shared.dtos.puzzle.PuzzleLevelDTO;
import webb.shared.dtos.puzzle.user.UserPuzzleDTO;

public class LoadingScreen extends Screen {

    private WebbProgressBar progressBar;
    private JLabel loadingText;

    @Override
    protected void populateComponents(Container contentPane, SpringLayout layout) {

        this.setBackground(WebbColors.TEXT_COLOR_BLACK);

        loadingText = new JLabel("Loading...");
        loadingText.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_72);
        loadingText.setForeground(WebbColors.TEXT_COLOR_WHITE);
        layout.putConstraint(SpringLayout.NORTH, loadingText, 50, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, loadingText, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
        this.add(loadingText);

        progressBar = new WebbProgressBar(WebbColors.c90, WebbColors.D9);
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);
        progressBar.setValue(0);
        progressBar.setPreferredSize(new Dimension(400, 40));
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, progressBar, 0, SpringLayout.VERTICAL_CENTER, contentPane);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, progressBar, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
        contentPane.add(progressBar);

    }

    int progressPuzzleLevels = 0;
    int progressUserCompletion = 0;
    @Override
    public void onShow() {

        SelectPuzzleScreen selectPuzzleScreen = (SelectPuzzleScreen) ScreenType.SELECT_PUZZLE.getScreenInstance();

        if(!selectPuzzleScreen.hasPopulatedLevelsYet()) {

            HTTPRequestOptions<PuzzleLevelDTO[]> requestOptions_puzzlesLevels = new HTTPRequestOptions<>();
            requestOptions_puzzlesLevels.setDefaultValue(SelectPuzzleScreen.DEFAULT_LEVELS);

            requestOptions_puzzlesLevels.setProgressCallback((onProgress) -> {
                progressPuzzleLevels = (int) (onProgress * 100);
                System.out.println("[Progress Puzzle] Loading progress: " + progressPuzzleLevels + "%");
                updateProgressBar();
            });

            WebbWebUtilities.makeRequestAsync(
                    "/puzzles/levels",
                    PuzzleLevelDTO[].class,
                    requestOptions_puzzlesLevels,
                    selectPuzzleScreen::setLevels
            );

            HTTPRequestOptions<PuzzleLevelDTO[]> requestOptions_puzzleLevel = new HTTPRequestOptions<>();
            requestOptions_puzzleLevel.setDefaultValue(SelectPuzzleScreen.DEFAULT_LEVELS);
            requestOptions_puzzleLevel.setProgressCallback((onProgress) -> {
                progressPuzzleLevels = (int) (onProgress * 100);
                System.out.println("[Progress Puzzle] Loading progress: " + progressPuzzleLevels + "%");
                updateProgressBar();
            });

            WebbWebUtilities.makeRequestAsync(
                    "/puzzles/levels",
                    PuzzleLevelDTO[].class,
                    requestOptions_puzzleLevel,
                    selectPuzzleScreen::setLevels
            );

            HTTPRequestOptions<UserPuzzleDTO[]> requestOptions_userPuzzle = new HTTPRequestOptions<>();
            requestOptions_userPuzzle.setDefaultValue(SelectPuzzleScreen.DEFAULT_USER_LEVEL_PROGRESS);
            requestOptions_userPuzzle.setProgressCallback((onProgress) -> {
                progressUserCompletion = (int) (onProgress * 100);
                System.out.println("[User Puzzle] Loading progress: " + progressUserCompletion + "%");
                updateProgressBar();
            });

            WebbWebUtilities.makeRequestAsync(
                    "/puzzles/users/username", //TODO: SET USERNAME
                    UserPuzzleDTO[].class,
                    requestOptions_userPuzzle,
                    (puzzleLevels) -> {

                        Set<Integer> completedLevels = new HashSet<>();
                        for(UserPuzzleDTO puzzleLevel : puzzleLevels) {
                            if(puzzleLevel.isCompleted()) {
                                completedLevels.add(puzzleLevel.getLevelId());
                            }
                        }

                        selectPuzzleScreen.setCompletedLevels(completedLevels);

                    }
            );
        }

    }

    boolean startedTransition = false;
    void updateProgressBar() {

        int progressInt = (int) ((progressPuzzleLevels + progressUserCompletion) / 2.0);
        if(progressInt > 100) {
            progressInt = 100;
        }

        progressBar.setValue(progressInt);



        if(progressInt == 100 && !startedTransition) {
            startedTransition = true;

            final int timeUntilSwitch = 1000;
            new Timer().scheduleAtFixedRate(new TimerTask() {
                int currentProgress = 0;
                @Override
                public void run() {

                    currentProgress++;

                    double colorProgress = (double) currentProgress / timeUntilSwitch;
                    if(colorProgress > 1) {
                        colorProgress = 1;
                    }

                    Color newBackgroundColor = WebbColors.getColorBetween(WebbColors.D9, WebbColors.TEXT_COLOR_BLACK, colorProgress);
                    setBackground(newBackgroundColor);

                    if(currentProgress >= timeUntilSwitch){
                        this.cancel();
                        System.out.println("Loading complete!");
                        switchScreenTo(ScreenType.MAIN_MENU);
                    }


                }
            }, 0, 1);


        }

    }
}
