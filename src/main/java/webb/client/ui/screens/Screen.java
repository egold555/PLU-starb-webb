package webb.client.ui.screens;

import java.awt.Container;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import webb.client.ui.WebbWindow;
import webb.client.ui.constants.WebbColors;
import webb.client.ui.screens.selectpuzzle.SelectPuzzleScreen;

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

    public void switchScreenTo(ScreenType screen) {
        WebbWindow.getInstance().switchScreen(screen);
    }

    public enum ScreenType {
        MAIN_MENU(new MainMenuScreen()),
        CREDITS(new CreditsScreen()),
        SELECT_PUZZLE(new SelectPuzzleScreen()),

        ;
        private final Screen screen;
        ScreenType(Screen screen) {
            this.screen = screen;
        }

        public Screen getScreen() {
            return screen;
        }
    }

}
