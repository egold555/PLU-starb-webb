package webb.shared.dtos.puzzle.user.update;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;
import lombok.ToString;
import webb.shared.dtos.puzzle.CellDTO;
import webb.shared.dtos.puzzle.updated.UpdatePuzzleLevelDTO;

@ToString
public class UpdateUserPuzzleDTO {
    private boolean completed;
    private long solveTime; //in milliseconds

    private List<CellDTO> placedMarkers;
    private  List<CellDTO> placedStars;

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

    public UpdateUserPuzzleDTO() {
    }

    /**
     * @return whether the puzzle has been completed
     */
    public boolean isCompleted() {return completed;}

    /**
     * @return the time it took to solve the puzzle in ms
     */
    public long getSolveTime() {return solveTime;}

    /**
     * @return the list of markers placed on the puzzle
     */
    public List<CellDTO> getPlacedMarkers() {return placedMarkers;}

    /**
     * @return the list of stars placed on the puzzle
     */
    public List<CellDTO> getPlacedStars() {return placedStars;}

    /**
     * @return the number of stars remaining on the puzzle
     */
    public int getStarsRemaining() {return starsRemaining;}

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setSolveTime(long solveTime) {
        this.solveTime = solveTime;
    }

    public void setPlacedMarkers(List<CellDTO> placedMarkers) {
        this.placedMarkers = placedMarkers;
    }

    public void setPlacedStars(List<CellDTO> placedStars) {
        this.placedStars = placedStars;
    }

    public void setStarsRemaining(int starsRemaining) {
        this.starsRemaining = starsRemaining;
    }
}
