package webb.client.ui.screens.options;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class GameOptions {

    private GameOptions() {}

    private static final File SETTINGS_FILE = new File("gamesettings.json");

    private boolean bgMusicEnabled = true;
    private boolean sfxEnabled = true;

    private boolean testButtonEnabled = false;

    public boolean isBgMusicEnabled() {return bgMusicEnabled;}
    public void setBgMusicEnabled(boolean bgMusicEnabled) {this.bgMusicEnabled = bgMusicEnabled;}

    public boolean isSfxEnabled() {return sfxEnabled;}
    public void setSfxEnabled(boolean sfxEnabled) {this.sfxEnabled = sfxEnabled;}

    public void setTestButtonEnabled(boolean testButtonEnabled) {this.testButtonEnabled = testButtonEnabled;}

    public boolean isTestButtonEnabled() {return testButtonEnabled;}

    public void save() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(SETTINGS_FILE, this);
    }

    public JsonNode toJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.valueToTree(this);
    }

    public static GameOptions load() throws IOException {

        if(!SETTINGS_FILE.exists()) {
            GameOptions options = new GameOptions();
            options.save();
            return options;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        GameOptions thing = objectMapper.readValue(SETTINGS_FILE, GameOptions.class);
        thing.save(); // causes the file to be updated with any new fields
        return thing;
    }

}
