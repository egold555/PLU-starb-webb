package starb.client;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import webb.client.ui.WebbWindow;
import webb.shared.dtos.old.Player_OLD;
import webb.server.security.PlayerManager;

/**
 * Creates a single window as an example of a Java GUI with a component
 * for drawing.
 *
 * Program arguments: -username=yourusername
 * Gradle run: --args="-username=yourusername"
 */
public class StarbClient {

    public static void main( String[] args ) throws Exception {

        final Map<String, String> programArguments = parseProgramArguments(args);

        String inputtedUserName = programArguments.getOrDefault("username", null);

        if(inputtedUserName == null)
            throw new Exception("Please specify a username using VM options with keyword `username`");

        Player_OLD player = new Player_OLD(inputtedUserName);

        if(!PlayerManager.authenticate(player)) {
            throw new Exception("The username: '%s' already exists, please use another username.".formatted(player.username()));
        }

        EventQueue.invokeLater( () -> WebbWindow.getInstance().setVisible(true));
    }

    static Map<String, String> parseProgramArguments(String[] args) throws Exception {
        Map<String, String> argsMap = new HashMap<>();
        for (String arg: args) {
            String[] parts = arg.split("=");

            if(parts.length != 2) {
                throw new Exception("Invalid Program option: '%s'".formatted(arg));
            }

            if(!parts[0].startsWith("-")) {
                throw new Exception("Invalid Program option: '%s'".formatted(arg));
            }

            final String key = parts[0].substring(1);
            final String value = parts[1];

            argsMap.put(key, value);
        }

        return argsMap;
    }
}
