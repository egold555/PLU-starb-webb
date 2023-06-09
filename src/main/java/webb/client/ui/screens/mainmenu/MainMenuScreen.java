package webb.client.ui.screens.mainmenu;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import webb.client.ui.WebbWindow;
import webb.client.ui.components.WebbButton;
import webb.client.ui.constants.WebbAudio;
import webb.client.ui.constants.WebbColors;
import webb.client.ui.constants.WebbFonts;
import webb.client.ui.screens.Screen;
import webb.client.ui.screens.ScreenType;
import webb.client.ui.screens.mainmenu.background.BackgroundSpacePanel;

/**
 * The main menu screen.
 */
public class MainMenuScreen extends Screen {

    private WebbButton testButton;

    @Override
    protected void populateComponents(Container contentPane, SpringLayout layout) {

        //------ Title text ------
        JLabel mainMenuText1 = new JLabel("Star Battle");
        mainMenuText1.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_72);
        mainMenuText1.setForeground(WebbColors.TEXT_COLOR_BLACK);
        layout.putConstraint(SpringLayout.NORTH, mainMenuText1, 50, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, mainMenuText1, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
        this.add(mainMenuText1);

        JLabel mainMenuText2 = new JLabel("Odyssey");
        mainMenuText2.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_72);
        mainMenuText2.setForeground(WebbColors.TEXT_COLOR_BLACK);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, mainMenuText2, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
        layout.putConstraint(SpringLayout.NORTH, mainMenuText2, 0, SpringLayout.SOUTH, mainMenuText1);
        this.add(mainMenuText2);

        //------ Play button ------

        WebbButton playButton = new WebbButton("Play", (self, rightClicked) -> {
            System.out.println("Play button pressed");
            this.switchScreenTo(ScreenType.SELECT_PUZZLE);
        });
        playButton.setPreferredSize(new Dimension(142, 43));
        playButton.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_32);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, playButton, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
        layout.putConstraint(SpringLayout.NORTH, playButton, 50, SpringLayout.SOUTH, mainMenuText2);

        this.add(playButton);

        //------ Options button ------
        WebbButton optionsButton = new WebbButton("Options", (self, rightClicked) -> {
            System.out.println("Options button pressed");
            this.switchScreenTo(ScreenType.OPTIONS);
        });
        optionsButton.setPreferredSize(new Dimension(142, 43));
        optionsButton.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_32);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, optionsButton, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
        layout.putConstraint(SpringLayout.NORTH, optionsButton, 10, SpringLayout.SOUTH, playButton);

        this.add(optionsButton);

        //---------- Credits button ----------
        WebbButton creditsButton = new WebbButton("Credits", (self, rightClicked) -> {
            System.out.println("Credits button pressed");
            this.switchScreenTo(ScreenType.CREDITS);
        });
        creditsButton.setPreferredSize(new Dimension(142, 43));
        creditsButton.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_32);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, creditsButton, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
        layout.putConstraint(SpringLayout.NORTH, creditsButton, 10, SpringLayout.SOUTH, optionsButton);

        this.add(creditsButton);

        //----------- Quit button ----------------
        WebbButton quitButton = new WebbButton("Quit", (self, rightClicked) -> {
            System.out.println("Quit button pressed");
            System.exit(0);
        });
        quitButton.setPreferredSize(new Dimension(142, 43));
        quitButton.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_32);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, quitButton, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
        layout.putConstraint(SpringLayout.NORTH, quitButton, 10, SpringLayout.SOUTH, creditsButton);

        this.add(quitButton);

        //----------- Test button ----------------

        testButton = new WebbButton("Test", (self, rightClicked) -> {
            System.out.println("Test button pressed");
            this.switchScreenTo(ScreenType.TEST_COMPONENTS);
        });
        testButton.setPreferredSize(new Dimension(142, 43));
        testButton.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_32);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, testButton, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
        layout.putConstraint(SpringLayout.NORTH, testButton, 10, SpringLayout.SOUTH, quitButton);

        testButton.setVisible(false);
        this.add(testButton);



        //----------- Background space panel ----------------
        BackgroundSpacePanel backgroundSpacePanel = new BackgroundSpacePanel();
        layout.putConstraint(SpringLayout.NORTH, backgroundSpacePanel, 0, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, backgroundSpacePanel, 0, SpringLayout.SOUTH, contentPane);
        layout.putConstraint(SpringLayout.EAST, backgroundSpacePanel, 0, SpringLayout.EAST, contentPane);
        layout.putConstraint(SpringLayout.WEST, backgroundSpacePanel, 0, SpringLayout.WEST, contentPane);
        this.add(backgroundSpacePanel);
    }

    @Override
    public void onShow() {
        WebbWindow.getInstance().getBGMusicPlayer().playBG(WebbAudio.BG_MAIN_MENU);

        if(WebbWindow.getInstance().getGameOptions().isTestButtonEnabled()) {
            testButton.setVisible(true);
        }
    }
}
