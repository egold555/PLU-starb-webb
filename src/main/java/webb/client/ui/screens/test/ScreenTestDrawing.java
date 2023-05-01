package webb.client.ui.screens.test;

import java.awt.Container;
import javax.swing.SpringLayout;
import webb.client.ui.components.WebbButton;
import webb.client.ui.screens.Screen;
import webb.client.ui.screens.mainmenu.background.BackgroundSpacePanel;
import webb.client.ui.screens.test.confetti.BackgroundConfetti;

public class ScreenTestDrawing extends Screen {

    @Override
    protected void populateComponents(Container contentPane, SpringLayout layout) {

        BackgroundConfetti confettiBackground = new BackgroundConfetti();
        layout.putConstraint(SpringLayout.NORTH, confettiBackground, 0, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, confettiBackground, 0, SpringLayout.SOUTH, contentPane);
        layout.putConstraint(SpringLayout.EAST, confettiBackground, 0, SpringLayout.EAST, contentPane);
        layout.putConstraint(SpringLayout.WEST, confettiBackground, 0, SpringLayout.WEST, contentPane);




        WebbButton testButton = new WebbButton("Add Confetti", (self, rightClicked) -> {
            confettiBackground.test();
        });

        layout.putConstraint(SpringLayout.NORTH, testButton, 10, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST, testButton, 10, SpringLayout.WEST, contentPane);
        this.add(testButton);
        this.add(confettiBackground);

    }

}
