package webb.client.ui.constants;

import java.awt.Color;

/**
 * A class containing all the colors used in the application.
 * This makes it easier to change the color scheme, and relate them to Figma
 */
public class WebbColors {

    private WebbColors() {}

    public static final Color B7 = Color.decode("#B7B7B7");
    public static final Color D9 = Color.decode("#D9D9D9");
    public static final Color c91 = Color.decode("#919191");
    public static final Color c90 = Color.decode("#909090");
    public static final Color c22 = Color.decode("#222222");
    public static final Color c6C = Color.decode("#6c6c6c");
    public static final Color c34 = Color.decode("#343434");
    public static final Color c70 = Color.decode("#707070");
    public static final Color c7F = Color.decode("#7F7F7F");


    public static final Color TEXT_COLOR_WHITE = Color.WHITE;
    public static final Color TEXT_COLOR_BLACK = c22;

    public static final Color TRANSPARENT = new Color(0, 0, 0, 0);


    public static Color getColorBetween(Color one, Color two, double ratio) {
        int red = (int)Math.abs((ratio * one.getRed()) + ((1 - ratio) * two.getRed()));
        int green = (int)Math.abs((ratio * one.getGreen()) + ((1 - ratio) * two.getGreen()));
        int blue = (int)Math.abs((ratio * one.getBlue()) + ((1 - ratio) * two.getBlue()));
        return new Color(red, green, blue);
    }

}
