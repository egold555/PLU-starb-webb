package webb.client.ui.popup;

import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import webb.client.ui.components.RoundedJPanel;
import webb.client.ui.constants.WebbColors;

public class PopupStatistics extends WebbPopup {

    public PopupStatistics() {
        super("My Statistics");
    }

    @Override
    protected void populateComponents(JPanel contentPane, SpringLayout layout) {

        RoundedJPanel panel = new RoundedJPanel();
        panel.setBackground(WebbColors.B7);

        // Set the size of the panel to be 100px smaller than the popup
        int width = this.getWidth() - 100;
        int height = this.getHeight() - 100;
        panel.setPreferredSize(new Dimension(width, height));


        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, panel, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, panel, 0, SpringLayout.VERTICAL_CENTER, contentPane);

        add(panel);

    }

}
