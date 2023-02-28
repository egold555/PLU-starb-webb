package starb.client.ui;

import javax.swing.*;
import java.awt.*;

/**
 * An example GUI containing a panel (JComponent) that can be used for drawing
 * with Java 2D graphics.  For more information on GUIs in Java see:
 *
 * https://docs.oracle.com/javase/tutorial/uiswing/
 */
public class ExampleWindow extends JFrame {

    private ExampleDrawingPanel graphicsPanel;

    private ExampleSidePanel sidePanel;

    public ExampleWindow() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Example Window");

        JPanel titlePanel = new JPanel();
        titlePanel.add( new JLabel("Example Drawing") );

        graphicsPanel = new ExampleDrawingPanel();
        sidePanel = new ExampleSidePanel();

        this.add(graphicsPanel, BorderLayout.CENTER);
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(sidePanel, BorderLayout.EAST);

        this.pack();
    }

}
