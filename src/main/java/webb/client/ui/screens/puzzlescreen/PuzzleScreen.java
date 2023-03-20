package webb.client.ui.screens.puzzlescreen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import javax.swing.SpringLayout;
import webb.client.ui.screens.Screen;

public class PuzzleScreen extends Screen {

    private PuzzleComponent puzzleComponent;
    private PuzzleSideScreen sidePanel;

    public PuzzleScreen() {
        super();
        this.setBackground(Color.red);
    }

    @Override
    protected void populateComponents(Container contentPane, SpringLayout layout) {

//        puzzleComponent = new PuzzleComponent();
//        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, puzzleComponent, 0, SpringLayout.HORIZONTAL_CENTER, this);
//        layout.putConstraint(SpringLayout.VERTICAL_CENTER, puzzleComponent, 0, SpringLayout.VERTICAL_CENTER, this);
//        this.add(puzzleComponent);

        sidePanel = new PuzzleSideScreen();
        layout.putConstraint(SpringLayout.EAST, sidePanel, 0, SpringLayout.EAST, contentPane);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, sidePanel, 0, SpringLayout.VERTICAL_CENTER, contentPane);
        layout.putConstraint(SpringLayout.HEIGHT, sidePanel, 0, SpringLayout.HEIGHT, contentPane);
        layout.putConstraint(SpringLayout.WIDTH, sidePanel, 100, "None", contentPane);
        this.add(sidePanel);

    }

}
