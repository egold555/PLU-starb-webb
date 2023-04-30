package webb.client.ui.constants;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import javax.imageio.ImageIO;

/**
 * This class contains all the images used.
 * This essentially acts as a cache for the images, so they are only loaded once.
 *
 * If an image is not found, a pink and black checkerboard image is returned.
 */
public class WebbImages {

    private static final BufferedImage ERROR_IMAGE = createErrorImage(300, 300);


    public static final BufferedImage PUZZLE_SELECTION_ARROW_PAGE_BACK = loadImage("arrow_page_back.png");

    //Puzzle selection screen
    public static final BufferedImage PUZZLE_SELECTION_ARROW_FORWARD = loadImage("screen_puzzle_selection/arrow_forward.png");
    public static final BufferedImage PUZZLE_SELECTION_ARROW_FORWARD_DISABLED = loadImage("screen_puzzle_selection/arrow_forward_disabled.png");
    public static final BufferedImage PUZZLE_SELECTION_ARROW_BACK = loadImage("screen_puzzle_selection/arrow_back.png");
    public static final BufferedImage PUZZLE_SELECTION_ARROW_BACK_DISABLED = loadImage("screen_puzzle_selection/arrow_back_disabled.png");
    public static final BufferedImage PUZZLE_SELECTION_BUTTON_TROPHY = loadImage("screen_puzzle_selection/button_trophy.png");
    public static final BufferedImage PUZZLE_SELECTION_BUTTON_STATS = loadImage("screen_puzzle_selection/button_stats.png");
    public static final BufferedImage PUZZLE_SELECTION_LEVEL_COMPLETE_EMBLEM = loadImage("screen_puzzle_selection/level_complete_emblem.png");
    public static final BufferedImage PUZZLE_SELECTION_STAR = loadImage("screen_puzzle_selection/star.png");

    public static final BufferedImage POPUP_CLOSE = loadImage("popup_close_button.png");

    //Popup congratulation images
    public static final BufferedImage POPUP_CONGRATULATIONS_STAR = loadImage("popup_congratulations/star.png");
    public static final BufferedImage POPUP_CONGRATULATIONS_STAR_ROTATED = loadImage("popup_congratulations/star_rotated.png");

    //Play puzzle screen
    public static final BufferedImage PLAY_PUZZLE_STAR = loadImage("screen_play_puzzle/star.png");
    public static final BufferedImage PLAY_PUZZLE_VALIDATE_BUTTON = loadImage("screen_play_puzzle/validate_button.png");
    public static final BufferedImage PLAY_PUZZLE_HINT_BUTTON = loadImage("screen_play_puzzle/hint_button.png");
    public static final BufferedImage PLAY_PUZZLE_RESET_BUTTON = loadImage("screen_play_puzzle/reset_button.png");
    public static final BufferedImage PLAY_PUZZLE_STAR_BACKGROUND = loadImage("screen_play_puzzle/background_star.png");
    public static final BufferedImage PLAY_PUZZLE_EXIT_BUTTON = loadImage("screen_play_puzzle/exit_button.png");

    public static final BufferedImage PLAY_PUZZLE_GRID_STAR = loadImage("screen_play_puzzle/grid_star.png");
    public static final BufferedImage PLAY_PUZZLE_GRID_INVALID_CELL = loadImage("screen_play_puzzle/grid_invalid_cell.png");
    public static final BufferedImage PLAY_PUZZLE_GRID_STAR_RED = loadImage("screen_play_puzzle/grid_star_red.png");
    public static final BufferedImage PLAY_PUZZLE_GRID_AVMARKER = loadImage("screen_play_puzzle/grid_avmarker.png");

