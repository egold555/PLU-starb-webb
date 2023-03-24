package webb.server.security;

import webb.model.Player;
import webb.server.repository.MockPlayerDatabase;

public class PlayerManager {
    // this'll eventually disappear and be a dependency injection
    private static final MockPlayerDatabase users = new MockPlayerDatabase();

    private static Player currentPlayer;

    public static boolean authenticate(Player player)  {
        if(users.exists(player.username())) {
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