package webb.client.ui.screens.test;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.io.IOException;
import javax.swing.SpringLayout;
import webb.client.ui.components.WebbBackButton;
import webb.client.ui.components.WebbButton;
import webb.client.ui.components.WebbCheckbox;
import webb.client.ui.components.WebbInputBox;
import webb.client.ui.constants.WebbColors;
import webb.client.ui.constants.WebbFonts;
import webb.client.ui.popup.ExampleWebbPopup;
import webb.client.ui.popup.statistics.PopupStatistics;
import webb.client.ui.popup.congratulations.PopupCongratulations;
import webb.client.ui.popup.leaderboard.PopupLeaderboard;
import webb.client.ui.screens.Screen;
import webb.client.ui.screens.ScreenType;
import webb.client.ui.testing.DummyData.DummyCongratulationsData;
import webb.client.ui.testing.DummyData.DummyLeaderboardData;
import webb.client.ui.testing.DummyData.DummyStatisticsData;
import webb.shared.dtos.puzzle.created.CreatePuzzleLevelDTO;
import webb.shared.dtos.puzzle.updated.UpdatePuzzleLevelDTO;

/**
 * A screen for testing popups.
 */
public class ScreenTestComponents extends Screen {

    private static final Dimension BUTTON_DIMENSION = new Dimension(400, 43);

    @Override
    protected void populateComponents(Container contentPane, SpringLayout layout) {

        // Back button
        WebbBackButton backButton = new WebbBackButton(contentPane, layout, (self, rightClicked) -> {
            this.switchScreenTo(ScreenType.MAIN_MENU);
        });
        add(backButton);

        // Example popup
        WebbButton btn1 = new WebbButton("Example", (self, rightClicked) -> {
            showPopupExample();
        });
        btn1.setPreferredSize(BUTTON_DIMENSION);
        btn1.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_32);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btn1, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
        layout.putConstraint(SpringLayout.NORTH, btn1, 50, SpringLayout.NORTH, contentPane);

        this.add(btn1);

