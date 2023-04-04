package webb.client.ui.screens.puzzlescreen;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import webb.client.ui.constants.WebbColors;
import webb.client.ui.constants.WebbImages;

/**
 * This class represents a single cell in the puzzle grid.
 * Allows us to not deal with coordinates and just use row and col.
 * Also allows easy use of drawing sprites.
 */
public class Cell {

    private final int row, col;
    private double sx, sy, sw, sh;
    private CellType type = CellType.EMPTY;

    public static final int NO_GROUP = -1;
    private int group = NO_GROUP;

    protected static final int WALL_NORTH = 0;
    protected static final int WALL_EAST = 1;
    protected static final int WALL_SOUTH = 2;
    protected static final int WALL_WEST = 3;
    private final boolean[] walls = new boolean[4]; //N, E, S, W

    private boolean isSolutionStar = false;

    /**
     * Create a new cell
     * @param col Column of the cell
     * @param row Row of the cell
     */
    public Cell(int col, int row) {
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
    public void setType(CellType type) {this.type = type;}

    /**
     * Checks to see if the mouse cords are inside the cell
     * @param x X cord of the mouse
     * @param y Y cord of the mouse
     * @return True if the mouse is inside the cell
     */
    protected boolean isInside(double x, double y) {
        return (x >= sx && x <= sx + sw && y >= sy && y <= sy + sh);
    }

    /**
     * Called when the cell is clicked
     * @param rightClick True if the right mouse button was clicked
     */
    protected void onClick(boolean rightClick) {
        System.out.println("Clicked on cell: " + col + ", " + row + " rightClick: " + rightClick);
    }

    /**
     * Set the group of the cell
     * @param group Group of the cell
     */
    protected void setGroup(int group) {this.group = group;}

    /**
     * Get the group of the cell
     * @return Group of the cell
     */
    protected int getGroup() {return group;}

    /**
     * Set witch side(s) of the cell has a wall
     * @see Cell#WALL_NORTH
     * @see Cell#WALL_EAST
     * @see Cell#WALL_SOUTH
     * @see Cell#WALL_WEST
     * @param wall Side to set the wall on
     */
    protected void setWall(int wall) {walls[wall] = true;}

    /**
     * Set the cell as in the solution
     * */
    protected void setSolutionStar() {this.isSolutionStar = true;}

    /**
     * Check if the cell is in the solution
     */
    protected boolean isSolutionStar() {return isSolutionStar;}

    /**
     * Draw the image for the cell, given the cell type
     * @param g2d Graphics2D object to draw with
     */
    protected void draw(Graphics2D g2d) {

        int imgOffset = 20;
        final int imgX = (int) (sx + imgOffset);
        final int imgY = (int) (sy + imgOffset);
        final int imgW = (int) (sw - (imgOffset * 2));
        final int imgH = (int) (sh - (imgOffset * 2));

        if(this.type.getImage() != null) {
            g2d.drawImage(this.type.getImage(), imgX, imgY, imgW, imgH, null );
        }

        //Draw walls
        g2d.setColor(Color.RED );
        g2d.setStroke( new BasicStroke( 5 ));

        //North
        if(walls[WALL_NORTH]) {
            g2d.drawLine((int)sx, (int)sy, (int)(sx+sw), (int)sy);
        }

        //East
        if(walls[WALL_EAST]) {
            g2d.drawLine((int)(sx+sw), (int)sy, (int)(sx+sw), (int)(sy+sh));
        }

        //South
        if(walls[WALL_SOUTH]) {
            g2d.drawLine((int)(sx+sw), (int)(sy+sh), (int)sx, (int)(sy+sh));
        }

        //West
        if(walls[WALL_WEST]) {
            g2d.drawLine((int)sx, (int)(sy+sh), (int)sx, (int)sy);
        }
    }

    /**
     * Set the bounds of the cell when we repaint the scene
     * This is used so we can easily calculate if the mouse is inside the cell
     * @param x X cord of the cell
     * @param y Y cord of the cell
     * @param width Width of the cell
     * @param height Height of the cell
     */
    protected void setBounds(double x, double y, double width, double height) {
        this.sx = x;
        this.sy = y;
        this.sw = width;
        this.sh = height;
    }

    /**
     * The type of the cell
     */
    public enum CellType {
        EMPTY((BufferedImage) null), // Empty cell
        STAR(WebbImages.PLAY_PUZZLE_GRID_STAR), // White star the player placed
        STAR_RED(WebbImages.PLAY_PUZZLE_GRID_STAR_RED), // Invalid star the player placed
        X(WebbImages.PLAY_PUZZLE_GRID_INVALID_CELL) // X mark that the player placed
        ;

        private final BufferedImage img;

        //Clone the cell type's image
        CellType(CellType cellType) {
            this.img = cellType.getImage();
        }

        //Create a new cell type
        CellType(BufferedImage image) {
            this.img = image;
        }

        public BufferedImage getImage() {
            return img;
        }
    }

}
