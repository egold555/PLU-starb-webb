package starb.client.ui;

import javax.swing.*;
import java.awt.*;

public class ExampleSidePanel extends JPanel {

    private JTextField textField;
    private JTextArea textArea;

    public ExampleSidePanel() {
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createTitledBorder("Side Panel"));

        JPanel gridPanel = new JPanel(new GridLayout(5, 1, 5, 5));
        gridPanel.add(new JButton("Button 1"));
        gridPanel.add( new JButton("Button 2"));

        gridPanel.add( new JLabel("Label:"));
        textField = new JTextField(15);
        gridPanel.add(textField);

        gridPanel.add(new JButton("Button 3"));

        // Wrap the GridLayout panel in a JPanel so that it doesn't grow to fill
        JPanel centerPanel = new JPanel();
        centerPanel.add(gridPanel);

        JPanel southPanel = new JPanel(new BorderLayout());
        textArea = new JTextArea(14, 15);
        southPanel.add( new JScrollPane(textArea), BorderLayout.PAGE_END );
        southPanel.add( new JLabel("Label"), BorderLayout.PAGE_START );

        this.add( centerPanel, BorderLayout.CENTER);
        this.add( southPanel, BorderLayout.PAGE_END);
    }
}
