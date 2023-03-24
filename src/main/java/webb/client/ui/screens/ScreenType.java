package webb.client.ui.screens;

import webb.client.ui.screens.creditsscreen.CreditsScreen;
import webb.client.ui.screens.puzzlescreen.PuzzleScreen;
import webb.client.ui.screens.selectpuzzle.SelectPuzzleScreen;
import webb.client.ui.screens.test.ScreenComponetTesting;
import webb.client.ui.screens.test.ScreenPopupTest;

public enum ScreenType {

    MAIN_MENU(new MainMenuScreen()),
    CREDITS(new CreditsScreen()),
    SELECT_PUZZLE(new SelectPuzzleScreen()),
    PLAY_PUZZLE(new PuzzleScreen()),
    TEST_POPUP(new ScreenPopupTest()),
    TEST_COMPONENTS(new ScreenComponetTesting())

    ;

    private final Screen screen;
    ScreenType(Screen screen) {
        this.screen = screen;
    }

    public Screen getScreenInstance() {
        return screen;
    }
}
