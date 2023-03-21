package webb.client.ui.components;

import java.awt.Component;
import javax.swing.SpringLayout;
import webb.client.ui.constants.WebbImages;

public class WebbBackButton extends WebbButton {

        public WebbBackButton(Component contentPane, SpringLayout layout, Runnable onClick) {
            super(WebbImages.ARROW_PAGE_BACK, 42, 42, onClick);
            layout.putConstraint(SpringLayout.NORTH, this, 10, SpringLayout.NORTH, contentPane);
            layout.putConstraint(SpringLayout.WEST, this, 10, SpringLayout.WEST, contentPane);
        }
}
