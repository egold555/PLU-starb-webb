package webb.shared.dtos.puzzle;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import webb.shared.dtos.puzzle.updated.UpdatePuzzleLevelDTO;

/**
 * Represents a puzzle from the JSON file.
 * Modified from: https://cs.plu.edu/courses/protected/cs390/2023s/project/iteration2.html
 */
public class PuzzleLevelDTO extends UpdatePuzzleLevelDTO {

    private final int id;

    /**
     * PuzzleLevelDTO constructor
     * @param id the id of the puzzle
     * @param regions the regions of the puzzle
     * @param solution the solution to the puzzle
     * @param gridSize the size of the grid
     * @param numStars the number of stars per region
     * @param solvedByNumPlayers the number of players who have solved the puzzle
     */
    public PuzzleLevelDTO(@JsonProperty("id") int id,
                          @JsonProperty("regions") List<List<CellDTO>> regions,
                          @JsonProperty("solution") List<CellDTO> solution,
                          @JsonProperty("gridSize") int gridSize,
                          @JsonProperty("numStars") int numStars,
                          @JsonProperty("solvedByNumPlayers") int solvedByNumPlayers) {
        super(regions, solution, gridSize, numStars, solvedByNumPlayers);
        this.id = id;
    }

    /**
     * @return the id of the puzzle
     */
    public int getId() {return id;}

    /**
     * Creates a PuzzleDTO from a JSON file
     * @param jsonFile the JSON file
     * @return the PuzzleDTO object
     * @throws java.io.IOException if the file cannot be read
     */
    public static PuzzleLevelDTO fromJSON(File jsonFile) throws IOException {
        return new ObjectMapper().readValue(jsonFile, PuzzleLevelDTO.class);
    }

    @Override
    public String toString() {
        return "PuzzleLevelDTO{" +
                "id=" + id +
                "} " + super.toString();
    }
}
