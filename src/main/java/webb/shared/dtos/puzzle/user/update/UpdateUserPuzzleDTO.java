package webb.shared.dtos.puzzle.user.update;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import webb.shared.dtos.puzzle.CellDTO;
import webb.shared.dtos.puzzle.updated.UpdatePuzzleLevelDTO;

@ToString
@NoArgsConstructor
@Getter
@Setter
public class UpdateUserPuzzleDTO {

    /**
     * @param completed whether the puzzle has been completed
     * @return whether the puzzle has been completed
     */
    private boolean completed;

    /**
     * @param solveTime the time it took to solve the puzzle in ms
     * @return the time it took to solve the puzzle in ms
     */
    private long solveTime; //in milliseconds

    /**
     * @param placedMarkers the list of markers placed on the puzzle
     * @return the list of markers placed on the puzzle
     */
    private List<CellDTO> placedMarkers;

    /**
     * @param placedStars the list of stars placed on the puzzle
     * @return the list of stars placed on the puzzle
     */
    private  List<CellDTO> placedStars;

    /**
     * @param starsRemaining the number of stars remaining on the puzzle
     * @return the number of stars remaining on the puzzle
     */
    private int starsRemaining;

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
