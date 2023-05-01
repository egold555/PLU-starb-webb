package webb.client.ui.screens.options;


import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import lombok.Getter;
import lombok.Setter;

public class GameOptions {

    private GameOptions() {}

    private static final File SETTINGS_FILE = new File("gamesettings.json");

    @Getter
    @Setter
    private boolean bgMusicEnabled = true;

    @Getter
    @Setter
    private boolean sfxEnabled = true;

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
