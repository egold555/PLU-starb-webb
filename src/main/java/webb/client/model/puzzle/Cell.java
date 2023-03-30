package webb.client.model.puzzle;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Represents a cell from the JSON file.
 * https://cs.plu.edu/courses/protected/cs390/2023s/project/iteration2.html
 */
public class Cell {

    private final int row;
    private final int col;
    private String type = "EMPTY";

    @JsonCreator
    public Cell( int[] values ) {
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

    public void changeType(Boolean lClick){
        if(lClick){
            if(type.equals("EMPTY")) {
                type = "STAR";
            }
            else if(type.equals("STAR")){
                type = "MARKER";
            }
            else if(type.equals("MARKER")){
                type = "EMPTY";
            }
        }
        else{
            type = "EMPTY";
        }
    }

    public void setMarker(){type = "MARKER";}

    @Override
    public String toString() {
        return "CellDTO{" +
                "row=" + row +
                ", col=" + col +
                '}';
    }
}