    public static final BufferedImage MAIN_MENU_PLANET = loadImage("screen_main_menu/planet.png");
    public static final BufferedImage MAIN_MENU_PLANET2 = loadImage("screen_main_menu/planet2.png");
    public static final BufferedImage MAIN_MENU_UFO = loadImage("screen_main_menu/ufo.png");
    public static final BufferedImage MAIN_MENU_UFO2 = loadImage("screen_main_menu/ufo2.png");
    public static final BufferedImage MAIN_MENU_ROCKET = loadImage("screen_main_menu/rocket.png");
    public static final BufferedImage MAIN_MENU_ROCKET2 = loadImage("screen_main_menu/rocket2.png");
    public static final BufferedImage MAIN_MENU_ROCKET3 = loadImage("screen_main_menu/rocket3.png");
    public static final BufferedImage MAIN_MENU_STAR = loadImage("screen_main_menu/star.png");
    public static final BufferedImage MAIN_MENU_EARTH = loadImage("screen_main_menu/earth.png");
    public static final BufferedImage MAIN_MENU_MOON = loadImage("screen_main_menu/moon.png");
    public static final BufferedImage MAIN_MENU_SATELLITE = loadImage("screen_main_menu/satellite.png");

    public static final BufferedImage[] MAIN_MENU_IMAGES = {
        MAIN_MENU_PLANET,
        MAIN_MENU_PLANET2,
        MAIN_MENU_UFO,
        MAIN_MENU_UFO2,
        MAIN_MENU_ROCKET,
        MAIN_MENU_ROCKET2,
        MAIN_MENU_ROCKET3,
        MAIN_MENU_STAR,
        MAIN_MENU_EARTH,
        MAIN_MENU_MOON,
        MAIN_MENU_SATELLITE
    };


    /**
     * Attempts to load an image from the resources folder.
     * If the image is not found, a pink and black checkerboard image is returned.
     *
     * @param imageName The name of the image to load
     * @return The image
     */
    private static BufferedImage loadImage(String imageName) {
        final String imagePath = "/webb/images/" + imageName;
        try {
            InputStream is = WebbImages.class.getResourceAsStream(imagePath);
            if(is == null) {
                System.err.println("Could not find image: " + imagePath);
                return ERROR_IMAGE;
            }

            return ImageIO.read(is);
        }
        catch(Exception e) {
            System.err.println("Could not load image: " + imagePath);
            e.printStackTrace();
            return ERROR_IMAGE;
        }

    }

    /**
     * Creates a pink and black checkerboard image.
     *
     * @param width The width of the image
     * @param height The height of the image
     * @return The image
     */
    private static BufferedImage createErrorImage(int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Color c1 = new Color(255, 105, 180);
        Color c2 = Color.BLACK;

        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                if((x + y) % 2 == 0) {
                    image.setRGB(x, y, c1.getRGB());
                }
                else {
                    image.setRGB(x, y, c2.getRGB());
                }
            }
        }

        return image;
    }

    public static BufferedImage scale(BufferedImage oldBufferedImage, double scale) {
        final int width = oldBufferedImage.getWidth();
        final int height = oldBufferedImage.getHeight();

        final int scaledWidth = (int) (width * scale);
        final int scaledHeight = (int) (height * scale);

        BufferedImage newBufferedImage = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_ARGB);

        AffineTransform transform = AffineTransform.getScaleInstance(scale, scale);
        AffineTransformOp transformOP = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);

        transformOP.filter(oldBufferedImage, newBufferedImage);

        return newBufferedImage;
    }

    public static BufferedImage rotate(BufferedImage img, double angle) {

        final double rads = Math.toRadians(angle);
        final double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
        final int w = img.getWidth();
        final int h = img.getHeight();
        final int newWidth = (int) Math.floor(w * cos + h * sin);
        final int newHeight = (int) Math.floor(h * cos + w * sin);

        BufferedImage rotated = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotated.createGraphics();
        AffineTransform at = new AffineTransform();

        final double tx = (newWidth - w) / 2d;
        final double ty = (newHeight - h) / 2d;

        at.translate(tx, ty);

        final int x = w / 2;
        final int y = h / 2;

        at.rotate(rads, x, y);
        g2d.setTransform(at);
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();

        return rotated;
    }

}
