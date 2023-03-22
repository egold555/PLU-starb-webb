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
import javax.swing.plaf.basic.BasicScrollBarUI;

public class WebbCustomScrollbar extends BasicScrollBarUI {
    private static final Dimension EMPTY_DIMENSION = new Dimension();

    private final Color TRACK_COLOR;
    private final Color BAR_COLOR;
    private final Color BAR_COLOR_HOVER;
    private final Color BAR_COLOR_CLICK;
    private static final int ARC_SIZE = 15;

    public WebbCustomScrollbar(Color trackColor, Color barColor, Color barHoverColor, Color barClickColor) {
        this.TRACK_COLOR = trackColor;
        this.BAR_COLOR = barColor;
        this.BAR_COLOR_HOVER = barHoverColor;
        this.BAR_COLOR_CLICK = barClickColor;
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return new JButton() {
            @Override
            public Dimension getPreferredSize() {
                return EMPTY_DIMENSION;
            }
        };
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return new JButton() {
            @Override
            public Dimension getPreferredSize() {
                return EMPTY_DIMENSION;
            }
        };
    }

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle r) {
        // We don't want to paint the track
    }

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

    @Override
    protected void setThumbBounds(int x, int y, int width, int height) {
        super.setThumbBounds(x, y, width, height);
        scrollbar.repaint();
    }
}
