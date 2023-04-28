package webb.client.ui.screens.mainmenu.background;

import java.util.Random;

public class PositionDirectionSpeedScale {

    private static final Random RNG = new Random();

    private final Point position;
    private final Point direction;
    private final double speed;
    private final double imgScale;
    private final double imgRotation;

    private PositionDirectionSpeedScale(Point position, Point direction, double speed, double imgScale, double imgRotation) {
        this.position = position;
        this.direction = direction;
        this.speed = speed;
        this.imgScale = imgScale;
        this.imgRotation = imgRotation;
    }

    public static PositionDirectionSpeedScale random(int imgWidth, int imgHeight, int screenWidth, int screenHeight) {

        boolean dirX = RNG.nextBoolean();
        boolean dirY = RNG.nextBoolean();

        // random position in a corner
        double x = dirX ? -imgWidth : screenWidth + imgWidth;
        double y = dirY ? -imgHeight : screenHeight + imgHeight;

        // random direction
        double dx = RNG.nextDouble();
        double dy = RNG.nextDouble();

        //Is it negative?
        dx = dx * (dirX ? 1 : -1);
        dy = dy * (dirY ? 1 : -1);

        // random speed
        double speed = RNG.nextDouble() * 3;
        speed += 0.5;

        // random scale
        double imgScale = RNG.nextDouble() * 0.5 + 0.5;

        // random rotation
        double imgRotation = Math.toDegrees(Math.atan2(dy, dx));

        return new PositionDirectionSpeedScale(new Point(x, y), new Point(dx, dy), speed, imgScale, imgRotation);

    }

    public double getImgScale() {
        return imgScale;
    }

    public Point getDirection() {
        return direction;
    }

    public Point getPosition() {
        return position;
    }

    public double getSpeed() {
        return speed;
    }

    public double getImgRotation() {
        return imgRotation;
    }
}
