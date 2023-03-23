package starb.client;

import java.awt.*;
import webb.client.ui.WebbWindow;
import webb.model.User;
import webb.server.security.UserManager;

/**
 * Creates a single window as an example of a Java GUI with a component
 * for drawing.
 */
public class StarbClient {

    public static void main( String[] args ) throws Exception {

        String inputtedUserName = System.getProperty("username");

        if(inputtedUserName == null)
            throw new Exception("Please specify a username using VM options with keyword `username`");

        User user = new User(inputtedUserName);

        if(!UserManager.authenticate(user)) {
            throw new Exception("The username: '%s' already exists, please use another username.".formatted(user.getUsername()));
        }

        EventQueue.invokeLater( () -> WebbWindow.getInstance().setVisible(true));
    }
}
