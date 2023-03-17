package webb.client.ui.screens;

import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import webb.client.ui.constants.WebbColors;

public abstract class Screen extends JPanel {

    public Screen() {
        Container contentPane = this;
        SpringLayout layout = new SpringLayout();

        populateComponents(this, layout);

        this.setBackground(WebbColors.D9);
        this.setLayout(layout);
    }

    // ContentPane is really 'this', but it makes it easier to understand the code
    protected abstract void populateComponents(Container contentPane, SpringLayout layout);

}
