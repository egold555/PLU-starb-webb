package webb.shared.dtos.puzzle;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Represents a cell from the JSON file.
 * https://cs.plu.edu/courses/protected/cs390/2023s/project/iteration2.html
 */
public class CellDTO {

    private final int row;
    private final int col;

    @JsonCreator
    public CellDTO(int[] values) {
        row = values[0];
        col = values[1];
    }

    /**
     * @return the column of the cell
     */
    public int getCol() {return col;}

    /**
     * @return the row of the cell
     */
    public int getRow() {return row;}

    @Override
    public String toString() {
        return "CellDTO{" +
                "row=" + row +
                ", col=" + col +
                '}';
    }
}
