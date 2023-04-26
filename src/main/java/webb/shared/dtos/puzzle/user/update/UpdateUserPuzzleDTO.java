package webb.shared.dtos.puzzle.user.update;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;
import lombok.ToString;
import webb.shared.dtos.puzzle.CellDTO;

@ToString
public class UpdateUserPuzzleDTO {

    /**
     * @return whether the puzzle has been completed
     */
    @Getter
    private final boolean completed;


    /**
     * @return the time it took to solve the puzzle in ms
     */
    @Getter
    private final long solveTime; //in milliseconds


    /**
     * @return the list of markers placed on the puzzle
     */
    @Getter
    private final List<CellDTO> placedMarkers;


    /**
     * @return the list of stars placed on the puzzle
     */
    @Getter
    private final List<CellDTO> placedStars;


    /**
     * @return the number of stars remaining on the puzzle
     */
    @Getter
    private final int starsRemaining;

    /** Creates a new UpdateUserPuzzleDTO object
     * @param completed whether the puzzle has been completed
     * @param solveTime the time it took to solve the puzzle in ms
     * @param placedMarkers the list of markers placed on the puzzle
     * @param placedStars the list of stars placed on the puzzle
     * @param starsRemaining the number of stars remaining on the puzzle
     */
    public UpdateUserPuzzleDTO(@JsonProperty("completed") boolean completed,
                               @JsonProperty("solveTime") long solveTime,
                               @JsonProperty("placedMarkers") List<CellDTO> placedMarkers,
                               @JsonProperty("placedStars") List<CellDTO> placedStars,
                               @JsonProperty("starsRemaining") int starsRemaining) {
        this.completed = completed;
        this.solveTime = solveTime;
        this.placedMarkers = placedMarkers;
        this.placedStars = placedStars;
        this.starsRemaining = starsRemaining;
    }

}
