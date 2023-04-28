package webb.client.ui.screens.mainmenu.background;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import javax.swing.JComponent;
import webb.client.ui.constants.WebbImages;

/**
 * A panel that draws the background of the main menu.
 */
public class BackgroundSpacePanel extends JComponent {

    private final Set<ImageAndDirection> images = new HashSet<>();

    private final Random RNG = new Random();

    /**
     * Paints the background.
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Remove images that are off screen, and draws the ones on screen.
        Set<ImageAndDirection> toBeRemoved = new HashSet<>();
        for(ImageAndDirection image : images) {
            image.draw(g);

            if(image.isOffScreen() && !image.isStillSpawning()) {
                toBeRemoved.add(image);
            }

            if(image.isStillSpawning() && !image.isOffScreen()) {
                image.setStillSpawning(false);
            }
        }

        images.removeAll(toBeRemoved);

        final boolean spawnRandomImage = RNG.nextInt(0, 200) == 0;

        //Spawn a new image if the RNG says so.
        if(spawnRandomImage) {

            int width = getWidth();
            int height = getHeight();

            //pick a random image
            final BufferedImage img = getARandomImage();

            PositionDirectionSpeedScale pds = PositionDirectionSpeedScale.createRandom(img.getWidth(), img.getHeight(), width, height);

            images.add(new ImageAndDirection(img, pds, width, height));
        }

        //Continue the loop
        repaint();

    }

    /**
     * Returns a random image from the main menu images.
     * @return a random image from the main menu images
     */
    private BufferedImage getARandomImage() {
        return switch (RNG.nextInt(0, 4)) {
            case 0 -> WebbImages.MAIN_MENU_PLANET;
            case 1 -> WebbImages.MAIN_MENU_UFO;
            case 2 -> WebbImages.MAIN_MENU_STAR;
            case 3 -> WebbImages.MAIN_MENU_ROCKET;
            default -> throw new AssertionError();
        };
    }

}
