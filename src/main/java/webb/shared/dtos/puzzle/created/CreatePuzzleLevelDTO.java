package webb.shared.dtos.puzzle.created;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import webb.shared.dtos.puzzle.CellDTO;

/**
 * Represents a puzzle from the JSON file.
 * Modified from: https://cs.plu.edu/courses/protected/cs390/2023s/project/iteration2.html
 */
public class CreatePuzzleLevelDTO {

    @NotNull
    private final List<List<CellDTO>> regions;
    @NotNull
    private final List<CellDTO> solution;
    @NotNull
    private final int gridSize;
    @NotNull
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
     * @return the regions of the puzzle as a list of Cells
     */
    public List<List<CellDTO>> getRegions() {return regions;}

    /**
     * @return the solution to the puzzle as a list of Cells
     */
    public List<CellDTO> getSolution() {return solution;}

    /**
     * @return the size of the grid
     */
    public int getGridSize() {return gridSize;}

    /**
     * @return the number of stars per region
     */
    public int getNumStars() {return numStars;}

    /**
     * @return the total number of stars in the puzzle
     */
    @JsonIgnore
    public int getTotalStars() {return numStars * regions.size();}

    @Override
    public String toString() {
        return "PuzzleDTO{" +
                "regions=" + regions +
                ", solution=" + solution +
                ", gridSize=" + gridSize +
                ", numStars=" + numStars +
                '}';
    }
}
