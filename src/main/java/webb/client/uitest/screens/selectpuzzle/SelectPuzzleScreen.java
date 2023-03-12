package webb.client.uitest.screens.selectpuzzle;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.plaf.basic.BasicBorders;
import webb.client.uitest.components.WebbButton;
import webb.client.uitest.constants.WebbColors;
import webb.client.uitest.constants.WebbFonts;
import webb.client.uitest.constants.WebbImages;

public class SelectPuzzleScreen extends JPanel {

        public SelectPuzzleScreen() {

                Container contentPane = this;
                SpringLayout layout = new SpringLayout();

                this.setBackground(WebbColors.D9);

                JLabel titleText = new JLabel("Select Puzzle");
                titleText.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_64);
                titleText.setForeground(WebbColors.TEXT_COLOR_BLACK);
                layout.putConstraint(SpringLayout.NORTH, titleText, 20, SpringLayout.NORTH, contentPane);
                layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, titleText, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
                this.add(titleText);

                JPanel puzzlePanel = new JPanel();
                puzzlePanel.setOpaque(false);
                //puzzlePanel.setBackground(WebbColors.B7);
                //puzzlePanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                layout.putConstraint(SpringLayout.NORTH, puzzlePanel, 20, SpringLayout.SOUTH, titleText);
                layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, puzzlePanel, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
                layout.putConstraint(SpringLayout.EAST, puzzlePanel, -20, SpringLayout.EAST, contentPane);
                layout.putConstraint(SpringLayout.WEST, puzzlePanel, 20, SpringLayout.WEST, contentPane);
                layout.putConstraint(SpringLayout.SOUTH, puzzlePanel, -70, SpringLayout.SOUTH, contentPane);
                //puzzlePanel.setLayout(new GridLayout(10, 10));

                for (int i = 0; i < 9; i++) {
                        PuzzleButton button = new PuzzleButton(i + 1, ThreadLocalRandom.current().nextInt(1,4));
                        button.setCompleted(ThreadLocalRandom.current().nextBoolean());
                        puzzlePanel.add(button);
                }

                this.add(puzzlePanel);


                JPanel bottomBar = new JPanel();
                SpringLayout bottomBarLayout = new SpringLayout();
                bottomBar.setBackground(WebbColors.D9);
                layout.putConstraint(SpringLayout.NORTH, bottomBar, 20, SpringLayout.SOUTH, puzzlePanel);
                layout.putConstraint(SpringLayout.EAST, bottomBar, -20, SpringLayout.EAST, contentPane);
                layout.putConstraint(SpringLayout.WEST, bottomBar, 20, SpringLayout.WEST, contentPane);
                layout.putConstraint(SpringLayout.SOUTH, bottomBar, 0, SpringLayout.SOUTH, contentPane);



                WebbButton puzzleBack = new WebbButton(WebbImages.ARROW_BACK, 42, 42);
                bottomBarLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, puzzleBack, -200, SpringLayout.HORIZONTAL_CENTER, bottomBar);
                bottomBarLayout.putConstraint(SpringLayout.VERTICAL_CENTER, puzzleBack, 0, SpringLayout.VERTICAL_CENTER, bottomBar);

                puzzleBack.addActionListener(e -> {
                        System.out.println("Puzzle back button pressed");
                });
                bottomBar.add(puzzleBack);


                WebbButton puzzleForward = new WebbButton(WebbImages.ARROW_FORWARD, 42, 42);
                bottomBarLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, puzzleForward, 200, SpringLayout.HORIZONTAL_CENTER, bottomBar);
                bottomBarLayout.putConstraint(SpringLayout.VERTICAL_CENTER, puzzleForward, 0, SpringLayout.VERTICAL_CENTER, bottomBar);

                puzzleForward.addActionListener(e -> {
                        System.out.println("Puzzle forward button pressed");
                });
                bottomBar.add(puzzleForward);


                WebbButton trophy = new WebbButton(WebbImages.BUTTON_TROPHY, 42, 42);
                bottomBarLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, trophy, -32, SpringLayout.HORIZONTAL_CENTER, bottomBar);
                bottomBarLayout.putConstraint(SpringLayout.VERTICAL_CENTER, trophy, 0, SpringLayout.VERTICAL_CENTER, bottomBar);

                trophy.addActionListener(e -> {
                        System.out.println("Trophy button pressed");
                });
                bottomBar.add(trophy);


                WebbButton stats = new WebbButton(WebbImages.BUTTON_STATS, 42, 42);
                bottomBarLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, stats, 32, SpringLayout.HORIZONTAL_CENTER, bottomBar);
                bottomBarLayout.putConstraint(SpringLayout.VERTICAL_CENTER, stats, 0, SpringLayout.VERTICAL_CENTER, bottomBar);

                stats.addActionListener(e -> {
                        System.out.println("Stats button pressed");
                });
                bottomBar.add(stats);


                bottomBar.setLayout(bottomBarLayout);
                this.add(bottomBar);



                WebbButton backButton = new WebbButton(WebbImages.ARROW_PAGE_BACK, 42, 42);
                layout.putConstraint(SpringLayout.NORTH, backButton, 0, SpringLayout.NORTH, contentPane);
                layout.putConstraint(SpringLayout.WEST, backButton, 10, SpringLayout.WEST, contentPane);
                backButton.addActionListener(e -> {
                        System.out.println("Back button pressed");
                });
                this.add(backButton);


                this.setLayout(layout);

        }


}
