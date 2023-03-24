package webb.model.puzzle;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class PuzzleDTO {

    private List<List<CellDTO>> regions;
    private List<CellDTO> solution;
    private int gridSize;
    private int numStars;

    private PuzzleDTO() {}

    // Getters and setters for each field

    public List<List<CellDTO>> getRegions() {return regions;}

    public List<CellDTO> getSolution() {return solution;}

    public int getGridSize() {return gridSize;}

    public int getNumStars() {return numStars;}

    public static PuzzleDTO fromJSON(File jsonFile) throws IOException {
        return new ObjectMapper().readValue(new File("puzzles/puzzle-1-1-1.json"), PuzzleDTO.class);
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
