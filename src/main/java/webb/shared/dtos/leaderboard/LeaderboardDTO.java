package webb.shared.dtos.leaderboard;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class LeaderboardDTO {

    private final List<LeaderboardEntryDTO> data;

    /**
     * Create a leaderboard object
     * @param data the list of leaderboard entries
     */
    public LeaderboardDTO(@JsonProperty("data") List<LeaderboardEntryDTO> data) {
        this.data = data;
    }

    /**
     * @return the data
     */
    public List<LeaderboardEntryDTO> getData() {return data;}
}
