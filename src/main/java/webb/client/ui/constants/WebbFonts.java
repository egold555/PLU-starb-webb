package webb.client.ui.constants;


import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.InputStream;
import javax.swing.JLabel;

/**
 * This class loads all of the fonts .
 * It acts as a cache for the fonts, so that they don't have to be loaded every time they are used.
 */
public class WebbFonts {

    // This font is used if a font could not be loaded.
    // https://stackoverflow.com/a/11592041
    private static final Font ERROR_FONT = new JLabel().getFont();

    // All of these don't have a size by default. Use the .deriveFont() method to set the size.
    private static final Font BALSAMIQ_SANS_REGULAR = loadFontFromFile("BalsamiqSans-Regular");
    private static final Font BALSAMIQ_SANS_BOLD = loadFontFromFile("BalsamiqSans-Bold");
    private static final Font BALSAMIQ_SANS_ITALIC = loadFontFromFile("BalsamiqSans-Italic");
    private static final Font BALSAMIQ_SANS_BOLD_ITALIC = loadFontFromFile("BalsamiqSans-BoldItalic");

    //Bold
    public static final Font BALSAMIQ_SANS_BOLD_24 = BALSAMIQ_SANS_BOLD.deriveFont(24f);

    //Regular
    public static final Font BALSAMIQ_SANS_REGULAR_13 = BALSAMIQ_SANS_REGULAR.deriveFont(13f);
    public static final Font BALSAMIQ_SANS_REGULAR_20 = BALSAMIQ_SANS_REGULAR.deriveFont(20f);
    public static final Font BALSAMIQ_SANS_REGULAR_24 = BALSAMIQ_SANS_REGULAR.deriveFont(24f);
    public static final Font BALSAMIQ_SANS_REGULAR_32 = BALSAMIQ_SANS_REGULAR.deriveFont(32f);
    public static final Font BALSAMIQ_SANS_REGULAR_48 = BALSAMIQ_SANS_REGULAR.deriveFont(48f);
    public static final Font BALSAMIQ_SANS_REGULAR_64 = BALSAMIQ_SANS_REGULAR.deriveFont(64f);
    public static final Font BALSAMIQ_SANS_REGULAR_72 = BALSAMIQ_SANS_REGULAR.deriveFont(72f);

    /**
     * Loads a font from a file in the resources folder.
     * @param fontName The name of the font file, without the extension.
     * @return The loaded font, or an error font if the font could not be loaded.
     */
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
