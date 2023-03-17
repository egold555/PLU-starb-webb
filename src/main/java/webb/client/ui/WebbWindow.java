package webb.client.ui;

import javax.swing.JFrame;;
import webb.client.ui.screens.Screen;
import webb.client.ui.screens.Screen.ScreenType;

public class WebbWindow extends JFrame {

    private static WebbWindow instance;

    private WebbWindow() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Webb Window");

        this.switchScreen(ScreenType.MAIN_MENU);

        this.setSize(600, 600);
    }

    public static WebbWindow getInstance() {
        if (instance == null) {
            instance = new WebbWindow();
        }
        return instance;
    }

    public void switchScreen(ScreenType screen) {
        switchScreen(screen.getScreen());
    }

    private void switchScreen(Screen screen) {
        this.getContentPane().removeAll();
        this.getContentPane().add(screen);
        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }
}
