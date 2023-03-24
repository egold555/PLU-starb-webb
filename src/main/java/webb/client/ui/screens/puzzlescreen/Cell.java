package webb.client.ui.screens.puzzlescreen;

import java.awt.Graphics2D;
import webb.client.ui.constants.WebbImages;

public class Cell {

    private final int row, col;
    private double sx, sy, sw, sh;
    private CellType type = CellType.EMPTY;

    public Cell(int col, int row) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {return row;}
    public int getCol() {return col;}

    public CellType getType() {return type;}
    public void setType(CellType type) {this.type = type;}

    protected boolean isInside(double x, double y) {
        return (x >= sx && x <= sx + sw && y >= sy && y <= sy + sh);
    }

    protected void onClick(boolean rightClick) {
        System.out.println("Clicked on cell: " + col + ", " + row + " rightClick: " + rightClick);
    }

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
            case INVALID:
                g2d.drawImage(WebbImages.PLAY_PUZZLE_GRID_INVALID_CELL, imgX, imgY, imgW, imgH, null );
                break;
        }
    }

    protected void setBounds(double x, double y, double width, double height) {
        this.sx = x;
        this.sy = y;
        this.sw = width;
        this.sh = height;
    }

    public enum CellType {
        EMPTY, STAR, STAR_RED, INVALID
    }

}
