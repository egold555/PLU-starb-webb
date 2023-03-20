package webb.client.ui.popup;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

/**
 * An example popup for a screen.
 * Just displays some Hello World text centered.
 */
public class ExampleWebbPopup extends WebbPopup {

    @Override
    protected void populateComponents(JPanel contentPane, SpringLayout layout) {
        JLabel label = new JLabel("Hello world!");
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, label, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, label, 0, SpringLayout.VERTICAL_CENTER, contentPane);
        contentPane.add(label);
    }
}
