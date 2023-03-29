package webb.client.ui.screens.puzzlescreen;

import java.awt.Container;
import javax.swing.SpringLayout;
import webb.client.ui.screens.Screen;
import webb.client.ui.testing.DummyData.DummyPlayPuzzleData;
import webb.client.model.puzzle.PuzzleDTO;

/**
 * The screen that displays the puzzle, that the user interacts with.
 * This screen also has the sidebar, which displays the puzzle number, stars remaining, etc.
 */
public class PuzzleScreen extends Screen {

    private PuzzleComponent puzzleComponent;
    private PuzzleSideScreen sidePanel;

    @Override
    protected void populateComponents(Container contentPane, SpringLayout layout) {

        //------------------ SIDEBAR ------------------
        sidePanel = new PuzzleSideScreen();
        layout.putConstraint(SpringLayout.EAST, sidePanel, -10, SpringLayout.EAST, contentPane);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, sidePanel, 0, SpringLayout.VERTICAL_CENTER, contentPane);
        layout.putConstraint(SpringLayout.HEIGHT, sidePanel, -10, SpringLayout.HEIGHT, contentPane);
        this.add(sidePanel);

        //------------------ PUZZLE COMPONENT ------------------
        puzzleComponent = new PuzzleComponent();
        layout.putConstraint(SpringLayout.NORTH, puzzleComponent, 10, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST, puzzleComponent, 10, SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, puzzleComponent, -10, SpringLayout.SOUTH, contentPane);
        layout.putConstraint(SpringLayout.EAST, puzzleComponent, -10, SpringLayout.WEST, sidePanel);
        this.add(puzzleComponent);

        // Populate with dummy data for testing
        populateWithDummyData();

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

        setPuzzle(DummyPlayPuzzleData.PUZZLE_1_1_1);
    }

    /**
     * Sets the puzzle to display on the screen.
     * @param puzzle The puzzle to display.
     * TODO: Finish this method once we have real data!
     */
    public void setPuzzle(PuzzleDTO puzzle) {
        sidePanel.setStarsRemaining(puzzle.getTotalStars(), puzzle.getTotalStars());
        sidePanel.setPuzzleNumber(0, puzzle.getNumStars());
        puzzleComponent.setPuzzle(puzzle);
    }

}