        // Statistics popup
        WebbButton btn2 = new WebbButton("Statistics", (self, rightClicked) -> {
            showPopupStatistics();
        });
        btn2.setPreferredSize(BUTTON_DIMENSION);
        btn2.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_32);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btn2, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
        layout.putConstraint(SpringLayout.NORTH, btn2, 10, SpringLayout.SOUTH, btn1);

        this.add(btn2);

        // Congratulations popup
        WebbButton btn3 = new WebbButton("Congrats", (self, rightClicked) -> {
            showPopupCongratulations();
        });
        btn3.setPreferredSize(BUTTON_DIMENSION);
        btn3.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_32);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btn3, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
        layout.putConstraint(SpringLayout.NORTH, btn3, 10, SpringLayout.SOUTH, btn2);

        this.add(btn3);

        // Leaderboard popup
        WebbButton btn4 = new WebbButton("Leaderboard", (self, rightClicked) -> {
            showPopupLeaderboard();
        });
        btn4.setPreferredSize(BUTTON_DIMENSION);
        btn4.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_32);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btn4, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
        layout.putConstraint(SpringLayout.NORTH, btn4, 10, SpringLayout.SOUTH, btn3);
        this.add(btn4);

        //Mousre test
        WebbButton btn5 = new WebbButton("Mouse Btn", (self, rightClicked) -> {
            if(rightClicked) {
                self.setText("Right");
            } else {
                self.setText("Left");
            }
        });
        btn5.setPreferredSize(BUTTON_DIMENSION);
        btn5.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_32);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btn5, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
        layout.putConstraint(SpringLayout.NORTH, btn5, 10, SpringLayout.SOUTH, btn4);

        this.add(btn5);


        WebbButton btn6 = new WebbButton("Play Game", (self, rightClicked) -> {
            switchScreenTo(ScreenType.PLAY_PUZZLE);
        });
        btn6.setPreferredSize(BUTTON_DIMENSION);
        btn6.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_32);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btn6, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
        layout.putConstraint(SpringLayout.NORTH, btn6, 10, SpringLayout.SOUTH, btn5);
        this.add(btn6);


        WebbButton btn7 = new WebbButton("BROKE: LoadFile", (self, rightClicked) -> {
            //TODO: Do we fix this? Not sure if we need it anymore becuse this will all go away.
//            try {
//                UpdatePuzzleLevelDTO puzzle = UpdatePuzzleLevelDTO.fromJSON(null);
//                System.out.println(puzzle);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
        });

        btn7.setPreferredSize(BUTTON_DIMENSION);
        btn7.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_32);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btn7, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
        layout.putConstraint(SpringLayout.NORTH, btn7, 10, SpringLayout.SOUTH, btn6);
        this.add(btn7);


        WebbButton btn8 = new WebbButton("Mouse Release", (self, rightClicked) -> {
            self.setTextColor(Color.GREEN);
            System.out.println("Click");
            if(rightClicked) {
                self.setText("Right");
            } else {
                self.setText("Left");
            }
        });
        btn8.setClickReleaseListener((self, rightClicked) -> {
            System.out.println("Release");
            self.setTextColor(Color.RED);
            if(rightClicked) {
                self.setText("Release Right");
            } else {
                self.setText("Release Left");
            }


        });
        btn8.setPreferredSize(BUTTON_DIMENSION);
        btn8.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_32);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btn8, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
        layout.putConstraint(SpringLayout.NORTH, btn8, 10, SpringLayout.SOUTH, btn7);
        this.add(btn8);


        WebbButton btn9 = new WebbButton("Error Handling Test", (self, rightClicked) -> {
            throw new RuntimeException("This is a test of the error handling system");
        });

        btn9.setPreferredSize(BUTTON_DIMENSION);
        btn9.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_32);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btn9, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
        layout.putConstraint(SpringLayout.NORTH, btn9, 10, SpringLayout.SOUTH, btn8);
        this.add(btn9);


        WebbButton btn10 = new WebbButton("Background space panel", (self, rightClicked) -> {
            switchScreenTo(ScreenType.TEST_DRAWING);
        });

        btn10.setPreferredSize(BUTTON_DIMENSION);
        btn10.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_32);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btn10, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
        layout.putConstraint(SpringLayout.NORTH, btn10, 10, SpringLayout.SOUTH, btn9);
        this.add(btn10);


        WebbButton btn11 = new WebbButton("Background confetti", (self, rightClicked) -> {
            switchScreenTo(ScreenType.TEST_CONFETTI);
        });

        btn11.setPreferredSize(BUTTON_DIMENSION);
        btn11.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_32);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btn11, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
        layout.putConstraint(SpringLayout.NORTH, btn11, 10, SpringLayout.SOUTH, btn10);
        this.add(btn11);


        WebbInputBox btn12 = new WebbInputBox(this, 10);
        btn12.setText("Input box");
        btn12.setBackground(WebbColors.B7);

        btn12.setPreferredSize(BUTTON_DIMENSION);
        btn12.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_32);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btn12, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
        layout.putConstraint(SpringLayout.NORTH, btn12, 10, SpringLayout.SOUTH, btn11);
        this.add(btn12);


        WebbCheckbox btn13 = new WebbCheckbox(true);

        //btn13.setPreferredSize(BUTTON_DIMENSION);
        btn13.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_32);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btn13, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
        layout.putConstraint(SpringLayout.NORTH, btn13, 10, SpringLayout.SOUTH, btn12);
        this.add(btn13);

    }

    //Show the popup on window load for easy testing
    @Override
    public void onShow() {
        //showPopupLeaderboard();
    }

    // Show the congratulations popup with dummy data
    private void showPopupCongratulations() {
        showPopup(new PopupCongratulations(
                null,
                DummyCongratulationsData.SOLVE_TIME,
                DummyCongratulationsData.PROGRESS_MIN,
                DummyCongratulationsData.PROGRESS_MAX,
                DummyCongratulationsData.PROGRESS_CURRENT,
                DummyCongratulationsData.CURRENT_TITLE,
                DummyCongratulationsData.NEXT_TITLE
        ));
    }

    // Show the statistics popup with dummy data
    private void showPopupStatistics() {
        showPopup(new PopupStatistics(
                DummyStatisticsData.DATA
        ));
    }

    // Show the leaderboard popup with dummy data
    private void showPopupLeaderboard() {
        showPopup(new PopupLeaderboard(
                DummyLeaderboardData.SCORES_300
        ));
    }

    // Show the example popup
    private void showPopupExample() {
        showPopup(new ExampleWebbPopup());
    }

}
