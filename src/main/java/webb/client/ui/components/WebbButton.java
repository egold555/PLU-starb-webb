package webb.client.ui.components;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

import webb.client.ui.constants.WebbColors;
import webb.client.ui.constants.WebbFonts;

public class WebbButton extends JButton {

    private static final int DEFAULT_BORDER_SIZE = 10;

    private int borderSize = DEFAULT_BORDER_SIZE;
    private Color textColor = WebbColors.TEXT_COLOR_BLACK;
    private String text = null;
    private BufferedImage image = null;
    private Font font = WebbFonts.BALSAMIQ_SANS_REGULAR_20;
    private boolean drawBackground = true;
    private Color backgroundColor = WebbColors.B7;

    public WebbButton(String text) {
        this(text, null);
    }

    public WebbButton(String text, Runnable onClick) {
        this(onClick);
        this.text = text;
    }

    public WebbButton(BufferedImage imageIn, int width, int height) {
        this(imageIn, width, height, null);
    }

    public WebbButton(BufferedImage imageIn, int width, int height, Runnable onClick) {
        this(onClick);
        this.image = imageIn;
        this.setDrawBackground(false);
        this.setPreferredSize(new Dimension(width, height));
    }

    public WebbButton(Runnable onClick) {
        setContentAreaFilled(false);
        setBorder(new EmptyBorder(8, 8, 8, 8));
        setForeground(Color.WHITE);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        if(onClick != null) {
            this.addActionListener(e -> onClick.run());
        }
    }

    public void setBorderSize(int borderSize) {this.borderSize = borderSize;}
    public void setTextColor(Color textColor) {this.textColor = textColor;}
    public void setDrawBackground(boolean drawBackground) {
        this.drawBackground = drawBackground;
        this.setOpaque(drawBackground);
    }
    public void setBackgroundColor(Color backgroundColor) {this.backgroundColor = backgroundColor;}

    @Override public void setFont(Font font) {this.font = font;}
    @Override public Font getFont() {return font;}

    @Override public void setText(String text) {this.text = text;}
    @Override public String getText() {return text;}

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if(drawBackground) {
            g2.setColor(backgroundColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), borderSize, borderSize);
        }
        //draw center text
        if(this.text != null) {
            g2.setColor(textColor);
            g2.setFont(this.font);
            FontMetrics fm = g2.getFontMetrics();
            int x = (getWidth() - fm.stringWidth(getText())) / 2;
            int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
            g2.drawString(getText(), x, y);
        }

        //draw center image
        if(this.image != null) {
            g2.drawImage(this.image, 0, 0, getWidth(), getHeight(), null);
        }

        g2.dispose();
        super.paint(g);
    }
}
