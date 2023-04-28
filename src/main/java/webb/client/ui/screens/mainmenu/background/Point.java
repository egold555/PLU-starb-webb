package webb.client.ui.screens.mainmenu.background;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Represents a point in 2D space, using doubles.
 */
@ToString
@AllArgsConstructor
@EqualsAndHashCode
public class Point {

    // These should be private, but they are public for the sake of simplicity.

    /**
     * The x coordinate of this point.
     */
    public double x;

    /**
     * The y coordinate of this point.
     */
    public double y;

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

}
