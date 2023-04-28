package webb.client.ui.screens.mainmenu.background;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class ImageAndDirection {

    private final BufferedImage bi;
    private final Point position;
    private final Point direction;
    private final double speed;
    private final int screenWidth;
    private final int screenHeight;

    private boolean isStillSpawning = true;

    public ImageAndDirection(BufferedImage bi, Point startingPoint, Point direction, double speed, int screenWidth, int screenHeight) {
        this.bi = bi;
        this.position = startingPoint;
        this.direction = direction;
        this.speed = speed;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    private void update() {
        position.x += direction.x * speed;
        position.y += direction.y * speed;
    }

    public void draw(Graphics g) {
        update();
        g.drawImage(bi, position.getXAsInt(), position.getYAsInt(), null);
    }

    public boolean isOffScreen() {

        //Not sure if this is correct
        return position.x < -bi.getWidth() || position.x > screenWidth || position.y < -bi.getHeight() || position.y > screenHeight;
    }

    public void setStillSpawning(boolean stillSpawning) {
        isStillSpawning = stillSpawning;
    }

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