package webb.client.ui.screens.puzzlescreen;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import javax.swing.JComponent;
import webb.client.ui.constants.WebbColors;

public class PuzzleComponent extends JComponent {

    private static final int MARGIN = 15;
    private int gridSize = 10;

    //COL, ROW
    private Cell[][] cells;

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
                for(int row = 0; row < gridSize; row++ ) {
                    for(int col = 0; col < gridSize; col++ ) {
                        Cell cell = getCell(col, row);
                        if( cell.isInside(x, y) ) {
                            cell.onClick(rightClick);
                        }
                    }
                }
            }
        });
    }

    public void setGridSize(int size) {
        gridSize = size;
        cells = null;
        cells = new Cell[gridSize][gridSize];
        for(int row = 0; row < gridSize; row++ ) {
            for(int col = 0; col < gridSize; col++ ) {
                cells[col][row] = new Cell( col, row );
            }
        }

        //if(repaint) {
            this.repaint();
        //}
    }

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
        final double cellSize = gridSize / this.gridSize;

        final double gridLeft = centerX - gridSize / 2D;
        final double gridTop = centerY - gridSize / 2D;

        //draw background
        g2d.setColor( WebbColors.c34 );
        g2d.fillRect( (int) gridLeft, (int) gridTop, (int) gridSize, (int) gridSize);

        g2d.setColor(WebbColors.c70 );
        g2d.setStroke( new BasicStroke( 3 ));

        // Draw the grid
        Line2D.Double line = new Line2D.Double();
        for(int i = 0; i < this.gridSize + 1; i++ ) {
            line.x1 = gridLeft;
            line.y1 = gridTop + i * cellSize;
            line.x2 = gridLeft + cellSize * this.gridSize;
            line.y2 = line.y1;
            g2d.draw( line );
        }

        for(int i = 0; i < this.gridSize + 1; i++ ) {
            line.x1 = gridLeft + i * cellSize;
            line.y1 = gridTop;
            line.x2 = line.x1;
            line.y2 = gridTop + cellSize * this.gridSize;
            g2d.draw( line );
        }

        //draw border
        g2d.setColor( WebbColors.c90 );
        g2d.setStroke( new BasicStroke( 5 ));
        g2d.drawRect( (int) gridLeft, (int) gridTop, (int) gridSize, (int) gridSize);

        //Update the position of the cell objects when we resize
        // Make sure to call this BEFORE drawing the images
        for(int row = 0; row < this.gridSize; row++ ) {
            for(int col = 0; col < this.gridSize; col++ ) {
                Cell cell = getCell(col, row);
                cell.setBounds( (gridLeft + col * cellSize), (gridTop + row * cellSize), cellSize, cellSize );
            }
        }

        //draw all the cells
        for(int row = 0; row < this.gridSize; row++ ) {
            for(int col = 0; col < this.gridSize; col++ ) {
                Cell cell = getCell(col, row);
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
    public Cell getCell(int col, int row) {
        if(col < 0 || col >= gridSize || row < 0 || row >= gridSize) {
            throw new IllegalArgumentException("Invalid cell position!");
        }
        return cells[col][row];
    }
}
