package webb.client.logic.puzzle;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * This class represents a single cell in the puzzle grid.
 * Allows us to not deal with coordinates and just use row and col.
 */
@ToString
public class CellLogic {

    /**
     * @return Row of the cell
     */
    @Getter
    private final int row;


    /**
     * @return Column of the cell
     */
    @Getter
    private final int col;


    /**
     * @return Type of the cell
     */
    @Getter
    private CellType type = CellType.EMPTY;

    public static final int NO_GROUP = -1;

    /**
     * Which group the cell is in.
     * @param group Group of the cell
     * @return Group of the cell
     */
    @Getter
    @Setter
    private int group = NO_GROUP;

    public static final int WALL_NORTH = 0;
    public static final int WALL_EAST = 1;
    public static final int WALL_SOUTH = 2;
    public static final int WALL_WEST = 3;


    /**
     * Get the walls of the cell
     * @return Array of walls
     * @see CellLogic#WALL_NORTH
     * @see CellLogic#WALL_EAST
     * @see CellLogic#WALL_SOUTH
     * @see CellLogic#WALL_WEST
     */
    @Getter
    private final boolean[] walls = new boolean[4]; //N, E, S, W


    /**
     * @return true if the cell is in the solution
     */
    @Getter
    private boolean isSolutionStar = false;



    /**
     * Set if the cell should draw the icon
     * @param drawIcon True if the cell should draw the icon
     */
    @Setter
    private boolean drawIcon = true;

    /**
     * Create a new cell
     * @param col Column of the cell
     * @param row Row of the cell
     */
    public CellLogic(int col, int row) {
        this.row = row;
        this.col = col;
    }

    /**
     * Set the type of the cell
     * @param type Type of the cell
     */
    public void setType(CellType type) {
        this.type = type;
        this.drawIcon = type != CellType.AMARKER && type != CellType.VMARKER;
    }

    /**
     * Called when the cell is clicked
     * @param rightClick True if the right mouse button was clicked
     */
    public void onClick(boolean rightClick) {
        this.changeType(rightClick);
    }

    private void changeType(boolean rightClick) {

        if(!rightClick){
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

        System.out.println("Clicked on cell: " + col + ", " + row + " rightClick: " + rightClick + " type: " + type);
    }

    /**
     * Set witch side(s) of the cell has a wall
     * @see CellLogic#WALL_NORTH
     * @see CellLogic#WALL_EAST
     * @see CellLogic#WALL_SOUTH
     * @see CellLogic#WALL_WEST
     * @param wall Side to set the wall on
     */
    public void setWall(int wall) {walls[wall] = true;}

    /**
     * Set the cell as in the solution
     * */
    public void setSolutionStar() {this.isSolutionStar = true;}

    /**
     * Check if the cell should draw the icon
     * @return True if the cell should draw the icon
     */
    public boolean shouldDrawIcon() {return drawIcon;}

}
