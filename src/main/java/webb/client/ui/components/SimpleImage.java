package webb.client.ui.components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;

public class SimpleImage extends JComponent {

    private final BufferedImage image;

    public SimpleImage(BufferedImage image, int width, int height) {
        super();
        this.image = image;
        this.setPreferredSize(new Dimension(width, height));
        this.setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        super.paintComponent(g);
    }
}
