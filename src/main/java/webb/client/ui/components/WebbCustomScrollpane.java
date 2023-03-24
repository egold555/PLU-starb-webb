package webb.client.ui.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicScrollBarUI;

/**
 * Custom scrollbar wrapper UI for JScrollPane
 */
public class WebbCustomScrollpane extends JScrollPane {

    private final Color TRACK_COLOR;
    private final Color BAR_COLOR;
    private final Color BAR_COLOR_HOVER;
    private final Color BAR_COLOR_CLICK;
    private static final int ARC_SIZE = 15;

    /**
     * Creates a new custom scrollbar UI
     * @param trackColor The color of the track
     * @param barColor The color of the bar
     * @param barHoverColor The color of the bar when hovered
     * @param barClickColor The color of the bar when clicked
     */
    public WebbCustomScrollpane(Color trackColor, Color barColor, Color barHoverColor, Color barClickColor) {
        this.TRACK_COLOR = trackColor;
        this.BAR_COLOR = barColor;
        this.BAR_COLOR_HOVER = barHoverColor;
        this.BAR_COLOR_CLICK = barClickColor;

        this.getVerticalScrollBar().setUI(new WebbScrollbarUI());
    }

    public void setVerticalScrollbarWidth(int width) {
        Dimension tmpSize = new Dimension(width, 0);
        this.getVerticalScrollBar().setMaximumSize(tmpSize);
        this.getVerticalScrollBar().setPreferredSize(tmpSize);
        this.getVerticalScrollBar().setMinimumSize(tmpSize);
    }

    /**
     * Custom scrollbar UI
     */
    private class WebbScrollbarUI extends BasicScrollBarUI {

        private static final Dimension EMPTY_DIMENSION = new Dimension();

        /**
         * Hacky way to hide the decrease button
         * @param orientation the orientation
         * @return an empty button
         */
        @Override
        protected JButton createDecreaseButton(int orientation) {
            return new JButton() {
                @Override
                public Dimension getPreferredSize() {
                    return EMPTY_DIMENSION;
                }
            };
        }

        /**
         * Hacky way to hide the increase button
         * @param orientation the orientation
         * @return an empty button
         */
        @Override
        protected JButton createIncreaseButton(int orientation) {
            return new JButton() {
                @Override
                public Dimension getPreferredSize() {
                    return EMPTY_DIMENSION;
                }
            };
        }

        /**
         * Hacky way to not have swing paint the track
         */
        @Override
        protected void paintTrack(Graphics g, JComponent c, Rectangle r) {
            // We don't want to paint the track
        }

        /**
         * Paints the entire component
         * @param g the graphics
         * @param c the component
         * @param r the rectangle
         */
        @Override
        protected void paintThumb(Graphics g, JComponent c, Rectangle r) {

            JScrollBar sb = (JScrollBar) c;
            if (!sb.isEnabled() || r.width > r.height) {return;}

            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Color color = BAR_COLOR;

            if (isDragging) {
                color = BAR_COLOR_CLICK;
            }
            else if (isThumbRollover()) {
                color = BAR_COLOR_HOVER;
            }
            g2.setPaint(color);
            g2.fillRoundRect(r.x, r.y, r.width, r.height, ARC_SIZE, ARC_SIZE);
            g2.setPaint(TRACK_COLOR);
            g2.drawRoundRect(r.x, r.y, r.width, r.height, ARC_SIZE, ARC_SIZE);
            g2.dispose();
        }

        /**
         * Bit of a hack to get java to repaint the scrollbar correctly.
         * @param x set the x location of the thumb
         * @param y set the y location of the thumb
         * @param width set the width of the thumb
         * @param height set the height of the thumb
         */
        @Override
        protected void setThumbBounds(int x, int y, int width, int height) {
            super.setThumbBounds(x, y, width, height);
            scrollbar.repaint();
        }

    }
}
