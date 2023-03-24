package webb.client.ui.components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;

/**
 * A simple image component that can be used to display an image
 */
public class WebbSimpleImage extends JComponent {

    private final BufferedImage image;

    /**
     * Creates a new image component
     * @param image The image to display
     * @param width The width of the component
     * @param height The height of the component
     */
    public WebbSimpleImage(BufferedImage image, int width, int height) {
        this.image = image;
        this.setPreferredSize(new Dimension(width, height));
        this.setOpaque(false);
    }

    /**
     * Paints the image
     * @param g The graphics object
     */
    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        super.paintComponent(g);
    }
}
