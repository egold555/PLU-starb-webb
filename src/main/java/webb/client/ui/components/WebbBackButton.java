package webb.client.ui.components;

import java.awt.Component;
import javax.swing.SpringLayout;
import webb.client.ui.constants.WebbImages;

/**
 * A WebbButton that is used to navigate back to the previous page.
 */
public class WebbBackButton extends WebbButton {

    /**
     * Creates a new WebbBackButton.
     * @param contentPane The content pane of the frame.
     * @param layout The layout of the content pane.
     * @param clickListener The action to perform when the button is clicked.
     */
    public WebbBackButton(Component contentPane, SpringLayout layout, ClickListener clickListener) {
        super(WebbImages.PUZZLE_SELECTION_ARROW_PAGE_BACK, 42, 42, clickListener);
        layout.putConstraint(SpringLayout.NORTH, this, 10, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST, this, 10, SpringLayout.WEST, contentPane);
    }
}
