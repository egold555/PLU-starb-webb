package webb.client.ui.screens.mainmenu.background;

import java.util.Random;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * A class that holds a position, direction, speed and scale for an image.
 */

@Getter
@AllArgsConstructor
public class PositionDirectionSpeedScale {

    private static final Random RNG = new Random();

    /**
     * @return the initial position of the image
     */
    private final Point position;

    /**
     * @return the direction of the image
     */
    private final Point direction;

    /**
     * @return the speed of the image
     */
    private final double speed;

    /**
     * @return the scale of the image
     */
    private final double imgScale;

    /**
     * @return the rotation of the image in degrees
     */
    private final double imgRotation;

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

}
