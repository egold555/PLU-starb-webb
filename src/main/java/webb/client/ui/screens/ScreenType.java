package webb.client.ui.screens;

import webb.client.ui.screens.creditsscreen.CreditsScreen;
import webb.client.ui.screens.puzzlescreen.PuzzleScreen;
import webb.client.ui.screens.selectpuzzle.SelectPuzzleScreen;
import webb.client.ui.screens.test.ScreenComponetTesting;
import webb.client.ui.screens.test.ScreenPopupTest;

public class ScreenType {
    private ScreenType() {}

    public static final Screen MAIN_MENU = new MainMenuScreen();
    public static final Screen CREDITS = new CreditsScreen();
    public static final Screen SELECT_PUZZLE = new SelectPuzzleScreen();
    public static final Screen PLAY_PUZZLE = new PuzzleScreen();

    public static final Screen TEST_POPUP = new ScreenPopupTest();
    public static final Screen TEST_COMPONENTS = new ScreenComponetTesting();
}
