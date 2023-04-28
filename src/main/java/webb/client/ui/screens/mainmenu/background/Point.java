package webb.client.ui.screens.mainmenu.background;

/**
 * Represents a point in 2D space, using doubles.
 */
public class Point {

    // These should be private, but they are public for the sake of simplicity.
    public double x;
    public double y;

    /**
     * Creates a new point with the given x and y coordinates.
     * @param x
     * @param y
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x coordinate as an int.
     * @return the x coordinate as an int.
     */
    public int getXAsInt() {
        return (int) x;
    }

    /**
     * Returns the y coordinate as an int.
     * @return the y coordinate as an int.
     */
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
