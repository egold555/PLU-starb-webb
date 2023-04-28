package webb.client.ui.screens.mainmenu.background;

public class Point {
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
