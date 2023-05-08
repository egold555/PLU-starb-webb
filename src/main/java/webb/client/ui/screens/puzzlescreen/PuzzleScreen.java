package webb.client.ui.screens.puzzlescreen;

import java.awt.Container;
import javax.swing.SpringLayout;
import webb.client.ui.WebbWindow;
import webb.client.ui.constants.WebbAudio;
import webb.client.ui.popup.congratulations.PopupCongratulations;
import webb.client.ui.screens.Screen;
import webb.client.ui.screens.ScreenType;
import webb.client.ui.screens.puzzlescreen.StopWatch.StopWatchCallback;
import webb.client.ui.screens.puzzlescreen.confetti.BackgroundConfetti;
import webb.client.ui.testing.DummyData.DummyPlayPuzzleData;
import webb.shared.dtos.puzzle.PuzzleLevelDTO;

/**
 * The screen that displays the puzzle, that the user interacts with.
 * This screen also has the sidebar, which displays the puzzle number, stars remaining, etc.
 */
public class PuzzleScreen extends Screen {

    private PuzzleComponent puzzleComponent;
    private PuzzleSideScreen sidePanel;
    private StopWatch stopWatch;

    private PuzzleLevelDTO puzzleToResetTo;

    //private BackgroundConfetti confettiMachine;

    @Override
    protected void populateComponents(Container contentPane, SpringLayout layout) {

//        confettiMachine = new BackgroundConfetti();
//        layout.putConstraint(SpringLayout.NORTH, confettiMachine, 0, SpringLayout.NORTH, contentPane);
//        layout.putConstraint(SpringLayout.SOUTH, confettiMachine, 0, SpringLayout.SOUTH, contentPane);
//        layout.putConstraint(SpringLayout.EAST, confettiMachine, 0, SpringLayout.EAST, contentPane);
//        layout.putConstraint(SpringLayout.WEST, confettiMachine, 0, SpringLayout.WEST, contentPane);
//        this.add(confettiMachine);

        //------------------ SIDEBAR ------------------
        sidePanel = new PuzzleSideScreen(this);
        layout.putConstraint(SpringLayout.EAST, sidePanel, -10, SpringLayout.EAST, contentPane);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, sidePanel, 0, SpringLayout.VERTICAL_CENTER, contentPane);
        layout.putConstraint(SpringLayout.HEIGHT, sidePanel, -10, SpringLayout.HEIGHT, contentPane);
        this.add(sidePanel);

        //------------------ PUZZLE COMPONENT ------------------
        puzzleComponent = new PuzzleComponent(this);
        layout.putConstraint(SpringLayout.NORTH, puzzleComponent, 10, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST, puzzleComponent, 10, SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, puzzleComponent, -10, SpringLayout.SOUTH, contentPane);
        layout.putConstraint(SpringLayout.EAST, puzzleComponent, -10, SpringLayout.WEST, sidePanel);
        this.add(puzzleComponent);

        // Populate with dummy data for testing
        //populateWithDummyData();

    }

    /**
     * Populates the puzzle screen with dummy data for testing.
     * TODO: Remove this method once we have real data!
     */
    private void populateWithDummyData() {
        sidePanel.setStarsRemaining(DummyPlayPuzzleData.SIDEBAR_STARTS_REMAINING, DummyPlayPuzzleData.SIDEBAR_STARTS_TOTAL);
        sidePanel.setPuzzleNumber(DummyPlayPuzzleData.SIDEBAR_PUZZLE_NUMBER, DummyPlayPuzzleData.SIDEBAR_PUZZLE_STAR);
        sidePanel.setTimeRemaining(DummyPlayPuzzleData.SIDEBAR_TIME_REMAINING);
        sidePanel.setPlayersCompleted(DummyPlayPuzzleData.SIDEBAR_PLAYERS_COMPLETED);

//        puzzleComponent.setGridSize(DummyPlayPuzzleData.PUZZLE_GRID_SIZE);
//
//
//        for(Entry<Point, CellType> entry : DummyPlayPuzzleData.PUZZLE_GRID_STARS.entrySet()) {
//            puzzleComponent.getCell(entry.getKey().x, entry.getKey().y).setType(entry.getValue());
//        }
    }

    /**
     * Sets the puzzle to display on the screen.
     * @param puzzle The puzzle to display.
     * TODO: Finish this method once we have real data!
     */
    public void setPuzzle(PuzzleLevelDTO puzzle) {
        this.puzzleToResetTo = puzzle;
        sidePanel.setStarsRemaining(puzzle.getTotalStars(), puzzle.getTotalStars());
        sidePanel.setPuzzleNumber(puzzle.getId() + 1, puzzle.getNumStars());
        puzzleComponent.setPuzzle(puzzle);

        if(stopWatch != null) {
            stopWatch.stop();
        }

        //System.out.println("Start timer!");
        stopWatch = new StopWatch(new StopWatchCallback() {
            @Override
            public void updateLabels(long currentTime) {
                sidePanel.setTimeRemaining(currentTime);
                // System.out.println("Time remaining: " + currentTime);
            }
        });

        sidePanel.setPlayersCompleted(puzzle.getSolvedByNumPlayers());

        stopWatch.start();
    }

    /**
     * Resets the puzzle board by setting it to the puzzle that was last set.
     */
    public void reset() {
        if(this.puzzleToResetTo == null) {
            System.err.println("Puzzle to reset to is null! Returning...");
            return;
        }
        setPuzzle(puzzleToResetTo);
        WebbWindow.getInstance().getBGMusicPlayer().playBG(WebbAudio.BG_IN_GAME);
    }

    protected void onPuzzleComplete() {
        System.out.println("Puzzle complete!");
        stopWatch.stop();
        //confettiMachine.enableConstantConfetti(true);
        WebbWindow.getInstance().getBGMusicPlayer().playBG(WebbAudio.BG_PUZZLE_COMPLETE);

        final long time = stopWatch.getTime();

        showPopup(new PopupCongratulations(
                this,
                time,
                1,
                3,
                2,
                "current",
                "next"
        ));
    }

    protected PuzzleComponent getPuzzleComponent() {
        return puzzleComponent;
    }

    protected void exitPuzzle() {
        stopWatch.stop();
        this.switchScreenTo(ScreenType.SELECT_PUZZLE);
    }

    @Override
    public void onShow() {
        WebbWindow.getInstance().getBGMusicPlayer().playBG(WebbAudio.BG_IN_GAME);
    }
}
