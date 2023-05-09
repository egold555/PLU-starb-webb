package webb.client.ui.constants;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class WebbAudio {

    public static final String BG_MAIN_MENU = "main_menu";
    public static final String BG_IN_GAME = "in_game";
    public static final String SFX_CLICK = "ui_button_click";
    public static final String SFX_BTN_HOVER = "ui_button_hover";
    public static final String BG_PUZZLE_COMPLETE = "game_win_bg";

    static {
        try {
            preloadAudio();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void preloadAudio() throws IOException {

        for(String name : new String[] {BG_MAIN_MENU, BG_IN_GAME, SFX_CLICK, SFX_BTN_HOVER}) {
            String path = "/webb/audio/" + name + ".wav";
            InputStream is = WebbAudio.class.getResourceAsStream(path);
            is.close();
        }
    }

}
