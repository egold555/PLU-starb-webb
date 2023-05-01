package webb.client.ui.screens.test.confetti;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.Random;
import webb.client.ui.helpers.Point;

public class Confetti {

    private static final double GRAVITY = 0.5;
    private static final double TERMINAL_VELOCITY = 5;
    private static final double DRAG = 0.075;

    private static final Random RANDOM = new Random();

    private static final ColorPair[] COLORS = new ColorPair[]{
            new ColorPair(0xFF0000, 0x8B0000), //red
            new ColorPair(0xFFA500, 0xff8c00), //orange --
            new ColorPair(0xFFFF00, 0x8B8B00), //yellow
            new ColorPair(0x00FF00, 0x006400), //green
            new ColorPair(0x40E0D0, 0x00CED1), //cyan --
            new ColorPair(0x0000FF, 0x00008B), //blue
            new ColorPair(0x800080, 0x4B0082), //purple
            new ColorPair(0xFFC0CB, 0xE75480), //pink
    };

    private final ColorPair colorPair;
    private final Point dimensions;
    private final Point position;
    private final double rotation;
    private final Point scale;
    private final Point velocity;

    private boolean isOutOfFrame = false;
    private Color currentDrawColor;

    /**
     * Create a new randomised confetti object.
     * @param screenWidth The width of the screen
     * @param screenHeight The height of the screen
     * @return A new randomised confetti object
     */
    public static Confetti createRandom(int screenWidth, int screenHeight) {
        return new Confetti(
                COLORS[(int)randomRange(0, COLORS.length)],
                new Point(randomRange(10, 20), randomRange(10, 30)),
                new Point(randomRange(0, screenWidth), 0 ),
                randomRange(0, 2 * Math.PI),
                new Point(1, 1),
                new Point(randomRange(-25, 25), randomRange(0, -50))
        );
    }

    /**
     * Constructor for a confetti object
     * @param colorPair The front and back colors of the confetti
     * @param dimensions The initial dimensions of the confetti
     * @param position The initial position of the confetti
     * @param rotation The initial rotation of the confetti
     * @param scale The initial scale of the confetti
     * @param velocity The initial velocity of the confetti
     */
    public Confetti(ColorPair colorPair, Point dimensions, Point position, double rotation, Point scale, Point velocity) {
        this.colorPair = colorPair;
        this.dimensions = dimensions;
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
        this.velocity = velocity;
        this.currentDrawColor = colorPair.getFront();
    }

    /**
     * Updates the confetti's position and velocity.
     * Should not be called every frame. Preferably every 1/60th of a second for a smooth animation.
     * @param screenWidth The width of the screen
     * @param screenHeight The height of the screen
     */
    public void update(int screenWidth, int screenHeight) {

        // Velocity
        this.velocity.x -= this.velocity.x * DRAG;
        this.velocity.y = Math.min(this.velocity.y + GRAVITY, TERMINAL_VELOCITY);
        this.velocity.x += RANDOM.nextBoolean() ? RANDOM.nextDouble() : -RANDOM.nextDouble();

        // Position
        this.position.x += this.velocity.x;
        this.position.y += this.velocity.y;

        //flag out of Y screen confetti
        if(position.y >= screenHeight) {isOutOfFrame = true;}


        //fix X positions that are out of frame
        if(this.position.x > screenWidth) {this.position.x = 0;}
        if(this.position.x < 0) {this.position.x = screenWidth;}

    }

    /**
     * Renders the confetti to the screen
     * @param g2d Graphics2D object to draw with
     */
    public void draw(Graphics2D g2d) {

        //Dimensions
        final double width = Math.abs(this.dimensions.x * this.scale.x);
        final double height = Math.abs(this.dimensions.y * this.scale.y);

        //Canvas position
        g2d.translate(this.position.x, this.position.y);
        g2d.rotate(this.rotation);

        //Spin
        this.scale.y = Math.cos(this.position.y * 0.1);

        //Color
        this.currentDrawColor = this.scale.y > 0 ? this.colorPair.getFront() : this.colorPair.getBack();

        //Draw
        g2d.setColor(currentDrawColor);
        g2d.fillRect((int) -width / 2, (int) -height / 2, (int) width, (int) height);

        //Reset
        g2d.setTransform(new AffineTransform(1, 0, 0, 1, 0, 0));

    }

    /**
     * Returns true if the confetti is out of the frame.
     * @return true if the confetti is out of the frame. Used to remove confetti from the set.
     */
    public boolean isOutOfFrame() {return isOutOfFrame;}

    /**
     * Returns a random double between min and max.
     * @param min minimum value
     * @param max maximum value
     * @return a random double between min and max.
     */
    private static double randomRange(double min, double max) {
        return min + (RANDOM.nextDouble() * (max - min));
    }

}
