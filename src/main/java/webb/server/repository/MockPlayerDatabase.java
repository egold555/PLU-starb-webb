package webb.server.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import webb.model.Player;

import java.io.File;
import java.util.*;

public class MockPlayerDatabase {
    private final Map<String, Player> players = new HashMap<>();
    private final String FP = "src/main/resources/webb/data/userdata.json";

    public MockPlayerDatabase() {
        ObjectMapper mapper = new ObjectMapper();

        File file = new File(FP);

        try {
            List<Player> playerList = Arrays.asList(mapper.readValue(file, Player[].class));

            for(Player player : playerList) {
                players.put(player.username(), player);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Player get(String username) {
        return players.get(username);
    }

    public List<Player> getAll() {
        return new ArrayList<>(players.values());
    }

    public boolean exists(String username) {
        Player player = players.get(username);

        return player != null;
    }
}
