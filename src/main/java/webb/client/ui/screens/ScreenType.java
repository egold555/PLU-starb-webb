package webb.client.ui.screens;

import webb.client.ui.screens.creditsscreen.CreditsScreen;
import webb.client.ui.screens.loading.LoadingScreen;
import webb.client.ui.screens.mainmenu.MainMenuScreen;
import webb.client.ui.screens.puzzlescreen.PuzzleScreen;
import webb.client.ui.screens.selectpuzzle.SelectPuzzleScreen;
import webb.client.ui.screens.test.ScreenTestComponents;
import webb.client.ui.screens.test.ScreenTestConfetti;
import webb.client.ui.screens.test.ScreenTestFullScreenDrawing;

/**
 * This enum is used to store all the screens in the game.
 * It is used to get the screen instance from the enum.
 */
public enum ScreenType {

    MAIN_MENU(new MainMenuScreen()),
    CREDITS(new CreditsScreen()),
    SELECT_PUZZLE(new SelectPuzzleScreen()),
    PLAY_PUZZLE(new PuzzleScreen()),
    LOADING(new LoadingScreen()),

    //Testing
    TEST_COMPONENTS(new ScreenTestComponents()),
    TEST_DRAWING(new ScreenTestFullScreenDrawing()),
    TEST_CONFETTI(new ScreenTestConfetti())

    ;

    private final Screen screen;
    ScreenType(Screen screen) {
        this.screen = screen;
    }

    public Screen getScreenInstance() {
        return screen;
    }
}
