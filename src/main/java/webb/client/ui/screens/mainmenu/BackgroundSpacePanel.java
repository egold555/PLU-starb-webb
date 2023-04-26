package webb.client.ui.screens.mainmenu;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JComponent;
import webb.client.ui.constants.WebbImages;

public class BackgroundSpacePanel extends JComponent {

    private Set<ImageAndDirection> images = new HashSet<>();

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Set<ImageAndDirection> tmp = new HashSet<>(images);
        for(ImageAndDirection image : images) {
            image.draw(g);

            if(image.isOffScreen()) {
                tmp.add(image);
            }
        }

        images.removeAll(tmp);

        if(ThreadLocalRandom.current().nextInt(0, 100) == 1) {
            images.add(new ImageAndDirection(WebbImages.MAIN_MENU_PLANET, 0, 0, true, true, 1, this));
        }

        //Draw all the space things

//        g.drawImage(WebbImages.MAIN_MENU_PLANET, imgX, imgY, this);
//
//        //bounce the image when it hits the edge of the screen
//        if (imgX > this.getWidth() - WebbImages.MAIN_MENU_PLANET.getWidth()) {
//            imgX = this.getWidth() - WebbImages.MAIN_MENU_PLANET.getWidth();
//        }
//        else if (imgX < 0) {
//            imgX = 0;
//        }
//
//        if (imgY > this.getHeight() - WebbImages.MAIN_MENU_PLANET.getHeight()) {
//            imgY = this.getHeight() - WebbImages.MAIN_MENU_PLANET.getHeight();
//        } else if (imgY < 0) {
//            imgY = 0;
//        }
//
//        if(goingRight) {
//            imgX++;
//        }
//        else {
//            imgX--;
//        }
//
//        if(goingDown) {
//            imgY++;
//        }
//        else {
//            imgY--;
//        }
//
//        if(imgX > this.getWidth() - WebbImages.MAIN_MENU_PLANET.getWidth()) {
//            goingRight = false;
//        }
//        else if(imgX < 0) {
//            goingRight = true;
//        }
//
//        if(imgY > this.getHeight() - WebbImages.MAIN_MENU_PLANET.getHeight()) {
//            goingDown = false;
//        }
//        else if(imgY < 0) {
//            goingDown = true;
//        }


        repaint();

    }

    static class ImageAndDirection {
        private int x;
        private int y;
        private final boolean goingRight;
        private final boolean goingDown;
        private final BufferedImage bi;
        private final int speed;

        private final int top = 0;
        private final int left = 0;
        private final int right;
        private final int bottom;

        private final int width;
        private final int height;

        public ImageAndDirection(BufferedImage bi, int x, int y, boolean goingRight, boolean goingDown, int speed, JComponent parent) {
            this.bi = bi;
            this.x = x;
            this.y = y;
            this.goingRight = goingRight;
            this.goingDown = goingDown;
            this.speed = speed;

            this.right = parent.getWidth();
            this.bottom = parent.getHeight();

            this.width = bi.getWidth();
            this.height = bi.getHeight();
        }

        private void update() {
            if(goingRight) {
                x += speed;
            }
            else {
                x -= speed;
            }

            if(goingDown) {
                y += speed;
            }
            else {
                y -= speed;
            }
        }

        public void draw(Graphics g) {
            update();
            g.drawImage(bi, x, y, null);
        }

        public boolean isOffScreen() {

            System.out.println("x: " + x + " y: " + y + " width: " + width + " height: " + height + " left: " + left + " right: " + right + " top: " + top + " bottom: " + bottom);

            if(x - width < left) {
                return true;
            }
            else if(x + width > right) {
                return true;
            }

            if(y - height < top) {
                return true;
            }
            else if(y + height > bottom) {
                return true;
            }

            return false;



        }

    }

}
