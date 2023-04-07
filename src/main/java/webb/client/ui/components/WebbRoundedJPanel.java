package webb.client.ui.components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 * A JPanel that has rounded corners.
 */
public class WebbRoundedJPanel extends JPanel {

    private final int rWidth, rHeight;

    /**
     * Creates a new WebbRoundedJPanel with 15px rounded corners.
     */
    public WebbRoundedJPanel() {
        this(15, 15);
    }

    /**
     * Creates a new WebbRoundedJPanel.
     *
     * @param roundWidth The width of the rounded corners.
     * @param roundHeight The height of the rounded corners.
     */
    public WebbRoundedJPanel(int roundWidth, int roundHeight) {
        this.rWidth = roundWidth;
        this.rHeight = roundHeight;
        this.setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension arcs = new Dimension(rWidth, rHeight);
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        //graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


        //Draws the rounded panel with borders.
        graphics.setColor(getBackground());
        graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint background

        //graphics.setColor(getForeground());
        //graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
    }

}
