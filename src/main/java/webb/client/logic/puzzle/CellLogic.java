package webb.client.logic.puzzle;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import webb.shared.dtos.puzzle.CellDTO;

/**
 * This class represents a single cell in the puzzle grid.
 * Allows us to not deal with coordinates and just use row and col.
 */
public class CellLogic {

    private final int row, col;

    private CellType type = CellType.EMPTY;

    public static final int NO_GROUP = -1;
    private int group = NO_GROUP;

    public static final int WALL_NORTH = 0;
    public static final int WALL_EAST = 1;
    public static final int WALL_SOUTH = 2;
    public static final int WALL_WEST = 3;
    private final boolean[] walls = new boolean[4]; //N, E, S, W

    private boolean isSolutionStar = false;

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
     * Get the row of the cell
     * @return Row of the cell
     */
    public int getRow() {return row;}

    /**
     * Get the column of the cell
     * @return Column of the cell
     */
    public int getCol() {return col;}

    /**
     * Get the type of the cell
     * @return Type of the cell
     */
    public CellType getType() {return type;}

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
            if(type == CellType.EMPTY || type == CellType.AMARKER || type == CellType.VMARKER) {
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
     * Set the group of the cell
     * @param group Group of the cell
     */
    public void setGroup(int group) {this.group = group;}

    /**
     * Get the group of the cell
     * @return Group of the cell
     */
    public int getGroup() {return group;}

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
     * Get the walls of the cell
     * @return Array of walls
     * @see CellLogic#WALL_NORTH
     * @see CellLogic#WALL_EAST
     * @see CellLogic#WALL_SOUTH
     * @see CellLogic#WALL_WEST
     */
    public boolean[] getWalls() {return walls;}

    /**
     * Set the cell as in the solution
     * */
    public void setSolutionStar() {this.isSolutionStar = true;}

    /**
     * Check if the cell is in the solution
     */
    public boolean isSolutionStar() {return isSolutionStar;}

    /**
     * Set if the cell should draw the icon
     * @param drawIcon True if the cell should draw the icon
     */
    public void setDrawIcon(boolean drawIcon) {
        this.drawIcon = drawIcon;
    }

    /**
     * Check if the cell should draw the icon
     * @return True if the cell should draw the icon
     */
    public boolean shouldDrawIcon() {return drawIcon;}

    public CellDTO toDTO() {
        //must be row, col
        return new CellDTO(new int[]{row, col});
    }
}
