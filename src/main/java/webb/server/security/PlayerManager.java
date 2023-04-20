package webb.server.security;

import com.fasterxml.jackson.databind.node.ObjectNode;
import webb.client.model.Player;
import webb.client.ui.helpers.WebbWebUtilities;
import webb.server.repository.MockPlayerDatabase;

public class PlayerManager {

    private static Player currentPlayer;

    public static boolean authenticate(Player player)  {

        System.out.println("Authenticating...");

        // Check if the player exists. Right now the server just checks the same userdata file as we had before
        // Just returns "exists" as a boolean if the user was in that file or not
        ObjectNode node = WebbWebUtilities.getRequest("does-user-exist?username=" + player.username());

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

    public static void setCurrentPlayer(Player currentPlayer) {
        PlayerManager.currentPlayer = currentPlayer;
    }

    public static Player getCurrentPlayer() {
        return currentPlayer;
    }
}