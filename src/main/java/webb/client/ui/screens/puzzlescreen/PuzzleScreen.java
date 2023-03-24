package webb.client.ui.screens.puzzlescreen;

import java.awt.Color;
import java.awt.Container;
import java.awt.Point;
import java.util.Map.Entry;
import javax.swing.SpringLayout;
import webb.client.ui.screens.Screen;
import webb.client.ui.screens.puzzlescreen.Cell.CellType;
import webb.client.ui.testing.DummyData.DummyPlayPuzzleData;

public class PuzzleScreen extends Screen {

    private PuzzleComponent puzzleComponent;
    private PuzzleSideScreen sidePanel;

    public PuzzleScreen() {
        super();
        //setBackground(Color.GREEN);
    }

    @Override
    protected void populateComponents(Container contentPane, SpringLayout layout) {



        sidePanel = new PuzzleSideScreen();
        layout.putConstraint(SpringLayout.EAST, sidePanel, -10, SpringLayout.EAST, contentPane);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, sidePanel, 0, SpringLayout.VERTICAL_CENTER, contentPane);
        layout.putConstraint(SpringLayout.HEIGHT, sidePanel, -10, SpringLayout.HEIGHT, contentPane);
        //layout.putConstraint(SpringLayout.WIDTH, sidePanel, 100, "None", contentPane);
        this.add(sidePanel);


        puzzleComponent = new PuzzleComponent();
        layout.putConstraint(SpringLayout.NORTH, puzzleComponent, 10, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST, puzzleComponent, 10, SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, puzzleComponent, -10, SpringLayout.SOUTH, contentPane);
        layout.putConstraint(SpringLayout.EAST, puzzleComponent, -10, SpringLayout.WEST, sidePanel);
        this.add(puzzleComponent);

        populateWithDummyData();

    }

    private void populateWithDummyData() {
        sidePanel.setStarsRemaining(DummyPlayPuzzleData.SIDEBAR_STARTS_REMAINING, DummyPlayPuzzleData.SIDEBAR_STARTS_TOTAL);
        sidePanel.setPuzzleNumber(DummyPlayPuzzleData.SIDEBAR_PUZZLE_NUMBER, DummyPlayPuzzleData.SIDEBAR_PUZZLE_STAR);
        sidePanel.setTimeRemaining(DummyPlayPuzzleData.SIDEBAR_TIME_REMAINING);
        sidePanel.setPlayersCompleted(DummyPlayPuzzleData.SIDEBAR_PLAYERS_COMPLETED);

        puzzleComponent.setGridSize(DummyPlayPuzzleData.PUZZLE_GRID_SIZE);


        for(Entry<Point, CellType> entry : DummyPlayPuzzleData.PUZZLE_GRID_STARS.entrySet()) {
            puzzleComponent.getCell(entry.getKey().x, entry.getKey().y).setType(entry.getValue());
        }
    }

}
