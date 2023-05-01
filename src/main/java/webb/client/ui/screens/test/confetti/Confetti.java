package webb.client.ui.screens.test.confetti;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.Random;
import webb.client.ui.helpers.Point;

public class Confetti {

    static final double gravity = 0.5;
    static final double terminalVelocity = 5;
    static final double drag = 0.075;

    private static final Random random = new Random();

    private static final ColorPair[] colors = new ColorPair[]{
            new ColorPair(0xFF0000, 0x8B0000), //red
            new ColorPair(0x00FF00, 0x006400), //green
            new ColorPair(0x0000FF, 0x00008B), //blue
            new ColorPair(0xFFFF00, 0x8B8B00), //yellow
            new ColorPair(0xFFA500, 0xff8c00), //orange
            new ColorPair(0xFFC0CB, 0xE75480), //pink
            new ColorPair(0x800080, 0x4B0082), //purple
            new ColorPair(0x40E0D0, 0x00CED1), //cyan
    };

    private final ColorPair colorPair;
    private final Point dimensions;
    private final Point position;
    private final double rotation;
    private final Point scale;
    private final Point velocity;

    private boolean isOutOfFrame = false;
    private Color currentDrawColor;

    public static Confetti createRandom(int screenWidth, int screenHeight) {
        return new Confetti(
                colors[(int)randomRange(0, colors.length)],
                new Point(randomRange(10, 20), randomRange(10, 30)),
                new Point(randomRange(0, screenWidth), 0 ),
                randomRange(0, 2 * Math.PI),
                new Point(1, 1),
                new Point(randomRange(-25, 25), randomRange(0, -50))
        );
    }

    public Confetti(ColorPair colorPair, Point dimensions, Point position, double rotation, Point scale, Point velocity) {
        this.colorPair = colorPair;
        this.dimensions = dimensions;
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
        this.velocity = velocity;
        this.currentDrawColor = colorPair.getFront();
    }

    public void update(int screenWidth, int screenHeight) {
        // Velocity
        this.velocity.x -= this.velocity.x * drag;
        this.velocity.y = Math.min(this.velocity.y + gravity, terminalVelocity);
        this.velocity.x += random.nextBoolean() ? random.nextDouble() : -random.nextDouble();

        // Position
        this.position.x += this.velocity.x;
        this.position.y += this.velocity.y;

        //flag out of Y screen confetti
        if(position.y >= screenHeight) {isOutOfFrame = true;}


        //fix X positions that are out of frame
        if(this.position.x > screenWidth) {this.position.x = 0;}
        if(this.position.x < 0) {this.position.x = screenWidth;}

//        //Spin
//        this.scale.y = Math.cos(this.position.y * 0.1);
//        this.currentDrawColor = this.scale.y > 0 ? this.colorPair.getFront() : this.colorPair.getBack();
    }

    public void draw(Graphics2D g2d) {
        final double width = makePositive(this.dimensions.x * this.scale.x);
        final double height = makePositive(this.dimensions.y * this.scale.y);

        //Canvas position
        g2d.translate(this.position.x, this.position.y);
        g2d.rotate(this.rotation);

        //Spin
        this.scale.y = Math.cos(this.position.y * 0.1);
        System.out.println(this.scale.y);

        this.currentDrawColor = this.scale.y > 0 ? this.colorPair.getFront() : this.colorPair.getBack();

        //Draw
        g2d.setColor(currentDrawColor);
        g2d.fillRect((int) -width / 2, (int) -height / 2, (int) width, (int) height);

        //Reset
        g2d.setTransform(new AffineTransform(1, 0, 0, 1, 0, 0));

    }

    public boolean isOutOfFrame() {return isOutOfFrame;}

    private static double randomRange(double min, double max) {
        return min + (random.nextDouble() * (max - min));
    }

    private double makePositive(double num) {

        if(num < 0) {
            num = num * -1;
        }

        return num;
    }

}
