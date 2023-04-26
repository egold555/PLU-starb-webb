package webb.shared.dtos.leaderboard;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
public class LeaderboardDTO {

    /**
     * @return the data
     */
    @NotNull
    @Getter
    private final List<LeaderboardEntryDTO> data;

    /**
     * Create a leaderboard object
     * @param data the list of leaderboard entries
     */
    public LeaderboardDTO(@JsonProperty("data") List<LeaderboardEntryDTO> data) {
        this.data = data;
    }

}
