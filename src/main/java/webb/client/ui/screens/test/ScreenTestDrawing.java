package webb.client.ui.screens.test;

import java.awt.Container;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import webb.client.ui.components.WebbButton;
import webb.client.ui.screens.Screen;

public class ScreenTestDrawing extends Screen {

    @Override
    protected void populateComponents(Container contentPane, SpringLayout layout) {

        TestUFO ufo = new TestUFO();
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


    class TestUFO extends JComponent {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.drawLine(0, 0, this.getWidth(), this.getHeight());

        }
    }
}
