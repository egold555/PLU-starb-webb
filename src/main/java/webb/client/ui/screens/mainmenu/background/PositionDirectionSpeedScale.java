package webb.client.ui.screens.mainmenu.background;

import java.util.Random;

/**
 * A class that holds a position, direction, speed and scale for an image.
 */
public class PositionDirectionSpeedScale {

    private static final Random RNG = new Random();

    private final Point position;
    private final Point direction;
    private final double speed;
    private final double imgScale;
    private final double imgRotation;

    /**
     * Constructor
     * @param position the initial position of the image
     * @param direction the direction the image is moving in
     * @param speed the speed the image is moving at
     * @param imgScale the scale of the image
     * @param imgRotation the rotation of the image in degrees
     */
    private PositionDirectionSpeedScale(Point position, Point direction, double speed, double imgScale, double imgRotation) {
        this.position = position;
        this.direction = direction;
        this.speed = speed;
        this.imgScale = imgScale;
        this.imgRotation = imgRotation;
    }

    /**
     * Creates a random PositionDirectionSpeedScale object
     * @param imgWidth the width of the image
     * @param imgHeight the height of the image
     * @param screenWidth the width of the screen
     * @param screenHeight the height of the screen
     * @return a random PositionDirectionSpeedScale object
     */
    public static PositionDirectionSpeedScale createRandom(int imgWidth, int imgHeight, int screenWidth, int screenHeight) {

        final boolean dirX = RNG.nextBoolean();
        final boolean dirY = RNG.nextBoolean();

        // random position in a corner
        final double x = dirX ? -imgWidth : screenWidth + imgWidth;
        final double y = dirY ? -imgHeight : screenHeight + imgHeight;

        // random direction
        double dx = RNG.nextDouble();
        double dy = RNG.nextDouble();

        //Is the direction negative?
        dx = dx * (dirX ? 1 : -1);
        dy = dy * (dirY ? 1 : -1);

        // random speed
        double speed = RNG.nextDouble() * 3;
        speed += 0.5;

        // random scale
        final double imgScale = RNG.nextDouble() * 0.5 + 0.5;

        // random rotation
        final double imgRotation = Math.toDegrees(Math.atan2(dy, dx));

        return new PositionDirectionSpeedScale(new Point(x, y), new Point(dx, dy), speed, imgScale, imgRotation);

    }

    /**
     * Returns the scale of the image
     * @return the scale of the image
     */
    public double getImgScale() {
        return imgScale;
    }

    /**
     * Returns the direction of the image
     * @return the direction of the image
     */
    public Point getDirection() {
        return direction;
    }

    /**
     * Returns the initial position of the image
     * @return the initial position of the image
     */
    public Point getPosition() {
        return position;
    }

    /**
     * Returns the speed of the image
     * @return the speed of the image
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * Returns the rotation of the image in degrees
     * @return the rotation of the image in degrees
     */
    public double getImgRotation() {
        return imgRotation;
    }
}
