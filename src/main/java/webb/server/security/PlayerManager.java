package webb.server.security;

import com.fasterxml.jackson.databind.node.ObjectNode;
import webb.client.ui.helpers.http.HTTPRequestOptions;
import webb.client.ui.helpers.http.WebbWebUtilities;
import webb.shared.dtos.old.Player_OLD;

public class PlayerManager {

    private static Player_OLD currentPlayer;

    public static boolean authenticate(Player_OLD player)  {

        System.out.println("Authenticating...");

        // Right now there is no documented endpoint for logging in.
        //TODO: Make this endpoint
        if(true) {
            System.out.println("Force Authenticated!");
            return true;
        }

        // Check if the player exists. Right now the server just checks the same userdata file as we had before
        // Just returns "exists" as a boolean if the user was in that file or not

        ObjectNode node = WebbWebUtilities.makeRequest(
                "does-user-exist?username=" + player.username(),
                new HTTPRequestOptions<ObjectNode>()
        );

        //If we failed to connect to the server, we can't authenticate
        if(!node.get("success").asBoolean()) {
            System.out.println("Failed to authenticate!");
            System.out.println(node.get("error").asText());
            return false;
        }

        //If the player doesn't exist, we can't authenticate
        if(!node.get("data").get("exists").asBoolean()) {
            return false;
        }

        PlayerManager.setCurrentPlayer(player);
        System.out.println("Authenticated!");
        return true;
    }

    public static void setCurrentPlayer(Player_OLD currentPlayer) {
        PlayerManager.currentPlayer = currentPlayer;
    }

    public static Player_OLD getCurrentPlayer() {
        return currentPlayer;
    }
}