package webb.client.ui.screens.puzzlescreen;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import webb.client.ui.components.WebbRoundedJPanel;
import webb.client.ui.constants.WebbColors;
import webb.client.ui.constants.WebbFonts;

public class PuzzleSideScreen extends WebbRoundedJPanel {

    public PuzzleSideScreen() {
        this.setBackground(WebbColors.c90);
        SpringLayout layout = new SpringLayout();

        //this.setPreferredSize( new Dimension(200, 500) );


        JPanel titlePanel = new WebbRoundedJPanel();
        titlePanel.setBackground(WebbColors.D9);
        titlePanel.setLayout(new BorderLayout());

        JLabel title = new JLabel("Puzzle");
        title.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        title.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_32);
        title.setForeground(WebbColors.TEXT_COLOR_BLACK);
        titlePanel.add(title, BorderLayout.CENTER);

       // layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, titlePanel, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, titlePanel, 0, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WIDTH, titlePanel, 0, SpringLayout.WIDTH, this);
        layout.putConstraint(SpringLayout.HEIGHT, titlePanel, 0, SpringLayout.HEIGHT, this);
        this.add(titlePanel);

//        JPanel gridPanel = new JPanel(new GridLayout(5, 1, 5, 5));
//        gridPanel.add(new JButton("Button 1"));
//        gridPanel.add( new JButton("Button 2"));
//
//        gridPanel.add( new JLabel("Label:"));
//        JTextField textField = new JTextField(15);
//        gridPanel.add(textField);
//
//        gridPanel.add(new JButton("Button 3"));
//
//        // Wrap the GridLayout panel in a JPanel so that it doesn't grow to fill
//        JPanel centerPanel = new JPanel();
//        centerPanel.add(gridPanel);
//
//        JPanel southPanel = new JPanel(new BorderLayout());
//        JTextArea textArea = new JTextArea(14, 15);
//        southPanel.add( new JScrollPane(textArea), BorderLayout.PAGE_END );
//        southPanel.add( new JLabel("Label"), BorderLayout.PAGE_START );
//
//        this.add( centerPanel, BorderLayout.CENTER);
//        this.add( southPanel, BorderLayout.PAGE_END);

        this.setLayout(layout);
    }
}
