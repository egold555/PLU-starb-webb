package webb.client.ui.popup.leaderboard;

import javax.swing.JPanel;
import javax.swing.SpringLayout;
import webb.client.ui.components.WebbCustomScrollpane;
import webb.client.ui.constants.WebbColors;
import webb.client.ui.popup.WebbPopup;
import webb.shared.dtos.leaderboard.LeaderboardDTO;

/**
 * A popup that displays the leaderboard.
 */
public class PopupLeaderboard extends WebbPopup {

    private final LeaderboardDTO scores;

    /**
     * Creates a new PopupLeaderboard.
     *
     * @param scores The scores to display.
     */
    public PopupLeaderboard(LeaderboardDTO scores) {
        super("Leaderboard");
        this.scores = scores;
    }

    @Override
    protected void populateComponents(JPanel contentPane, SpringLayout layout) {
        WebbLeaderboardTable table = new WebbLeaderboardTable(scores.getData());

        WebbCustomScrollpane scrollPane = new WebbCustomScrollpane(
                table,
                WebbColors.c90,
                WebbColors.c6C,
                WebbColors.c6C.darker(),
                WebbColors.c6C.darker().darker()
        );


        // Make the scrollbar thinner
        scrollPane.setVerticalScrollbarWidth(10);

        layout.putConstraint(SpringLayout.NORTH, scrollPane, 90, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST, scrollPane, 20, SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.EAST, scrollPane, -20, SpringLayout.EAST, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, scrollPane, -20, SpringLayout.SOUTH, contentPane);
        contentPane.add(scrollPane);
    }
}
