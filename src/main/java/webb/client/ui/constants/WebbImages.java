package webb.client.ui.constants;

import java.awt.Color;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public class WebbImages {

    private static final BufferedImage ERROR_IMAGE = createErrorImage(300, 300);

    public static final BufferedImage PUZZLE_SELECTION_ARROW_PAGE_BACK = loadImage("arrow_page_back.png");
    public static final BufferedImage PUZZLE_SELECTION_ARROW_FORWARD = loadImage("arrow_forward.png");
    public static final BufferedImage PUZZLE_SELECTION_ARROW_BACK = loadImage("arrow_back.png");
    public static final BufferedImage PUZZLE_SELECTION_BUTTON_TROPHY = loadImage("button_trophy.png");
    public static final BufferedImage PUZZLE_SELECTION_BUTTON_STATS = loadImage("button_stats.png");
    public static final BufferedImage PUZZLE_SELECTION_LEVEL_COMPLETE_EMBLEM = loadImage("level_complete_emblem.png");
    public static final BufferedImage PUZZLE_SELECTION_STAR = loadImage("star.png");

    public static final BufferedImage POPUP_CLOSE = loadImage("popup_close_button.png");

    //Popup congratulation images
    public static final BufferedImage POPUP_CONGRATULATIONS_STAR = loadImage("congratulations_star.png");
    public static final BufferedImage POPUP_CONGRATULATIONS_STAR_ROTATED = loadImage("congratulations_star_rotated.png");

    public static final BufferedImage PLAY_PUZZLE_STAR = loadImage("play_puzzle_star.png");
    public static final BufferedImage PLAY_PUZZLE_VALIDATE_BUTTON = ERROR_IMAGE;
    public static final BufferedImage PLAY_PUZZLE_HINT_BUTTON = ERROR_IMAGE;
    public static final BufferedImage PLAY_PUZZLE_RESET_BUTTON = ERROR_IMAGE;
    public static final BufferedImage PLAY_PUZZLE_STAR_BACKGROUND = loadImage("play_puzzle_background_star.png");
    public static final BufferedImage PLAY_PUZZLE_EXIT_BUTTON = loadImage("play_puzzle_exit_button.png");

    public static final BufferedImage PLAY_PUZZLE_GRID_STAR = loadImage("play_puzzle_grid_star.png");
    public static final BufferedImage PLAY_PUZZLE_GRID_INVALID_CELL = loadImage("play_puzzle_grid_invalid_cell.png");
    public static final BufferedImage PLAY_PUZZLE_GRID_STAR_RED = loadImage("play_puzzle_grid_star_red.png");

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

    //create pink and black checkerboard image
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

}
