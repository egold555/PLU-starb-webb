package starb.client;

import java.awt.*;
import webb.client.ui.WebbWindow;

/**
 * Creates a single window as an example of a Java GUI with a component
 * for drawing.
 */
public class StarbClient {

    public static void main( String[] args ) {
        // Start the GUI
//        EventQueue.invokeLater( () -> new ExampleWindow().setVisible(true));
        EventQueue.invokeLater( () -> WebbWindow.getInstance().setVisible(true));
    }

}
