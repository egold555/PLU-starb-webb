package webb.shared.dtos.leaderboard;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
public class LeaderboardEntryDTO implements Comparable<LeaderboardEntryDTO> {

    /**
     * @return the username of the user
     */
    @NotBlank
    @Getter
    private final String username;


    /**
     * @return the amount of puzzles they have finished
     */
    @NotNull
    @Getter
    private final int completedPuzzles;

    /**
     * Create a leaderboard entry object
     * @param username of the person
     * @param completedPuzzles number of puzzles they have created
     */
    public LeaderboardEntryDTO(@JsonProperty("username") String username,
            @JsonProperty("completedPuzzles") int completedPuzzles
    ) {
        this.username = username;
        this.completedPuzzles = completedPuzzles;
    }

    /**
     * Sorts by completed puzzles highest to lowest
     * Tiebreaker is alphabetical order
     * @return the comparison
     */
    @Override
    public int compareTo(LeaderboardEntryDTO o) {
        final int completedPuzzlesCompare = Integer.compare(o.completedPuzzles, this.completedPuzzles);
        if(completedPuzzlesCompare != 0) return completedPuzzlesCompare;

        return this.username.compareTo(o.username);
    }
}
