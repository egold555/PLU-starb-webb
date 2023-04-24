package webb.shared.dtos.old;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import webb.shared.dtos.puzzle.CellDTO;

/**
 * Represents a puzzle from the JSON file.
 * Modified from: https://cs.plu.edu/courses/protected/cs390/2023s/project/iteration2.html
 */
@Deprecated
public class PuzzleDTO_OLD {

    private List<List<CellDTO>> regions;
    private List<CellDTO> solution;
    private int gridSize;
    private int numStars;

    private PuzzleDTO_OLD() {}

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
    public int getTotalStars() {return numStars * regions.size();}

    /**
     * Creates a PuzzleDTO from a JSON file
     * @param jsonFile the JSON file
     * @return the PuzzleDTO object
     * @throws IOException if the file cannot be read
     */
    public static PuzzleDTO_OLD fromJSON(File jsonFile) throws IOException {
        return new ObjectMapper().readValue(jsonFile, PuzzleDTO_OLD.class);
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
