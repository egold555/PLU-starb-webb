package webb.client.ui.constants;


import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.InputStream;

public class WebbFonts {

    private static final Font ERROR_FONT = new Font("Arial", Font.PLAIN, 20);

    // All of these don't have a size by default. Use the .deriveFont() method to set the size.
    private static final Font BALSAMIQ_SANS_REGULAR = loadFontFromFile("BalsamiqSans-Regular");
    private static final Font BALSAMIQ_SANS_BOLD = loadFontFromFile("BalsamiqSans-Bold");
    private static final Font BALSAMIQ_SANS_ITALIC = loadFontFromFile("BalsamiqSans-Italic");
    private static final Font BALSAMIQ_SANS_BOLD_ITALIC = loadFontFromFile("BalsamiqSans-BoldItalic");

    public static final Font BALSAMIQ_SANS_BOLD_24 = BALSAMIQ_SANS_BOLD.deriveFont(24f);

    public static final Font BALSAMIQ_SANS_REGULAR_20 = BALSAMIQ_SANS_REGULAR.deriveFont(20f);
    public static final Font BALSAMIQ_SANS_REGULAR_24 = BALSAMIQ_SANS_REGULAR.deriveFont(24f);
    public static final Font BALSAMIQ_SANS_REGULAR_32 = BALSAMIQ_SANS_REGULAR.deriveFont(32f);
    public static final Font BALSAMIQ_SANS_REGULAR_48 = BALSAMIQ_SANS_REGULAR.deriveFont(48f);
    public static final Font BALSAMIQ_SANS_REGULAR_64 = BALSAMIQ_SANS_REGULAR.deriveFont(64f);
    public static final Font BALSAMIQ_SANS_REGULAR_72 = BALSAMIQ_SANS_REGULAR.deriveFont(72f);

    private static Font loadFontFromFile(String fontName) {
        final String fontPath = "/webb/fonts/" + fontName + ".ttf";
        try {
            InputStream is = WebbFonts.class.getResourceAsStream(fontPath);
            if(is == null) {
                System.err.println("Error loading InputStream from file: " + fontPath);
                return ERROR_FONT;
            }
            Font font = Font.createFont(Font.TRUETYPE_FONT, is);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
            return font;
        }
        catch (Exception e) {
            System.err.println("Error loading font from file: " + fontPath);
            e.printStackTrace();
            return ERROR_FONT;
        }
    }
}
