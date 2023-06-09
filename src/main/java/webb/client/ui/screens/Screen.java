package webb.client.ui.screens;

import java.awt.Container;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import webb.client.ui.WebbWindow;
import webb.client.ui.constants.WebbColors;
import webb.client.ui.popup.WebbPopup;
import webb.client.ui.popup.errorhandler.PopupErrorHandler;

/**
 * A JPanel wrapper with some extra functionality.
 * This is the base class for all screens.
 */
public abstract class Screen extends JPanel {

    /**
     * Creates a new screen.
     * This is an abstract class, so it cannot be instantiated directly.
     */
    public Screen() {
        Container contentPane = this;
        SpringLayout layout = new SpringLayout();
        this.setBackground(WebbColors.D9);
        populateComponents(this, layout);


        this.setLayout(layout);

    }

    /**
     * Populates the components of the screen.
     * @param contentPane The content pane of the screen (this)
     * @param layout The layout of the screen.
     */
    protected abstract void populateComponents(Container contentPane, SpringLayout layout);

    /**
     * Switches the display screen to the given screen.
     * @param screen The screen to switch to.
     */
    public void switchScreenTo(ScreenType screen) {
        WebbWindow.getInstance().switchScreen(screen);
    }

    /**
     * Shows a popup on the screen.
     * @param popup The popup to show.
     */
    public void showPopup(WebbPopup popup) {
        popup.showPopup();
    }

    /**
     * Called when the screen is made visible
     */
    public void onShow() {}

}
