package webb.client.ui.constants;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class WebbAudio {

    public static final String BG_MAIN_MENU = "main_menu";
    public static final String BG_IN_GAME = "in_game";
    public static final String SFX_CLICK = "ui_button2";

    static {
        try {
            preloadAudio();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void preloadAudio() throws IOException {

        for(String name : new String[] {BG_MAIN_MENU, BG_IN_GAME, SFX_CLICK}) {
            String path = "/webb/audio/" + name + ".wav";
            InputStream is = WebbAudio.class.getResourceAsStream(path);
            is.close();
        }
    }

//    public static final InputStream BG_MAIN_MENU = getAudioFileWithName("main_menu");
//    public static final InputStream BG_IN_GAME = getAudioFileWithName("in_game");
//    public static final InputStream SFX_CLICK = getAudioFileWithName("ui_button");
//
//    private static InputStream getAudioFileWithName(String name) {
//        String path = "/webb/audio/" + name + ".wav";
//        InputStream is = WebbAudio.class.getResourceAsStream(path);
//        if(is == null) {
//            System.err.println("Could not find audio file: " + path);
//            return null;
//        }
//        return is;
//    }

}
