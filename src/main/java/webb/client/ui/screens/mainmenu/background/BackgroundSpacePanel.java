package webb.client.ui.screens.mainmenu.background;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import javax.swing.JComponent;
import webb.client.ui.constants.WebbImages;

public class BackgroundSpacePanel extends JComponent {

    private final Set<ImageAndDirection> images = new HashSet<>();

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

        repaint();

    }

}
