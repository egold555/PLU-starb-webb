package webb.client.ui.screens.mainmenu;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JComponent;
import webb.client.ui.constants.WebbImages;

public class BackgroundSpacePanel extends JComponent {

    private Set<ImageAndDirection> images = new HashSet<>();

    static final Random RNG = new Random();

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

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

        if(RNG.nextInt(0, 200) == 0) {

            int width = getWidth();
            int height = getHeight();


            BufferedImage img = switch (RNG.nextInt(0, 4)) {
                case 0 -> WebbImages.MAIN_MENU_PLANET;
                case 1 -> WebbImages.MAIN_MENU_UFO;
                case 2 -> WebbImages.MAIN_MENU_STAR;
                case 3 -> WebbImages.MAIN_MENU_ROCKET;
                default -> throw new AssertionError();
            };

            PositionDirectionSpeedScale pds = PositionDirectionSpeedScale.random(img.getWidth(), img.getHeight(), width, height);

            boolean dontRotate = img == WebbImages.MAIN_MENU_UFO;

            img = WebbImages.scale(img, pds.getImgScale());

            if(!dontRotate) {
                img = WebbImages.rotate(img, pds.getImgRotation());
            }

            images.add(new ImageAndDirection(img, pds.getPosition(), pds.getDirection(), pds.getSpeed(), width, height));
        }

        g.drawString("Images: " + images.size(), 10, 10);

        int tmpy = 20;
        for(ImageAndDirection image : images) {
            g.drawString("  - " + image, 10, tmpy);

            tmpy+=20;
        }

        repaint();

    }

    static class PositionDirectionSpeedScale {



        private final Point position;
        private final Point direction;
        private final double speed;
        private final double imgScale;
        private final double imgRotation;

        public PositionDirectionSpeedScale(Point position, Point direction, double speed, double imgScale, double imgRotation) {
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

    static class Point {
        public double x;
        public double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public int getXAsInt() {
            return (int) x;
        }

        public int getYAsInt() {
            return (int) y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    static class ImageAndDirection {

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

}
