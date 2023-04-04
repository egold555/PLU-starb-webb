package webb.client.model.puzzle;

import com.fasterxml.jackson.annotation.JsonCreator;
import webb.client.ui.screens.puzzlescreen.Cell.CellType;

/**
 * Represents a cell from the JSON file.
 * https://cs.plu.edu/courses/protected/cs390/2023s/project/iteration2.html
 */
public class CellDTO {

    private final int row;
    private final int col;
    private CellType type = CellType.EMPTY;

    @JsonCreator
    public CellDTO( int[] values ) {
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

    /**
     * @return the type of the cell
     */
    public CellType getType() {return type;}

    public void setEmpty(){type = CellType.EMPTY;}

    public void setStar(){type = CellType.STAR;}

    public void setMarker(){type = CellType.VMARKER;}

    public void setInvalid(){
        type = CellType.INVALID;
    }

    public void setAMarker(){type = CellType.AMARKER;}

    @Override
    public String toString() {
        return "CellDTO{" +
                "row=" + row +
                ", col=" + col +
                '}';
    }

    public void changeType(Boolean lClick){
        if(lClick){
            if(type == CellType.EMPTY) {
                type = CellType.STAR;
            }
            else if(type == CellType.STAR || type == CellType.INVALID){
                type = CellType.PLAYER_MARKER;
            }
            else if(type == CellType.PLAYER_MARKER){
                type = CellType.EMPTY;
            }
        }
        else{
            if(type == CellType.STAR || type == CellType.INVALID || type == CellType.PLAYER_MARKER) {
                type = CellType.EMPTY;
            }
        }
    }
}
