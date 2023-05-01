package webb.client.ui.screens.test;

import java.awt.Container;
import javax.swing.SpringLayout;
import webb.client.ui.components.WebbButton;
import webb.client.ui.screens.Screen;
import webb.client.ui.screens.test.confetti.BackgroundConfetti;

public class ScreenTestConfetti extends Screen {

    @Override
    protected void populateComponents(Container contentPane, SpringLayout layout) {

        BackgroundConfetti confettiBackground = new BackgroundConfetti();
        layout.putConstraint(SpringLayout.NORTH, confettiBackground, 0, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, confettiBackground, 0, SpringLayout.SOUTH, contentPane);
        layout.putConstraint(SpringLayout.EAST, confettiBackground, 0, SpringLayout.EAST, contentPane);
        layout.putConstraint(SpringLayout.WEST, confettiBackground, 0, SpringLayout.WEST, contentPane);




        WebbButton testButton = new WebbButton("Add Confetti", (self, rightClicked) -> {
            confettiBackground.addSomeConfetti(100);
        });

        layout.putConstraint(SpringLayout.NORTH, testButton, 10, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST, testButton, 10, SpringLayout.WEST, contentPane);

        // draw the confetti over the button
        this.add(confettiBackground);

        this.add(testButton);

        //add it in the background so it doesn't cover the button
        //this.add(confettiBackground);

    }

}
