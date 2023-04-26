package webb.client.ui.components;

import java.awt.BasicStroke;
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
import javax.swing.border.LineBorder;
import lombok.Getter;
import lombok.Setter;
import webb.client.ui.WebbWindow;
import webb.client.ui.constants.WebbAudio;
import webb.client.ui.constants.WebbColors;
import webb.client.ui.constants.WebbFonts;

public class WebbButton extends JButton {

    private static final int DEFAULT_BORDER_SIZE = 10;


    /**
     * Sets the size of the border around the button.
     * @param borderSize The size of the border.
     */
    @Getter
    private int borderSize = DEFAULT_BORDER_SIZE;


    /**
     * The text color of the button.
     * @param textColor The color of the text to set it to
     */
    @Setter
    private Color textColor = WebbColors.TEXT_COLOR_BLACK;

    private String text = null;

    /**
     * Sets the image of the button.
     * @param image The image to display on the button.
     */
    @Setter
    private BufferedImage image = null;

    private Font font = WebbFonts.BALSAMIQ_SANS_REGULAR_20;


    private boolean drawBackground = true;


    /**
     * Sets the background color of the button.
     * @param backgroundColor The color of the background.
     */
    @Setter
    private Color backgroundColor = WebbColors.B7;


    private boolean hovering = false;


    /**
     * Sets whether or not the button should draw an outline.
     * @param drawButtonOutline Whether or not to draw an outline.
     */
    @Setter
    private boolean drawButtonOutline = true;

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
     * @param text The text to display on the button.
     * @param imageIn The image to display on the button.
     * @param width The width of the image / button.
     * @param height The height of the image / button.
     * @param clickListener The action to perform when the button is clicked.
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
        this.drawButtonOutline = false;
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
                public void mouseEntered(MouseEvent e) {
                    WebbWindow.getInstance().getSfxPlayer().queue(WebbAudio.SFX_BTN_HOVER);
                    hovering = true;
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    setBorder(new EmptyBorder(8, 8, 8, 8));
                    hovering = false;
                }

                @Override
                public void mousePressed(MouseEvent e) {

                    final boolean isLeftClick = e.getButton() == MouseEvent.BUTTON1;
                    final boolean isRightClick = e.getButton() == MouseEvent.BUTTON3;

                    //they middle clicked or something?
                    if(!isLeftClick && !isRightClick) return;

                    if (isRightClick) {
                        self.getModel().setPressed(true);
                    }

                    WebbWindow.getInstance().getSfxPlayer().queue(WebbAudio.SFX_CLICK);
                    clickListener.onClick(self, isRightClick);

                }
            });

        }
    }

    /**
     * Sets the action to perform when the button is released.
     * @param clickReleaseListener The action to perform when the button is released.
     */
    public void setClickReleaseListener(ClickReleaseListener clickReleaseListener) {
        WebbButton self = this;
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                final boolean isLeftClick = e.getButton() == MouseEvent.BUTTON1;
                final boolean isRightClick = e.getButton() == MouseEvent.BUTTON3;

                //they middle clicked or something?
                if(!isLeftClick && !isRightClick) return;

                self.getModel().setPressed(false);

                clickReleaseListener.onClickRelease(self, isRightClick);
            }
        });
    }

    /**
     * Sets whether or not the button should draw a background.
     * @param drawBackground Whether or not to draw a background.
     */
    public void setDrawBackground(boolean drawBackground) {
        this.drawBackground = drawBackground;
        this.setOpaque(drawBackground);
    }

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

        if(drawButtonOutline) {
            g2.setColor(Color.BLACK);
            final int strokeSize = this.hovering ? 3 : 1;
            g2.setStroke(new BasicStroke(strokeSize));
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, borderSize, borderSize);
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

    public static interface ClickReleaseListener {
        /**
         * Called when the button is released.
         * @param self The button that was released.
         * @param rightClick Whether or not the right mouse button was released.
         */
        void onClickRelease(WebbButton self, boolean rightClick);
    }
}
