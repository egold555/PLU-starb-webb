package webb.server.security;

import webb.shared.dtos.old.Player_OLD;
import webb.server.repository.MockPlayerDatabase;

public class PlayerManager {
    // this'll eventually disappear and be a dependency injection
    private static final MockPlayerDatabase users = new MockPlayerDatabase();

    private static Player_OLD currentPlayer;

    public static boolean authenticate(Player_OLD player)  {
        if(users.exists(player.username())) {
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