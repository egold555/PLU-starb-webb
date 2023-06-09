package webb.client.ui.screens.puzzlescreen;

import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import webb.client.ui.components.WebbButton;
import webb.client.ui.components.WebbRoundedJPanel;
import webb.client.ui.components.WebbSimpleImage;
import webb.client.ui.constants.WebbColors;
import webb.client.ui.constants.WebbFonts;
import webb.client.ui.constants.WebbImages;
import webb.client.ui.helpers.WebbTextUtilities;

/**
 * The side panel of the puzzle screen
 */
public class PuzzleSideScreen extends WebbRoundedJPanel {

    private final JLabel starLabel;
    private final JLabel starsRemainingNumber;
    private final JLabel timeLabel;
    private final JLabel puzzleNumber;
    private final  JLabel completedByLabel;

    public PuzzleSideScreen(PuzzleScreen puzzleScreen) {
        this.setBackground(WebbColors.c90);
        SpringLayout layout = new SpringLayout();

        this.setPreferredSize( new Dimension(230, 0) );

        //----------------- Cut between panels -----------------
        JPanel cutPanel = new JPanel();
        cutPanel.setBackground(WebbColors.c90);
        cutPanel.setPreferredSize(new Dimension(0, 10));
        layout.putConstraint(SpringLayout.NORTH, cutPanel, 100, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, cutPanel, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, cutPanel, -5, SpringLayout.EAST, this);
        this.add(cutPanel);

        //----------------- Inner panel -----------------
        JPanel innerPanel = new WebbRoundedJPanel();
        SpringLayout innerLayout = new SpringLayout();
        innerPanel.setBackground(WebbColors.D9);

        layout.putConstraint(SpringLayout.NORTH, innerPanel, 10, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.EAST, innerPanel, -10, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.WEST, innerPanel, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.SOUTH, innerPanel, -10, SpringLayout.SOUTH, this);
        this.add(innerPanel);

        //----------------- Upper Panel -----------------

        starLabel = new JLabel("?");
        starLabel.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_32);
        starLabel.setForeground(WebbColors.TEXT_COLOR_WHITE);
        innerLayout.putConstraint(SpringLayout.NORTH, starLabel, 25, SpringLayout.NORTH, innerPanel);
        innerLayout.putConstraint(SpringLayout.WEST, starLabel, 31, SpringLayout.WEST, innerPanel);
        innerPanel.add(starLabel);

        WebbSimpleImage starImage = new WebbSimpleImage(WebbImages.PLAY_PUZZLE_STAR, 51, 51);
        innerLayout.putConstraint(SpringLayout.NORTH, starImage, 15, SpringLayout.NORTH, innerPanel);
        innerLayout.putConstraint(SpringLayout.WEST, starImage, 15, SpringLayout.WEST, innerPanel);
        innerPanel.add(starImage);

