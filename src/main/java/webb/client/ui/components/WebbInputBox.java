package webb.client.ui.components;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class WebbInputBox extends JTextField {

    private int arcRadius = 10;

    private final Container container;

    public WebbInputBox(Container container, int length) {
        super(length);
        this.container = container;

        //setContentAreaFilled(false);
        setBorder(new EmptyBorder(8, 8, 8, 8));
        setForeground(Color.WHITE);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    long lastPaint = 0;
    boolean drawingVerticalLine = false;

    @Override
    protected void paintComponent(Graphics g) {
        //super.paintComponent(g);

        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics.setColor(getBackground());
        graphics.fillRoundRect(0, 0, getWidth(), getHeight(), arcRadius, arcRadius);


        if(System.currentTimeMillis() - lastPaint > 1000) {
            drawingVerticalLine = !drawingVerticalLine;
            lastPaint = System.currentTimeMillis();
        }

        if(drawingVerticalLine) {
            graphics.setColor(Color.WHITE);
            graphics.drawLine(getWidth() - 10, 10, getWidth() - 10, getHeight() - 10);

        }


        this.paintText(graphics);
        repaint();
    }

    private void paintText(Graphics2D g) {
        // Draw font
        g.setColor(getForeground());
        if (this.getFont() != null && this.getText() != null) {
            FontMetrics fm = getFontMetrics(getFont());
            g.setColor(this.getForeground());
            g.drawString(this.getText(), ((this.getWidth() / 2) - (fm.stringWidth(this.getText()) / 2)),
                    ((this.getHeight() / 2) + fm.getMaxDescent()));
        }
    }
}
