package webb.shared.dtos.puzzle.created;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import webb.shared.dtos.puzzle.CellDTO;

/**
 * Represents a puzzle from the JSON file.
 * Modified from: https://cs.plu.edu/courses/protected/cs390/2023s/project/iteration2.html
 */
@ToString
@Getter
@Setter
public class CreatePuzzleLevelDTO {

    /**
     * @param regions the regions of the puzzle as a list of Cells
     * @return the regions of the puzzle as a list of Cells
     */
    @NotNull
    private List<List<CellDTO>> regions;


    /**
     * @param solution the solution to the puzzle as a list of Cells
     * @return the solution to the puzzle as a list of Cells
     */
    @NotNull
    private List<CellDTO> solution;

    /**
     * @param gridSize the size of the grid
     * @return the size of the grid
     */
    @NotNull
    private int gridSize;


    /**
     * @param numStars the number of stars per region
     * @return the number of stars per region
     */
    @NotNull
    private int numStars;

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
