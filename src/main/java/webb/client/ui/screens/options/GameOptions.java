package webb.client.ui.screens.options;


import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class GameOptions {

    private GameOptions() {}

    private static final File SETTINGS_FILE = new File("gamesettings.json");

    private boolean bgMusicEnabled = true;
    private boolean sfxEnabled = true;

    public boolean isBgMusicEnabled() {return bgMusicEnabled;}
    public void setBgMusicEnabled(boolean bgMusicEnabled) {this.bgMusicEnabled = bgMusicEnabled;}

    public boolean isSfxEnabled() {return sfxEnabled;}
    public void setSfxEnabled(boolean sfxEnabled) {this.sfxEnabled = sfxEnabled;}

    public void save() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(SETTINGS_FILE, this);
    }

    public static GameOptions load() throws IOException {

        if(!SETTINGS_FILE.exists()) {
            GameOptions options = new GameOptions();
            options.save();
            return options;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(SETTINGS_FILE, GameOptions.class);
    }

}
