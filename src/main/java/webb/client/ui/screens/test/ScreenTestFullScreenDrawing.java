package webb.client.ui.screens.test;

import java.awt.Container;
import javax.swing.SpringLayout;
import webb.client.ui.components.WebbButton;
import webb.client.ui.screens.Screen;
import webb.client.ui.screens.mainmenu.background.BackgroundSpacePanel;

public class ScreenTestFullScreenDrawing extends Screen {

    @Override
    protected void populateComponents(Container contentPane, SpringLayout layout) {

        BackgroundSpacePanel bg = new BackgroundSpacePanel();
        layout.putConstraint(SpringLayout.NORTH, bg, 0, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, bg, 0, SpringLayout.SOUTH, contentPane);
        layout.putConstraint(SpringLayout.EAST, bg, 0, SpringLayout.EAST, contentPane);
        layout.putConstraint(SpringLayout.WEST, bg, 0, SpringLayout.WEST, contentPane);




        WebbButton testButton = new WebbButton("Button", (self, rightClicked) -> {
            System.out.println("Button clicked");
        });

        layout.putConstraint(SpringLayout.NORTH, testButton, 10, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST, testButton, 10, SpringLayout.WEST, contentPane);
        this.add(testButton);
        this.add(bg);

    }

}
