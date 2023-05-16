package webb.client;

import java.awt.EventQueue;
import java.util.HashMap;
import java.util.Map;
import webb.client.authentication.AuthenticationManager;
import webb.client.ui.WebbWindow;

/**
 * Creates a single window as an example of a Java GUI with a component
 * for drawing.
 *
 * Program arguments: -username=yourusername
 * Gradle run: --args="-username=yourusername"
 */
public class Main {

    public static void main( String[] args ) throws Exception {

        final Map<String, String> programArguments = parseProgramArguments(args);

        String inputtedUserName = programArguments.getOrDefault("username", null);

        if(inputtedUserName == null) {
            throw new Exception("Please specify a username using VM options with keyword `username`");
        }

        if(!AuthenticationManager.getInstance().authenticate(inputtedUserName)) {
            //throw new Exception("Failed to authenticate: '%s'. ".formatted(inputtedUserName));
            System.err.println("Failed to authenticate: '%s' Attempting to create a new user for '%s'".formatted(inputtedUserName, inputtedUserName));

            if(!AuthenticationManager.getInstance().createUser(inputtedUserName)) {
                throw new Exception("Failed to create user: '%s'. ".formatted(inputtedUserName));
            }

        }

        System.out.println("Successfully authenticated: '%s'".formatted(AuthenticationManager.getInstance().getCurrentUser().getUsername()));

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
