package webb.client.ui.screens.puzzlescreen.confetti;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JComponent;

public class BackgroundConfetti extends JComponent {

    private final Set<Confetti> confetti = new HashSet<>();
    private boolean constantConfetti = false;

    /**
     * Add some confetti to the background
     * @param amount the amount of confetti pieces to add
     */
    public void addSomeConfetti(int amount) {
        final int screenWidth = getWidth();
        final int screenHeight = getHeight();

        for(int i = 0; i < amount; i++) {
            confetti.add(Confetti.createRandom(screenWidth, screenHeight));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Update and draw the confetti
        Graphics2D g2d = (Graphics2D) g;
        updateAndDrawConfetti(g2d);

        //Continue the loop
        repaint();


    }

    // The last time the confetti was updated
    private long lastTime = System.currentTimeMillis();

    /**
     * Updates and draws the confetti.
     * @param g2d the graphics object to draw with.
     */
    private void updateAndDrawConfetti(Graphics2D g2d) {

        final int screenWidth = getWidth();
        final int screenHeight = getHeight();

        Set<Confetti> toBeRemoved = new HashSet<>();

        // Update the confetti every 1/60th of a second
        if(System.currentTimeMillis() - lastTime > 1/60f * 1000) {

            for(Confetti c : confetti) {
                c.update(screenWidth, screenHeight);
            }

            // Update the last time the confetti was updated
            lastTime = System.currentTimeMillis();
        }

        // Draw the confetti every frame
        for(Confetti c : confetti) {

            c.draw(g2d);

            // Check if the confetti is out of frame
            if(c.isOutOfFrame()) {
                toBeRemoved.add(c);
            }
        }

        // Remove the confetti that is out of frame
        confetti.removeAll(toBeRemoved);

        if(constantConfetti && confetti.size() < 50) {
            addSomeConfetti(50);
        }

    }

    public void enableConstantConfetti(boolean enable) {
        this.constantConfetti = enable;
    }


}
