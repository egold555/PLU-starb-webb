package webb.client.ui.screens.mainmenu.background;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import webb.client.ui.constants.WebbImages;
import webb.client.ui.helpers.Point;

/**
 * A class that represents an image and its direction
 */
public class ImageAndDirection {

    private final BufferedImage bi;
    private final Point position;
    private final Point direction;
    private final double speed;
    private final int screenWidth;
    private final int screenHeight;

    private boolean isStillSpawning = true;

    /**
     * Constructs an ImageAndDirection object from the given BufferedImage and PositionDirectionSpeedScale
     * @param bi the BufferedImage to use
     * @param pds the PositionDirectionSpeedScale to use
     * @param screenWidth the width of the screen
     * @param screenHeight the height of the screen
     */
    public ImageAndDirection(BufferedImage bi, PositionDirectionSpeedScale pds, int screenWidth, int screenHeight) {
        this(scaleAndRotate(bi, pds.getImgScale(), pds.getImgRotation()), pds.getPosition(), pds.getDirection(), pds.getSpeed(), screenWidth, screenHeight);
    }

    @Deprecated
    private ImageAndDirection(BufferedImage bi, Point startingPoint, Point direction, double speed, int screenWidth, int screenHeight) {
        this.bi = bi;
        this.position = startingPoint;
        this.direction = direction;
        this.speed = speed;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    /**
     * Scales and rotates the given image
     * @param img the image to scale and rotate
     * @param scale the scale to apply
     * @param rotation the rotation to apply in degrees
     * @return the scaled and rotated image
     */
    private static BufferedImage scaleAndRotate(BufferedImage img, double scale, double rotation) {
        boolean dontRotate =
                img == WebbImages.MAIN_MENU_UFO ||
                img == WebbImages.MAIN_MENU_UFO2
                ;

        img = WebbImages.scale(img, scale);

        if(!dontRotate) {
            img = WebbImages.rotate(img, rotation);
        }

        return img;
    }

    /**
     * Updates the position of this image given the speed and direction
     */
    private void update() {
        position.x += direction.x * speed;
        position.y += direction.y * speed;
    }

    /**
     * Draws this image to the screen
     * @param g the graphics object to draw to
     */
    public void draw(Graphics g) {
        update();
        g.drawImage(bi, position.getXAsInt(), position.getYAsInt(), null);
    }

    /**
     * @return true if this image is off the screen
     */
    public boolean isOffScreen() {
        return position.x < -bi.getWidth() || position.x > screenWidth || position.y < -bi.getHeight() || position.y > screenHeight;
    }

    /**
     * Set the image to still be spawning. This is so the image doesn't instaly get removed when it spawns off the screen
     * @param stillSpawning true if this image is still spawning
     */
    public void setStillSpawning(boolean stillSpawning) {
        isStillSpawning = stillSpawning;
    }

    /**
     * @return true if this image is still spawning
     */
    public boolean isStillSpawning() {
        return isStillSpawning;
    }

    @Override
    public String toString() {
        return "ImageAndDirection{" +
                ", position=" + position +
                ", direction=" + direction +
                ", speed=" + speed +
                ", isStillSpawning=" + isStillSpawning +
                '}';
    }
}