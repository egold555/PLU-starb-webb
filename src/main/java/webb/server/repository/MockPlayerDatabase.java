package webb.server.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import webb.shared.dtos.old.Player_OLD;

import java.io.File;
import java.util.*;

@Deprecated
public class MockPlayerDatabase {
    private final Map<String, Player_OLD> players = new HashMap<>();
    private final String FP = "src/main/resources/webb/data/userdata.json";

    public MockPlayerDatabase() {
        ObjectMapper mapper = new ObjectMapper();

        File file = new File(FP);

        try {
            Player_OLD[] playerList = mapper.readValue(file, Player_OLD[].class);

            for(Player_OLD player : playerList) {
                players.put(player.username(), player);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Player_OLD get(String username) {
        return players.get(username);
    }

    public List<Player_OLD> getAll() {
        return new ArrayList<>(players.values());
    }

    public boolean exists(String username) {
        Player_OLD player = players.get(username);

        return player != null;
    }
}
