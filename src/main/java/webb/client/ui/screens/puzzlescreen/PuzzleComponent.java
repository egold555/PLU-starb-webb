package webb.client.ui.screens.puzzlescreen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.border.LineBorder;

public class PuzzleComponent extends JComponent {

    private static final int MARGIN = 15;
    private static final int SIZE = 10;

    //COL, ROW
    private final Cell[][] cells = new Cell[SIZE][SIZE];

    public PuzzleComponent() {
        this.setBorder( new LineBorder( Color.BLUE, 2, true ));
        this.setOpaque(false);

        // Initialize the cells
        for( int row = 0; row < SIZE; row++ ) {
            for( int col = 0; col < SIZE; col++ ) {
                cells[col][row] = new Cell( col, row );
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        final int width = this.getWidth();
        final int height = this.getHeight();
        
        final double centerX = width / 2D;
        final double centerY = height / 2D;
        final double gridSize = Math.min(width, height) - (MARGIN * 2 );
        final double cellSize = gridSize / SIZE;
        
        final double gridLeft = centerX - gridSize / 2D;
        final double gridTop = centerY - gridSize / 2D;

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor( Color.black );

        // Draw the grid
        Line2D.Double line = new Line2D.Double();
        for( int i = 0; i < SIZE + 1; i++ ) {
            line.x1 = gridLeft;
            line.y1 = gridTop + i * cellSize;
            line.x2 = gridLeft + cellSize * SIZE;
            line.y2 = line.y1;
            g2d.draw( line );
        }

        for( int i = 0; i < SIZE + 1; i++ ) {
            line.x1 = gridLeft + i * cellSize;
            line.y1 = gridTop;
            line.x2 = line.x1;
            line.y2 = gridTop + cellSize * SIZE;
            g2d.draw( line );
        }

        //Update the position of the cell objects when we resize
        // Make sure to call this BEFORE drawing the images
        for( int row = 0; row < SIZE; row++ ) {
            for( int col = 0; col < SIZE; col++ ) {
                Cell cell = cells[col][row];
                cell.setBounds( (gridLeft + col * cellSize), (gridTop + row * cellSize), cellSize, cellSize );
            }
        }

        for( int row = 0; row < SIZE; row++ ) {
            for( int col = 0; col < SIZE; col++ ) {
                Cell cell = cells[col][row];
                cell.draw(g2d);
            }
        }

    }
    
    class Cell {

        private int row, col;
        private double sx, sy, sw, sh;

        public Cell(int col, int row) {
            this.row = row;
            this.col = col;
        }

        public int getRow() {return row;}

        public int getCol() {return col;}

        protected void draw(Graphics2D g2d) {
            g2d.setColor( Color.red );
            g2d.fillOval( (int) sx, (int) sy, (int) sw, (int) sh);
        }

        protected void setBounds(double x, double y, double width, double height) {
            this.sx = x;
            this.sy = y;
            this.sw = width;
            this.sh = height;
        }

    }

}
