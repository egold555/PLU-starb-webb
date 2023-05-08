package webb.client.ui.screens.puzzlescreen;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import webb.client.logic.puzzle.CellLogic;
import webb.client.logic.puzzle.CellType;
import webb.client.ui.constants.WebbColors;
import webb.shared.dtos.puzzle.CellDTO;

/**
 * This class represents a single cell in the puzzle grid.
 * Allows us to not deal with coordinates and just use row and col.
 * This class handles the drawing of the cell logic class.
 */
public class CellComponent {

    private final CellLogic logic;
    private double sx, sy, sw, sh;

    /**
     * Create a new cell
     * @param col Column of the cell
     * @param row Row of the cell
     */
    public CellComponent(int col, int row) {
       this.logic = new CellLogic(col, row);
    }

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
    public void onClick(boolean rightClick) {
        this.logic.onClick(rightClick);
    }


    /**
     * Get the logic data for the cell
     * @return the logic data class
     */
    public CellLogic getLogic() {
        return logic;
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

        if(this.logic.shouldDrawIcon() && this.logic.getType().getImage() != null) {
            g2d.drawImage(this.logic.getType().getImage(), imgX, imgY, imgW, imgH, null );
        }

        //Draw walls
        g2d.setColor(WebbColors.D9);
        g2d.setStroke( new BasicStroke( 15, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND ));

        //North
        if(this.logic.getWalls()[CellLogic.WALL_NORTH]) {
            g2d.drawLine((int)sx, (int)sy, (int)(sx+sw), (int)sy);
        }

        //East
        if(this.logic.getWalls()[CellLogic.WALL_EAST]) {
            g2d.drawLine((int)(sx+sw), (int)sy, (int)(sx+sw), (int)(sy+sh));
        }

        //South
        if(this.logic.getWalls()[CellLogic.WALL_SOUTH]) {
            g2d.drawLine((int)(sx+sw), (int)(sy+sh), (int)sx, (int)(sy+sh));
        }

        //West
        if(this.logic.getWalls()[CellLogic.WALL_WEST]) {
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

    //-------------------- Getters and Setters For Easy Logic --------------------
    public int getGroup() {return this.logic.getGroup();}
    public void setWall(int wall) {this.logic.setWall(wall);}
    public CellType getType() {return this.logic.getType();}
    public void setGroup(int group) {this.logic.setGroup(group);}
    public void setType(CellType type) {this.logic.setType(type);}
    public int getRow() {return this.logic.getRow();}
    public int getCol() {return this.logic.getCol();}
    public void setSolutionStar() {this.logic.setSolutionStar();}
    public boolean isSolutionStar() {return this.logic.isSolutionStar();}

    public CellDTO toDTO() {return this.logic.toDTO();}
}
