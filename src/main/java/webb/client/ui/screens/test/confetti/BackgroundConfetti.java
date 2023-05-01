package webb.client.ui.screens.test.confetti;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JComponent;

public class BackgroundConfetti extends JComponent {

    private final Set<Confetti> confetti = new HashSet<>();

    public void test() {
        for(int i = 0; i < 300; i++) {
            confetti.add(Confetti.createRandom(getWidth(), getHeight()));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);


        Graphics2D g2d = (Graphics2D) g;
        updateAndDrawConfetti(g2d);

        //Continue the loop
        repaint();


    }

    long lastTime = System.currentTimeMillis();

    private void updateAndDrawConfetti(Graphics2D g2d) {

        final int width = getWidth();
        final int height = getHeight();

        Set<Confetti> toBeRemoved = new HashSet<>();

        if(System.currentTimeMillis() - lastTime > 1/60f * 1000) {

            for(Confetti c : confetti) {
                c.update(width, height);
            }

            lastTime = System.currentTimeMillis();
        }

        for(Confetti c : confetti) {

            c.draw(g2d);

            if(c.isOutOfFrame()) {
                toBeRemoved.add(c);
            }
        }

        confetti.removeAll(toBeRemoved);

    }



}
