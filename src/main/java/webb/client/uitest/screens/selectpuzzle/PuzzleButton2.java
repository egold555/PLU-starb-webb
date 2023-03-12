package webb.client.uitest.screens.selectpuzzle;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import webb.client.uitest.components.RoundedJPanel;
import webb.client.uitest.components.WebbButton;
import webb.client.uitest.constants.WebbImages;

public class PuzzleButton2 extends JPanel {

    private boolean completed = false;
    private int stars = 0;

    public PuzzleButton2(int id, int stars) {
        this.stars = stars;
        this.setOpaque(false);

        SpringLayout innerLayout = new SpringLayout();

        RoundedJPanel panel = new RoundedJPanel(10, 10);

        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(140, 140));

        panel.setBackground(Color.red);

        WebbButton button = new WebbButton("" + id);
        button.setDrawBackground(false);
        button.addActionListener(e -> System.out.println("Puzzle button " + id + " clicked!"));

        innerLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, panel, 0, SpringLayout.HORIZONTAL_CENTER, this);
        innerLayout.putConstraint(SpringLayout.VERTICAL_CENTER, panel, 0, SpringLayout.VERTICAL_CENTER, this);

        panel.add(button, BorderLayout.CENTER);

        this.setPreferredSize(new Dimension(180, 180));
        this.add(panel);

        this.setLayout(innerLayout);
    }

    public void setCompleted(boolean completed) {this.completed = completed;}

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        if(completed) {
            g2.drawImage(WebbImages.LEVEL_COMPLETE_EMBLEM, 120, 0, 50, 50, null);
        }
    }

    //    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        Graphics2D g2 = (Graphics2D) g;
//        g2.drawImage(WebbImages.LEVEL_COMPLETE_EMBLEM, 0, 0, getWidth(), getHeight(), null);
//
//        //super.paintComponent(g);
//
//    }
}
