package webb.shared.dtos.puzzle.created;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;
import webb.shared.dtos.puzzle.CellDTO;

/**
 * Represents a puzzle from the JSON file.
 * Modified from: https://cs.plu.edu/courses/protected/cs390/2023s/project/iteration2.html
 */
@ToString
public class CreatePuzzleLevelDTO {

    /**
     * @return the regions of the puzzle as a list of Cells
     */
    @NotNull
    @Getter
    private final List<List<CellDTO>> regions;

    /**
     * @return the solution to the puzzle as a list of Cells
     */
    @NotNull
    @Getter
    private final List<CellDTO> solution;

    /**
     * @return the size of the grid
     */
    @NotNull
    @Getter
    private final int gridSize;

    /**
     * @return the number of stars per region
     */
    @NotNull
    @Getter
    private final int numStars;

    /**
     * Constructor for CreatePuzzleLevelDTO
     * @param regions the regions of the puzzle as a list of CellDTO
     * @param solution the solution to the puzzle as a list of CellDTO
     * @param gridSize the size of the grid
     * @param numStars the number of stars per region
     */
    public CreatePuzzleLevelDTO(@JsonProperty("regions") List<List<CellDTO>> regions,
                                @JsonProperty("solution") List<CellDTO> solution,
                                @JsonProperty("gridSize") int gridSize,
                                @JsonProperty("numStars") int numStars) {
        this.regions = regions;
        this.solution = solution;
        this.gridSize = gridSize;
        this.numStars = numStars;
    }

    /**
     * @return the total number of stars in the puzzle
     */
    @JsonIgnore
    public int getTotalStars() {return numStars * regions.size();}

}
