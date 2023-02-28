package starb.client.ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * A GUI component demonstrating how to use Graphics2D to draw shapes and
 * images.  The paintComponent method does all of the drawing, and is called
 * automatically by the event system.  Never call paintComponent directly.
 * Instead, a call to paintComponent can be triggered by calling the repaint()
 * method (inherited).
 *
 * https://docs.oracle.com/en/java/javase/17/docs/api/java.desktop/java/awt/Component.html#repaint()
 *
 * For more information about 2D graphics in Java see:
 *
 * https://docs.oracle.com/javase/tutorial/2d/index.html
 */
public class ExampleDrawingPanel extends JComponent {

    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;

    private static final String STAR_IMAGE_FILE = "image/star_gold.png";

    private BufferedImage starImage;
    private static final String SPOT_IMAGE_FILE = "image/spot_the_cow.png";
    private BufferedImage spotImage;

    // Grid dimensions and location
    private int cellSize = 40;
    private int rows = 10;
    private int cols = 10;
    private Point2D.Double gridUpperLeft = new Point2D.Double(15,15);

    public ExampleDrawingPanel() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        // Load the image files
        try {
            starImage = ImageIO.read(new File(STAR_IMAGE_FILE));
            spotImage = ImageIO.read(new File(SPOT_IMAGE_FILE));
        } catch(IOException e) {
            String message = "Unable to load image: " + STAR_IMAGE_FILE;
            System.err.println(message);
            System.err.println(e.getMessage());
            throw new RuntimeException(message);
        }

        this.addMouseListener( new DrawPanelMouseListener() );
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor( Color.black );

        // Example grid
        Line2D.Double line = new Line2D.Double();
        for( int i = 0; i < rows + 1; i++ ) {
            line.x1 = gridUpperLeft.x;
            line.y1 = gridUpperLeft.y + i * cellSize;
            line.x2 = gridUpperLeft.x + cellSize * cols;
            line.y2 = line.y1;
            g2d.draw( line );
        }
        for( int i = 0; i < cols + 1; i++ ) {
            line.x1 = gridUpperLeft.x + i * cellSize;
            line.y1 = gridUpperLeft.y;
            line.x2 = line.x1;
            line.y2 = gridUpperLeft.y + cellSize * rows;
            g2d.draw( line );
        }

        // Draw a thicker line, left side of cells in column
        BasicStroke stroke = new BasicStroke(5.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER);
        g2d.setStroke(stroke);
        int column = 1, startCellY = 1, endCellY = 3;
        line.x1 = column * cellSize + gridUpperLeft.x;
        line.y1 = startCellY * cellSize + gridUpperLeft.y;
        line.x2 = line.x1;
        line.y2 = (endCellY + 1) * cellSize + gridUpperLeft.y;
        g2d.draw(line);

        // Draw stars in a few places
        drawStar( 3, 4, g2d );
        drawStar( 1, 1, g2d );
        drawStar( 0, 2, g2d );

        // Draw the cow
        float scale = 0.4f;
        g2d.drawImage( spotImage, (int)(gridUpperLeft.x + cellSize * cols + 5), (int)gridUpperLeft.y,
                (int)(spotImage.getWidth() * scale), (int)(spotImage.getHeight() * scale), this);
    }

    private void drawStar( int row, int col, Graphics2D g2d ) {
        g2d.drawImage(starImage,
                (int)(gridUpperLeft.x + row * cellSize),
                (int)(gridUpperLeft.y + col * cellSize),
                cellSize, cellSize,
                this
                );
    }

    private class DrawPanelMouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.printf("Click: (%d, %d)%n", e.getX(), e.getY());
        }
    }
}
