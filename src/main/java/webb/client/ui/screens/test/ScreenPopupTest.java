package webb.client.ui.screens.test;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.SpringLayout;
import webb.client.ui.components.WebbBackButton;
import webb.client.ui.components.WebbButton;
import webb.client.ui.constants.WebbFonts;
import webb.client.ui.popup.ExampleWebbPopup;
import webb.client.ui.popup.PopupStatistics;
import webb.client.ui.popup.congratulations.PopupCongratulations;
import webb.client.ui.popup.leaderboard.PopupLeaderboard;
import webb.client.ui.screens.Screen;
import webb.client.ui.screens.ScreenType;
import webb.client.ui.testing.DummyData.DummyCongratulationsData;
import webb.client.ui.testing.DummyData.DummyLeaderboardData;
import webb.client.ui.testing.DummyData.DummyStatisticsData;

/**
 * A screen for testing popups.
 */
public class ScreenPopupTest extends Screen {

    @Override
    protected void populateComponents(Container contentPane, SpringLayout layout) {

        // Back button
        WebbBackButton backButton = new WebbBackButton(contentPane, layout, () -> {
            this.switchScreenTo(ScreenType.MAIN_MENU);
        });
        add(backButton);

        // Example popup
        WebbButton btn1 = new WebbButton("Example", () -> {
            showPopupExample();
        });
        btn1.setPreferredSize(new Dimension(142, 43));
        btn1.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_32);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btn1, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
        layout.putConstraint(SpringLayout.NORTH, btn1, 50, SpringLayout.NORTH, contentPane);

        this.add(btn1);

        // Statistics popup
        WebbButton btn2 = new WebbButton("Statistics", () -> {
            showPopupStatistics();
        });
        btn2.setPreferredSize(new Dimension(142, 43));
        btn2.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_32);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btn2, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
        layout.putConstraint(SpringLayout.NORTH, btn2, 10, SpringLayout.SOUTH, btn1);

        this.add(btn2);

        // Congratulations popup
        WebbButton btn3 = new WebbButton("Congrats", () -> {
            showPopupCongratulations();
        });
        btn3.setPreferredSize(new Dimension(142, 43));
        btn3.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_32);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btn3, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
        layout.putConstraint(SpringLayout.NORTH, btn3, 10, SpringLayout.SOUTH, btn2);

        this.add(btn3);

        // Leaderboard popup
        WebbButton btn4 = new WebbButton("Leaderboard", () -> {
            showPopupLeaderboard();
        });
        btn4.setPreferredSize(new Dimension(142, 43));
        btn4.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_32);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btn4, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
        layout.putConstraint(SpringLayout.NORTH, btn4, 10, SpringLayout.SOUTH, btn3);

        this.add(btn4);

    }

    //Show the popup on window load for easy testing
    @Override
    public void onShow() {
        showPopupLeaderboard();
    }

    // Show the congratulations popup with dummy data
    private void showPopupCongratulations() {
        showPopup(new PopupCongratulations(
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
                DummyStatisticsData.CURRENT_TITLE,
                DummyStatisticsData.GAMES_COMPLETED,
                DummyStatisticsData.GAMES_MAX,
                DummyStatisticsData.SOLVE_TIME_MIN,
                DummyStatisticsData.SOLVE_TIME_MAX,
                DummyStatisticsData.SOLVE_TIME_AVERAGE
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
