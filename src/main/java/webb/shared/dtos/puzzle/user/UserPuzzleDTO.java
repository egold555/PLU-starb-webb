package webb.shared.dtos.puzzle.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import webb.shared.dtos.puzzle.CellDTO;
import webb.shared.dtos.puzzle.user.update.UpdateUserPuzzleDTO;

public class UserPuzzleDTO extends UpdateUserPuzzleDTO {

    private final int id;
    private final String user;

    /**
     * Creates a new UpdateUserPuzzleDTO object
     *
     * @param id             the id of the puzzle
     * @param user           the username of the associated user
     * @param completed      whether the puzzle has been completed
     * @param solveTime      the time it took to solve the puzzle in ms
     * @param placedMarkers  the list of markers placed on the puzzle
     * @param placedStars    the list of stars placed on the puzzle
     * @param starsRemaining the number of stars remaining on the puzzle
     */
    public UserPuzzleDTO(@JsonProperty("id") int id,
                         @JsonProperty("username") String user,
                         @JsonProperty("completed") boolean completed,
                         @JsonProperty("solveTime") long solveTime,
                         @JsonProperty("placedMarkers") List<CellDTO> placedMarkers,
                         @JsonProperty("placedStars") List<CellDTO> placedStars,
                         @JsonProperty("starsRemaining") int starsRemaining) {
        super(completed, solveTime, placedMarkers, placedStars, starsRemaining);
        this.id = id;
        this.user = user;
    }

    /**
     * @return the id of the puzzle
     */
    public int getId() {return id;}
}
