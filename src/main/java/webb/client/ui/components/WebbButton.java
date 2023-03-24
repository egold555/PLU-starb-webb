package webb.client.ui.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

    /**
     * Creates a new WebbButton with the given text.
     * @param text The text to display on the button.
     * @param clickListener The action to perform when the button is clicked.
     */
    public WebbButton(String text, ClickListener clickListener) {
        this(clickListener);
        this.text = text;
    }

    /**
     * Creates a new WebbButton with the given text and image.
     * @param text
     * @param imageIn
     * @param width
     * @param height
     * @param clickListener
     */
    public WebbButton(String text, BufferedImage imageIn, int width, int height, ClickListener clickListener) {
        this(imageIn, width, height, clickListener);
        this.text = text;
    }


    /**
     * Creates a new WebbButton with the given image.
     * @param imageIn The image to display on the button.
     * @param width The width of the image / button.
     * @param height The height of the image / button.
     * @param clickListener The action to perform when the button is clicked.
     */
    public WebbButton(BufferedImage imageIn, int width, int height, ClickListener clickListener) {
        this(clickListener);
        this.image = imageIn;
        this.setDrawBackground(false);
        this.setPreferredSize(new Dimension(width, height));
    }

    /**
     * Creates a new blank WebbBackButton.
     * @param clickListener The action to perform when the button is clicked.
     */
    public WebbButton(ClickListener clickListener) {
        setContentAreaFilled(false);
        setBorder(new EmptyBorder(8, 8, 8, 8));
        setForeground(Color.WHITE);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        if(clickListener != null) {
            WebbButton self = this;
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {

                    final boolean isLeftClick = e.getButton() == MouseEvent.BUTTON1;
                    final boolean isRightClick = e.getButton() == MouseEvent.BUTTON3;

                    //they middle clicked or something?
                    if(!isLeftClick && !isRightClick) return;

                    if (isRightClick) {
                        self.getModel().setPressed(true);
                    }

                    clickListener.onClick(self, isRightClick);

                }
            });

//            this.addActionListener(e -> onClick.run());
        }
    }

    /**
     * Sets the size of the border around the button.
     * @param borderSize The size of the border.
     */
    public void setBorderSize(int borderSize) {this.borderSize = borderSize;}

    /**
     * Sets the color of the text on the button.
     * @param textColor The color of the text.
     */
    public void setTextColor(Color textColor) {this.textColor = textColor;}

    /**
     * Sets whether or not the button should draw a background.
     * @param drawBackground Whether or not to draw a background.
     */
    public void setDrawBackground(boolean drawBackground) {
        this.drawBackground = drawBackground;
        this.setOpaque(drawBackground);
    }

    /**
     * Sets the background color of the button.
     * @param backgroundColor The color of the background.
     */
    public void setBackgroundColor(Color backgroundColor) {this.backgroundColor = backgroundColor;}

    /**
     * Sets the font of the text on the button.
     * @param font The font of the text.
     */
    @Override public void setFont(Font font) {this.font = font;}

    /**
     * Returns the font of the text on the button.
     * @return The font of the text.
     */
    @Override public Font getFont() {return font;}

    /**
     * Sets the text of the button.
     * @param text The text to display on the button.
     */
    @Override public void setText(String text) {this.text = text;}

    /**
     * Returns the text of the button.
     * @return The text of the button.
     */
    @Override public String getText() {return text;}

    /**
     * Paints the button.
     * @param g the Graphics context in which to paint
     */
    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //draw the background
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

    public static interface ClickListener {
        /**
         * Called when the button is clicked.
         * @param self The button that was clicked.
         * @param rightClick Whether or not the right mouse button was clicked.
         */
        void onClick(WebbButton self, boolean rightClick);
    }
}
