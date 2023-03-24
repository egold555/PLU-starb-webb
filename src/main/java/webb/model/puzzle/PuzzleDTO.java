package webb.model.puzzle;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Represents a puzzle from the JSON file.
 * Modified from: https://cs.plu.edu/courses/protected/cs390/2023s/project/iteration2.html
 */
public class PuzzleDTO {

    private List<List<CellDTO>> regions;
    private List<CellDTO> solution;
    private int gridSize;
    private int numStars;

    private PuzzleDTO() {}

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
     * @return the number of stars in the puzzle
     */
    public int getNumStars() {return numStars;}

    /**
     * Creates a PuzzleDTO from a JSON file
     * @param jsonFile the JSON file
     * @return the PuzzleDTO object
     * @throws IOException if the file cannot be read
     */
    public static PuzzleDTO fromJSON(File jsonFile) throws IOException {
        return new ObjectMapper().readValue(jsonFile, PuzzleDTO.class);
    }

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
