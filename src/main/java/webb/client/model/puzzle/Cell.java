package webb.client.model.puzzle;

public class Cell {
    private final int row;
    private final int col;
    private String type = "BLANK";

    public Cell(int row, int col){
        this.row = row;
        this.col = col;
    }

    /**
     * @return the type of the cell
     */
    public String getType() {return type;}


    public void changeType(Boolean lClick){
        if(lClick){
            if(type == "BLANK") {
                type = "STAR";
            }
            else if(type == "STAR"){
                type = "MARKER";
            }
            else if(type == "MARKER"){
                type = "BLANK";
            }
        }
        else{
            type = "BLANK";
        }
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
