package webb.client.ui.screens.test.confetti;

import java.awt.Color;

public class ColorPair {

    private final Color front;
    private final Color back;

    public ColorPair(int front, Color back) {
        this(new Color(front), back);
    }

    public ColorPair(Color front, int back) {
        this(front, new Color(back));
    }
    public ColorPair(int front, int back) {
        this(new Color(front), new Color(back));
    }

    public ColorPair(Color front, Color back) {
        this.front = front;
        this.back = back;
    }

    public Color getFront() {return front;}

    public Color getBack() {return back;}
}
