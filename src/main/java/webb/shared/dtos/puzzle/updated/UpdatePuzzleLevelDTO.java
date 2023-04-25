package webb.shared.dtos.puzzle.updated;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import webb.shared.dtos.puzzle.CellDTO;
import webb.shared.dtos.puzzle.created.CreatePuzzleLevelDTO;

/**
 * Represents a puzzle from the JSON file.
 * Modified from: https://cs.plu.edu/courses/protected/cs390/2023s/project/iteration2.html
 */
public class UpdatePuzzleLevelDTO extends CreatePuzzleLevelDTO {

    @NotNull
    private int solvedByNumPlayers;

    /**
     * UpdatePuzzleLevelDTO constructor
     * @param regions the regions of the puzzle as a list of CellDTO
     * @param solution the solution to the puzzle as a list of CellDTO
     * @param gridSize the size of the grid
     * @param numStars the number of stars per region
     * @param solvedByNumPlayers the number of players who have solved the puzzle
     */
    public UpdatePuzzleLevelDTO(@JsonProperty("regions") List<List<CellDTO>> regions,
                                @JsonProperty("solution") List<CellDTO> solution,
                                @JsonProperty("gridSize") int gridSize,
                                @JsonProperty("numStars") int numStars,
                                @JsonProperty("solvedByNumPlayers") int solvedByNumPlayers) {
        super(regions, solution, gridSize, numStars);
        this.solvedByNumPlayers = solvedByNumPlayers;
    }

    /**
     * @return the number of players who have solved the puzzle
     */
    public int getSolvedByNumPlayers() {return solvedByNumPlayers;}

    /**
     * Unsure if this is needed as of now, but I am adding it.
     * @param solvedByNumPlayers the number of players who have solved the puzzle
     */
    public void setSolvedByNumPlayers(int solvedByNumPlayers) {this.solvedByNumPlayers = solvedByNumPlayers;}

    @Override
    public String toString() {
        return "PuzzleLevelDTO{" +
                ", solvedByNumPlayers=" + solvedByNumPlayers +
                "} " + super.toString();
    }
}
