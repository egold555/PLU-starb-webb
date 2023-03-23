package webb.client.ui.screens.puzzlescreen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import javax.swing.SpringLayout;
import webb.client.ui.constants.WebbColors;
import webb.client.ui.screens.Screen;
import webb.client.ui.testing.DummyData.DummyPlayPuzzleData;

public class PuzzleScreen extends Screen {

    private PuzzleComponent puzzleComponent;
    private PuzzleSideScreen sidePanel;

    public PuzzleScreen() {
        super();
        setBackground(Color.RED
        );
    }

    @Override
    protected void populateComponents(Container contentPane, SpringLayout layout) {

//        puzzleComponent = new PuzzleComponent();
//        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, puzzleComponent, 0, SpringLayout.HORIZONTAL_CENTER, this);
//        layout.putConstraint(SpringLayout.VERTICAL_CENTER, puzzleComponent, 0, SpringLayout.VERTICAL_CENTER, this);
//        this.add(puzzleComponent);

        sidePanel = new PuzzleSideScreen();
        layout.putConstraint(SpringLayout.EAST, sidePanel, -10, SpringLayout.EAST, contentPane);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, sidePanel, 0, SpringLayout.VERTICAL_CENTER, contentPane);
        layout.putConstraint(SpringLayout.HEIGHT, sidePanel, -10, SpringLayout.HEIGHT, contentPane);
        //layout.putConstraint(SpringLayout.WIDTH, sidePanel, 100, "None", contentPane);
        this.add(sidePanel);

        populateWithDummyData();

    }

    private void populateWithDummyData() {
        sidePanel.setStarsRemaining(DummyPlayPuzzleData.SIDEBAR_STARTS_REMAINING, DummyPlayPuzzleData.SIDEBAR_STARTS_TOTAL);
        sidePanel.setPuzzleNumber(DummyPlayPuzzleData.SIDEBAR_PUZZLE_NUMBER, DummyPlayPuzzleData.SIDEBAR_PUZZLE_STAR);
        sidePanel.setTimeRemaining(DummyPlayPuzzleData.SIDEBAR_TIME_REMAINING);

    }

}
