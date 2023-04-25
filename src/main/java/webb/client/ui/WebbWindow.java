package webb.client.ui;

import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import webb.client.ui.screens.Screen;
import webb.client.ui.screens.ScreenType;

;

/**
 * The main window of the Webb client.
 */
public class WebbWindow extends JFrame {

    private static WebbWindow instance;
    /*
    Constructor is private because this is a singleton class.
     */
    private WebbWindow() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Star Battle Odyssey");

        this.switchScreen(ScreenType.LOADING);

        //maximizes the window for testing
        this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        this.setSize(600, 600);
    }

    /**
     Returns the instance of the WebbWindow.
     If the instance is null, it creates a new instance and returns it.
     */
    public static WebbWindow getInstance() {
        if (instance == null) {
            instance = new WebbWindow();
        }
        return instance;
    }

    /**
     * Switches the display screen to the given screen.
     * @param screen The screen instance to switch to.
     */
    public void switchScreen(ScreenType screen) {
        this.switchScreen(screen.getScreenInstance());
    }

    /**
     * Switches the display screen to the given screen.
     * @param screen The screen instance to switch to.
     * @see webb.client.ui.WebbWindow#switchScreen(webb.client.ui.screens.ScreenType)
     */
    private void switchScreen(Screen screen) {
        this.getContentPane().removeAll();
        this.getContentPane().add(screen);
        this.getContentPane().revalidate();
        this.getContentPane().repaint();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                screen.onShow();
            }
        }, 1);
    }
}