        puzzleNumber = new JLabel("Puzzle ?");
        puzzleNumber.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_32);
        puzzleNumber.setForeground(WebbColors.TEXT_COLOR_BLACK);
        innerLayout.putConstraint(SpringLayout.NORTH, puzzleNumber, 25, SpringLayout.NORTH, innerPanel);
        innerLayout.putConstraint(SpringLayout.WEST, puzzleNumber, 30, SpringLayout.EAST, starLabel);
        innerPanel.add(puzzleNumber);

        //----------------- Lower Panel -----------------
        timeLabel = new JLabel("00:00");
        timeLabel.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_48);
        timeLabel.setForeground(WebbColors.TEXT_COLOR_BLACK);
        innerLayout.putConstraint(SpringLayout.NORTH, timeLabel, 120, SpringLayout.NORTH, innerPanel);
        innerLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, timeLabel, 0, SpringLayout.HORIZONTAL_CENTER, innerPanel);
        innerPanel.add(timeLabel);

        // Buttons
        WebbButton validateButton = new WebbButton(WebbImages.PLAY_PUZZLE_VALIDATE_BUTTON, 42, 42, (self, rightClicked) -> {
            System.out.println("Validate button clicked");
            puzzleScreen.onPuzzleComplete();
        });
        innerLayout.putConstraint(SpringLayout.NORTH, validateButton, 20, SpringLayout.SOUTH, timeLabel);
        innerLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, validateButton, -50, SpringLayout.HORIZONTAL_CENTER, innerPanel);
        innerPanel.add(validateButton);

        WebbButton highlightMovesButton = new WebbButton(WebbImages.PLAY_PUZZLE_HINT_BUTTON, 42, 42, (self, rightClicked) -> {
            System.out.println("Hint button clicked");
            puzzleScreen.getPuzzleComponent().setHintVisible(true);
        });
        highlightMovesButton.setClickReleaseListener((self, rightClicked) -> {
            System.out.println("Hint button released");
            puzzleScreen.getPuzzleComponent().setHintVisible(false);
        });
        innerLayout.putConstraint(SpringLayout.NORTH, highlightMovesButton, 20, SpringLayout.SOUTH, timeLabel);
        innerLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, highlightMovesButton, 0, SpringLayout.HORIZONTAL_CENTER, innerPanel);
        innerPanel.add(highlightMovesButton);

        WebbButton resetButton = new WebbButton(WebbImages.PLAY_PUZZLE_RESET_BUTTON, 42, 42, (self, rightClicked) -> {
            System.out.println("Reset button clicked");
            puzzleScreen.reset();
        });
        innerLayout.putConstraint(SpringLayout.NORTH, resetButton, 20, SpringLayout.SOUTH, timeLabel);
        innerLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, resetButton, 50, SpringLayout.HORIZONTAL_CENTER, innerPanel);
        innerPanel.add(resetButton);

        // Star background
        WebbSimpleImage starBackground = new WebbSimpleImage(WebbImages.PLAY_PUZZLE_STAR_BACKGROUND, 198, 223);
        innerLayout.putConstraint(SpringLayout.VERTICAL_CENTER, starBackground, 0, SpringLayout.VERTICAL_CENTER, innerPanel);
        innerLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, starBackground, 0, SpringLayout.HORIZONTAL_CENTER, innerPanel);


        JLabel starsRemainingLabel = new JLabel("Stars Remaining");
        starsRemainingLabel.setFont(WebbFonts.BALSAMIQ_SANS_BOLD_24);
        starsRemainingLabel.setForeground(WebbColors.TEXT_COLOR_BLACK);
        innerLayout.putConstraint(SpringLayout.VERTICAL_CENTER, starsRemainingLabel, 0, SpringLayout.VERTICAL_CENTER, starBackground);
        innerLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, starsRemainingLabel, 0, SpringLayout.HORIZONTAL_CENTER, innerPanel);
        innerPanel.add(starsRemainingLabel);

        starsRemainingNumber = new JLabel("? / ?");
        starsRemainingNumber.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_24);
        starsRemainingNumber.setForeground(WebbColors.TEXT_COLOR_BLACK);
        innerLayout.putConstraint(SpringLayout.VERTICAL_CENTER, starsRemainingNumber, 40, SpringLayout.VERTICAL_CENTER, starBackground);
        innerLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, starsRemainingNumber, 0, SpringLayout.HORIZONTAL_CENTER, innerPanel);
        innerPanel.add(starsRemainingNumber);

        completedByLabel = new JLabel("Completed by ? players");
        completedByLabel.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_13);
        completedByLabel.setForeground(WebbColors.c7F);
        innerLayout.putConstraint(SpringLayout.SOUTH, completedByLabel, -10, SpringLayout.SOUTH, innerPanel);
        innerLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, completedByLabel, 0, SpringLayout.HORIZONTAL_CENTER, innerPanel);
        innerPanel.add(completedByLabel);

        innerPanel.add(starBackground); //We need to add this after the text so the text is infront of it. Java is backwards.


        // Exit button

        //Kind of a dumb hack, but drawing spaces nudges the text over
        WebbButton exitButton = new WebbButton("    E x i t", WebbImages.PLAY_PUZZLE_EXIT_BUTTON, 93, 24, (self, rightClicked) -> {
            System.out.println("Exit button pressed");
            puzzleScreen.exitPuzzle();
        });
        exitButton.setDrawBackground(false);
        exitButton.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_24);
        exitButton.setForeground(WebbColors.TEXT_COLOR_BLACK);
        innerLayout.putConstraint(SpringLayout.SOUTH, exitButton, -50, SpringLayout.SOUTH, innerPanel);
        innerLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, exitButton, 0, SpringLayout.HORIZONTAL_CENTER, innerPanel);
        innerPanel.add(exitButton);


        innerPanel.setLayout(innerLayout);
        this.setLayout(layout);
    }

    /**
     * Sets the puzzle number
     * @param puzzleNumber The puzzle number
     * @param stars The number of stars the puzzle is
     */
    public void setPuzzleNumber(int puzzleNumber, int stars) {
        this.puzzleNumber.setText("Puzzle " + puzzleNumber);
        this.starLabel.setText("" + stars);
    }

    /**
     * Sets the number of stars remaining
     * @param starsRemaining The number of stars remaining
     * @param starsMax The maximum number of stars
     */
    public void setStarsRemaining(int starsRemaining, int starsMax) {
        this.starsRemainingNumber.setText(starsRemaining + " / " + starsMax);
    }

    /**
     * Sets the time remaining
     * @param timeRemaining The time remaining in milliseconds
     */
    public void setTimeRemaining(long timeRemaining) {
        this.timeLabel.setText(WebbTextUtilities.formatMinSec(timeRemaining));
    }

    /**
     * Sets the number of players who have completed the puzzle
     * @param playersCompleted The number of players who have completed the puzzle
     */
    public void setPlayersCompleted(int playersCompleted) {
        this.completedByLabel.setText("Completed by " + playersCompleted + " players");
    }
}
