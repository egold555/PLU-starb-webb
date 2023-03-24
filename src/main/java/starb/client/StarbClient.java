package starb.client;

import java.awt.*;
import webb.client.ui.WebbWindow;
import webb.model.Player;
import webb.server.security.PlayerManager;

/**
 * Creates a single window as an example of a Java GUI with a component
 * for drawing.
 */
public class StarbClient {

    public static void main( String[] args ) throws Exception {

        String inputtedUserName = System.getProperty("username");

        if(inputtedUserName == null)
            throw new Exception("Please specify a username using VM options with keyword `username`");

        Player player = new Player(inputtedUserName);

        if(!PlayerManager.authenticate(player)) {
            throw new Exception("The username: '%s' already exists, please use another username.".formatted(player.username()));
        }

        EventQueue.invokeLater( () -> WebbWindow.getInstance().setVisible(true));
    }
}
