package webb.client.ui.screens.puzzlescreen;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import webb.client.logic.puzzle.CellLogic;
import webb.client.logic.puzzle.CellType;
import webb.client.logic.puzzle.PuzzleLogic;
import webb.client.model.puzzle.CellDTO;
import webb.client.ui.constants.WebbColors;
import webb.client.model.puzzle.PuzzleDTO;

public class PuzzleComponent extends JComponent {

    private static final int MARGIN = 15;

    private final PuzzleLogic logic = new PuzzleLogic();

    public PuzzleComponent() {
        //this.setBorder( new LineBorder( Color.BLUE, 2, true ));
        this.setOpaque(false);

        //on click event
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                final int x = evt.getX();
                final int y = evt.getY();

                final int btn = evt.getButton();
                final boolean leftClick = btn == MouseEvent.BUTTON1;
                final boolean rightClick = btn == MouseEvent.BUTTON3;

                //We don't want a middle click for example
                if(!leftClick && !rightClick) return;

                //if mouse is in a square
                for(int row = 0; row < logic.getGridSize(); row++ ) {
                    for(int col = 0; col < logic.getGridSize(); col++ ) {
                        CellComponent cell = getCell(col, row);
                        if( cell.isInside(x, y) ) {
                            onClick(cell, rightClick, true);
                            repaint();
                            printBoard();
                        }
                    }
                }
            }
        });
    }

    /**
     * Sets the size of the grid, and resets the cells
     * @param size Size of the grid
     */
    private void setGridSize(int size) {
        logic.setGridSize(size);

        //if(repaint) {
        this.repaint();
        //}
    }

    /**
     * Set the puzzle to be displayed
     * @param puzzle Puzzle to be displayed
     */
    public void setPuzzle(PuzzleDTO puzzle) {

        logic.setPuzzle(puzzle);
        //repaint
        this.repaint();
    }

    /**
     * Mirrors changeType from CellDTO while also clearing adjacently marked cells if c is cleared.
     * @param c
     * @param rightClick
     * @param visible
     */
    public void onClick(CellComponent c, boolean rightClick, boolean visible){
        logic.onClick(c, rightClick, visible);
    }

    /**
     * Primary method for checking and setting if stars are in valid locations
     * @param visible
     */
    public void checkBoard(boolean visible){
        logic.checkBoard(visible);
    }

    public void printBoard(){
        logic.printBoard();
    }

    /**
     * Paints the component
     * @param g the Graphics object
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        //do some fun math to calculate all the positions we need based on the component size
        final int width = this.getWidth();
        final int height = this.getHeight();

        final double centerX = width / 2D;
        final double centerY = height / 2D;
        final double gridSize = Math.min(width, height) - (MARGIN * 2 );
        final double cellSize = gridSize / logic.getGridSize();

        final double gridLeft = centerX - gridSize / 2D;
        final double gridTop = centerY - gridSize / 2D;

        //draw background
        g2d.setColor( WebbColors.c34 );
        g2d.fillRect( (int) gridLeft, (int) gridTop, (int) gridSize, (int) gridSize);

        g2d.setColor(WebbColors.c70 );
        g2d.setStroke( new BasicStroke( 3 ));

        // Draw the grid
        Line2D.Double line = new Line2D.Double();
        for(int i = 0; i < logic.getGridSize() + 1; i++ ) {
            line.x1 = gridLeft;
            line.y1 = gridTop + i * cellSize;
            line.x2 = gridLeft + cellSize * logic.getGridSize();
            line.y2 = line.y1;
            g2d.draw( line );
        }

        for(int i = 0; i < logic.getGridSize() + 1; i++ ) {
            line.x1 = gridLeft + i * cellSize;
            line.y1 = gridTop;
            line.x2 = line.x1;
            line.y2 = gridTop + cellSize * logic.getGridSize();
            g2d.draw( line );
        }

        //draw border
        g2d.setColor( WebbColors.c90 );
        g2d.setStroke( new BasicStroke( 5 ));
        g2d.drawRect( (int) gridLeft, (int) gridTop, (int) gridSize, (int) gridSize);

        //Update the position of the cell objects when we resize
        // Make sure to call this BEFORE drawing the images
        for(int row = 0; row < logic.getGridSize(); row++ ) {
            for(int col = 0; col < logic.getGridSize(); col++ ) {
                CellComponent cell = getCell(col, row);
                cell.setBounds( (gridLeft + col * cellSize), (gridTop + row * cellSize), cellSize, cellSize );
            }
        }

        //draw all the cells
        for(int row = 0; row < logic.getGridSize(); row++ ) {
            for(int col = 0; col < logic.getGridSize(); col++ ) {
                CellComponent cell = getCell(col, row);
                cell.draw(g2d);
            }
        }

    }

    /**
     * Get a cell at a specific position
     * @param col Column of the cell
     * @param row Row of the cell
     * @return The cell at the specified position
     * @throws IllegalArgumentException if the position is invalid
     */
    public CellComponent getCell(int col, int row) {
        return logic.getCell(col, row);
    }
}
