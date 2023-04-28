package webb.shared.dtos.leaderboard;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LeaderboardEntryDTO implements Comparable<LeaderboardEntryDTO> {

    @NotBlank
    private final String username;
    @NotNull
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
     * @return the username of the user
     */
    public String getUsername() {return username;}

    /**
     * @return the amount of puzzles they have finished
     */
    public int getCompletedPuzzles() {return completedPuzzles;}

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
    @Override
    public boolean equals(Object o){
        if(o == this){
            return true;
        }
        if(!(o instanceof LeaderboardEntryDTO)){
            return false;
        }
        LeaderboardEntryDTO other = (LeaderboardEntryDTO) o;

        return((this.username.equals(other.username)) && (this.completedPuzzles == other.completedPuzzles));
    }
}
