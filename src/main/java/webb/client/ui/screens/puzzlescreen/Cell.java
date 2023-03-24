package webb.client.ui.screens.puzzlescreen;

import java.awt.Graphics2D;
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
     * Draw the image for the cell, given the cell type
     * @param g2d Graphics2D object to draw with
     */
    protected void draw(Graphics2D g2d) {

        int imgOffset = 20;
        final int imgX = (int) (sx + imgOffset);
        final int imgY = (int) (sy + imgOffset);
        final int imgW = (int) (sw - (imgOffset * 2));
        final int imgH = (int) (sh - (imgOffset * 2));

        switch(this.type) {
            case EMPTY:
                break;
            case STAR:
                g2d.drawImage(WebbImages.PLAY_PUZZLE_GRID_STAR, imgX, imgY, imgW, imgH, null );
                break;
            case STAR_RED:
                g2d.drawImage(WebbImages.PLAY_PUZZLE_GRID_STAR_RED, imgX, imgY, imgW, imgH, null );
                break;
            case X:
                g2d.drawImage(WebbImages.PLAY_PUZZLE_GRID_INVALID_CELL, imgX, imgY, imgW, imgH, null );
                break;
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
        EMPTY, // Empty cell
        STAR, // White star the player placed
        STAR_RED, // Invalid star the player placed
        X // X mark that the player placed
        ;
    }

}
