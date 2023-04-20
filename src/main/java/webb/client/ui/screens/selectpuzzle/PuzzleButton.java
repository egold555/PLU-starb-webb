package webb.client.ui.screens.selectpuzzle;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import webb.client.ui.components.WebbButton;
import webb.client.ui.components.WebbRoundedJPanel;
import webb.client.ui.constants.WebbColors;
import webb.client.ui.constants.WebbImages;

/**
 * A button that represents a puzzle.
 * Used in the puzzle selection screen.
 */
public class PuzzleButton extends JPanel {

    private boolean completed = false;
    private int stars = 0;

    public PuzzleButton(int id, int stars) {
        this.stars = stars;
        this.setOpaque(false);

        SpringLayout innerLayout = new SpringLayout();

        WebbRoundedJPanel panel = new WebbRoundedJPanel(10, 10);


        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(140, 140));

        panel.setBackground(WebbColors.B7);

        WebbButton button = new WebbButton("" + id, (self, rightClicked) -> {
            System.out.println("Puzzle button " + id + " clicked!");
        });
        button.setDrawBackground(false);

        innerLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, panel, 0, SpringLayout.HORIZONTAL_CENTER, this);
        innerLayout.putConstraint(SpringLayout.VERTICAL_CENTER, panel, 0, SpringLayout.VERTICAL_CENTER, this);

        panel.add(button, BorderLayout.CENTER);

        this.setPreferredSize(new Dimension(180, 180));
        this.add(panel);


        this.setLayout(innerLayout);
    }

    /**
     * Sets whether or not this puzzle is completed.
     * @param completed True if completed, false otherwise.
     */
    public void setCompleted(boolean completed) {this.completed = completed;}

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        if(completed) {
            g2.drawImage(WebbImages.PUZZLE_SELECTION_LEVEL_COMPLETE_EMBLEM, 120, 0, 50, 50, null);
        }

        g2.setColor(WebbColors.c91);
        int width = 120;

        g2.fillRoundRect(this.getWidth()/2 - width/2, this.getHeight() - 40, width, 30, 30, 30);

        //VERY VERY bad way to do this, but right now my brain hurts
        //and java swing is murdering me.
        //TODO: Replace with a JPanel with a BorderLayout
        if(stars == 1) {
            g2.drawImage(WebbImages.PUZZLE_SELECTION_STAR, this.getWidth()/2 - 15, this.getHeight() - 40, 30, 30, null);
        }
        else if(stars == 2) {
            g2.drawImage(WebbImages.PUZZLE_SELECTION_STAR, this.getWidth()/2 - 30, this.getHeight() - 40, 30, 30, null);
            g2.drawImage(WebbImages.PUZZLE_SELECTION_STAR, this.getWidth()/2 + 5, this.getHeight() - 40, 30, 30, null);
        }
        else if(stars == 3) {
            g2.drawImage(WebbImages.PUZZLE_SELECTION_STAR, this.getWidth()/2 - 45, this.getHeight() - 40, 30, 30, null);
            g2.drawImage(WebbImages.PUZZLE_SELECTION_STAR, this.getWidth()/2 - 10, this.getHeight() - 40, 30, 30, null);
            g2.drawImage(WebbImages.PUZZLE_SELECTION_STAR, this.getWidth()/2 + 25, this.getHeight() - 40, 30, 30, null);
        }

    }

}