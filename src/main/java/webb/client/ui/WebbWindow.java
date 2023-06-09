package webb.client.ui;

import java.awt.Toolkit;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import webb.client.ui.audio.BGMusicPlayer;
import webb.client.ui.audio.SFXPlayer;
import webb.client.ui.popup.errorhandler.PopupErrorHandler;
import webb.client.ui.screens.Screen;
import webb.client.ui.screens.ScreenType;
import webb.client.ui.screens.options.GameOptions;

;

/**
 * The main window of the Webb client.
 */
public class WebbWindow extends JFrame {

    private static WebbWindow instance;
    /*
    Constructor is private because this is a singleton class.
     */

    private SFXPlayer sfxPlayer = new SFXPlayer();
    private BGMusicPlayer bgMusicPlayer = new BGMusicPlayer();

    private GameOptions gameOptions;

    private WebbWindow() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Star Battle Odyssey");

        try {
            this.gameOptions = GameOptions.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.switchScreen(ScreenType.LOADING);
        //this.switchScreen(ScreenType.OPTIONS);

        //maximizes the window for testing
        this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        this.setSize(600, 600);

        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/webb/images/icon/icon5.png")));

        // Set the default uncaught exception handler to show a popup with the error.
        // We also print the error to the console.
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            handleError(e);
        });

        sfxPlayer.start();
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

    /**
     * Returns the SFXPlayer instance.
     * @return The SFXPlayer instance.
     */
    public SFXPlayer getSFXPlayer() {
        return sfxPlayer;
    }

    /**
     * Returns the BGMusicPlayer instance.
     * @return The BGMusicPlayer instance.
     */
    public BGMusicPlayer getBGMusicPlayer() {
        return bgMusicPlayer;
    }

    /**
     * Returns the GameOptions instance.
     * @return The GameOptions instance.
     */
    public GameOptions getGameOptions() {return gameOptions;}

    /**
     * Handles an error by showing a popup with the error and printing the error to the console.
     * @param e The error to handle.
     */
    public void handleError(Throwable e) {
        e.printStackTrace();

        // We don't want to show an error popup if the error popup is causing the error
        try {
            new PopupErrorHandler(e).showPopup();
        }
        catch (Throwable e1) {
            e1.printStackTrace();
        }
    }
}
