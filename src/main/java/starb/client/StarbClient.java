package starb.client;

import starb.client.ui.ExampleWindow;
import webb.client.uitest.EricsTestWindow;

import java.awt.*;

/**
 * Creates a single window as an example of a Java GUI with a component
 * for drawing.
 */
public class StarbClient {

    public static void main( String[] args ) {
        // Start the GUI
//        EventQueue.invokeLater( () -> new ExampleWindow().setVisible(true));
        EventQueue.invokeLater( () -> new EricsTestWindow().setVisible(true));
    }

}
