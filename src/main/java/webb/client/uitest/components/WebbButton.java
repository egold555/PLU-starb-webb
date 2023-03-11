package webb.client.uitest.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import webb.client.uitest.constants.WebbColors;
import webb.client.uitest.constants.WebbFonts;

public class WebbButton extends JButton {

    private static final int DEFAULT_BORDER_SIZE = 10;

    private int borderSize = DEFAULT_BORDER_SIZE;
    private Color textColor = WebbColors.TEXT_COLOR_BLACK;
    private String text = null;
    private BufferedImage image = null;
    private Font font = WebbFonts.BALSAMIQ_SANS_REGULAR_20;

    public WebbButton() {
        this(null, null);
    }

    public WebbButton(String text) {
        this(text, null);
    }

    public WebbButton(BufferedImage image) {
        this(null, image);
    }

    public WebbButton(String text, BufferedImage image) {
        super();
        this.text = text;
        this.image = image;
        setContentAreaFilled(false);
        setBorder(new EmptyBorder(8, 8, 8, 8));
        setForeground(Color.WHITE);
    }

    public void setBorderSize(int borderSize) {this.borderSize = borderSize;}
    public void setTextColor(Color textColor) {this.textColor = textColor;}

    @Override public void setFont(Font font) {this.font = font;}
    @Override public Font getFont() {return font;}

    @Override public void setText(String text) {this.text = text;}
    @Override public String getText() {return text;}

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(WebbColors.B7);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), borderSize, borderSize);

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
            int x = (getWidth() - this.image.getWidth()) / 2;
            int y = (getHeight() - this.image.getHeight()) / 2;
            g2.drawImage(this.image, x, y, null);
        }

        g2.dispose();
        super.paint(g);
    }
}
