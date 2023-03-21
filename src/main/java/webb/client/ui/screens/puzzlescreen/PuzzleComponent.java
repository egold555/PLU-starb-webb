package webb.client.ui.screens.puzzlescreen;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.JComponent;

public class PuzzleComponent extends JComponent {

    public PuzzleComponent() {
        super();
        this.setBackground(Color.green);
        this.setBorder(BorderFactory.createLineBorder(Color.black, 3));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.drawRect(0, 0, 100, 100);

    }
}
