package webb.client.ui.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JComponent;
import javax.swing.JProgressBar;
import javax.swing.plaf.basic.BasicProgressBarUI;

/**
 * A customizable progress bar wrapper for JProgressBar.
 */
public class WebbProgressBar extends JProgressBar {

    private final Color COLOR_BACKGROUND;
    private final Color COLOR_BAR;

    /**
     * Creates a new WebbProgressBar with the given colors.
     * @param background The background color of the progress bar.
     * @param barColor The color of the progress bar.
     */
    public WebbProgressBar(Color background, Color barColor) {
        this.COLOR_BACKGROUND = background;
        this.COLOR_BAR = barColor;
        setBorderPainted(false);
        setOpaque(false);
        setUI(new WebbProgressBarUI());
    }

    /**
     * UI class for the WebbProgressBar.
     */
    private class WebbProgressBarUI extends BasicProgressBarUI {
        private static final int STROKE_WIDTH = 3;

        @Override
        protected void paintDeterminate(Graphics g, JComponent c) {

            Graphics2D g2d = (Graphics2D) g.create();

            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


            // Draw the background
            g2d.setColor(COLOR_BACKGROUND);

            int width = progressBar.getWidth();
            int height = progressBar.getHeight();

            RoundRectangle2D backgroundRectangle = new RoundRectangle2D.Double((STROKE_WIDTH / 2), (STROKE_WIDTH / 2), width - STROKE_WIDTH, height - STROKE_WIDTH, height, height);
            g2d.fill(backgroundRectangle);

            // Draw the progress bar
            final int innerHeight = height - (STROKE_WIDTH * 4);
            int innerWidth = width - (STROKE_WIDTH * 4);

            double dProgress = progressBar.getPercentComplete();
            if (dProgress < 0) {
                dProgress = 0;
            }
            else if (dProgress > 1) {
                dProgress = 1;
            }

            innerWidth = (int) Math.round(innerWidth * dProgress);

            g2d.setColor(COLOR_BAR);
            RoundRectangle2D fill = new RoundRectangle2D.Double(STROKE_WIDTH * 2, STROKE_WIDTH * 2, innerWidth, innerHeight, innerHeight, innerHeight);
            g2d.fill(fill);

            g2d.dispose();
        }
    }
}
