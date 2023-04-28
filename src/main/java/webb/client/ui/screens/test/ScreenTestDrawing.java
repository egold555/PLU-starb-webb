package webb.client.ui.screens.test;

import java.awt.Container;
import javax.swing.SpringLayout;
import webb.client.ui.components.WebbButton;
import webb.client.ui.screens.Screen;
import webb.client.ui.screens.mainmenu.background.BackgroundSpacePanel;

public class ScreenTestDrawing extends Screen {

    @Override
    protected void populateComponents(Container contentPane, SpringLayout layout) {

        BackgroundSpacePanel ufo = new BackgroundSpacePanel();
        layout.putConstraint(SpringLayout.NORTH, ufo, 0, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, ufo, 0, SpringLayout.SOUTH, contentPane);
        layout.putConstraint(SpringLayout.EAST, ufo, 0, SpringLayout.EAST, contentPane);
        layout.putConstraint(SpringLayout.WEST, ufo, 0, SpringLayout.WEST, contentPane);




        WebbButton backButton = new WebbButton("Back", (self, rightClicked) -> {
            System.out.println("Back button pressed");
        });

        layout.putConstraint(SpringLayout.NORTH, backButton, 10, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST, backButton, 10, SpringLayout.WEST, contentPane);
        this.add(backButton);
        this.add(ufo);

    }

}
