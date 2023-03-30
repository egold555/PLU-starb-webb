package webb.client.model.puzzle;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Represents a cell from the JSON file.
 * https://cs.plu.edu/courses/protected/cs390/2023s/project/iteration2.html
 */
public class CellDTO {

    private final int row;
    private final int col;
    private String type = "EMPTY";

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
    public String getType() {return type;}

    public void setEmpty(){type = "EMPTY";}

    public void setStar(){type = "STAR";}

    public void setMarker(){type = "VMARKER";}

    public void setInvalid(String direction){
        if(direction.equals("R")){
            type = "RINVALID";
        }
        if(direction.equals("C")){
            type = "CINVALID";
        }
    }

    @Override
    public String toString() {
        return "CellDTO{" +
                "row=" + row +
                ", col=" + col +
                '}';
    }

    public void changeType(Boolean lClick){
        if(lClick){
            if(type.equals("EMPTY")) {
                type = "STAR";
            }
            else if(type.equals("STAR")||type.equals("RINVALID")||type.equals("CINVALID")){
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
}
