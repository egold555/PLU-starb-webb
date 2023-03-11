package webb.client.uitest.screens;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import webb.client.uitest.components.WebbButton;
import webb.client.uitest.constants.WebbColors;
import webb.client.uitest.constants.WebbFonts;

public class MainMenuScreen extends JPanel {

    public MainMenuScreen() {

        Container contentPane = this;
        SpringLayout layout = new SpringLayout();

        this.setBackground(WebbColors.D9);




        JLabel mainMenuText1 = new JLabel("Star Battle");
        mainMenuText1.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_72);
        mainMenuText1.setForeground(WebbColors.TEXT_COLOR_BLACK);
        layout.putConstraint(SpringLayout.NORTH, mainMenuText1, 50, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, mainMenuText1, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
        this.add(mainMenuText1);

        JLabel mainMenuText2 = new JLabel("Odyssey");
        mainMenuText2.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_72);
        mainMenuText2.setForeground(WebbColors.TEXT_COLOR_BLACK);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, mainMenuText2, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
        layout.putConstraint(SpringLayout.NORTH, mainMenuText2, 0, SpringLayout.SOUTH, mainMenuText1);
        this.add(mainMenuText2);

        WebbButton playButton = new WebbButton("Play");
        playButton.setPreferredSize(new Dimension(142, 43));
        playButton.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_32);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, playButton, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
        layout.putConstraint(SpringLayout.NORTH, playButton, 50, SpringLayout.SOUTH, mainMenuText2);

        playButton.addActionListener((e) -> {
            System.out.println("Play button pressed");
        });

        this.add(playButton);

        WebbButton creditsButton = new WebbButton("Credits");
        creditsButton.setPreferredSize(new Dimension(142, 43));
        creditsButton.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_32);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, creditsButton, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
        layout.putConstraint(SpringLayout.NORTH, creditsButton, 10, SpringLayout.SOUTH, playButton);

        creditsButton.addActionListener((e) -> {
            System.out.println("Credits button pressed");
        });

        this.add(creditsButton);

        WebbButton exitButton = new WebbButton("Exit");
        exitButton.setPreferredSize(new Dimension(142, 43));
        exitButton.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_32);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, exitButton, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
        layout.putConstraint(SpringLayout.NORTH, exitButton, 10, SpringLayout.SOUTH, creditsButton);

        exitButton.addActionListener((e) -> {
            System.out.println("Exit button pressed");
        });

        this.add(exitButton);

        this.setLayout(layout);
    }

}
