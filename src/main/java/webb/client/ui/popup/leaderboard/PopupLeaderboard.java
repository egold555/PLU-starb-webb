package webb.client.ui.popup.leaderboard;

import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;
import webb.client.ui.components.WebbCustomScrollbar;
import webb.client.ui.constants.WebbColors;
import webb.client.ui.popup.WebbPopup;

public class PopupLeaderboard extends WebbPopup {

    private final LeaderboardScore[] scores;

    public PopupLeaderboard(LeaderboardScore[] scores) {
        super("Leaderboard");
        this.scores = scores;
    }

    @Override
    protected void populateComponents(JPanel contentPane, SpringLayout layout) {
        WebbLeaderboardTable table = new WebbLeaderboardTable(scores);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getVerticalScrollBar().setUI(new WebbCustomScrollbar(
                WebbColors.c90,
                WebbColors.c6C,
                WebbColors.c6C.darker(),
                WebbColors.c6C.darker().darker()
        ));

        Dimension tmpSize = new Dimension(10, 0);
        scrollPane.getVerticalScrollBar().setMaximumSize(tmpSize);
        scrollPane.getVerticalScrollBar().setPreferredSize(tmpSize);
        scrollPane.getVerticalScrollBar().setMinimumSize(tmpSize);

        layout.putConstraint(SpringLayout.NORTH, scrollPane, 90, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST, scrollPane, 20, SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.EAST, scrollPane, -20, SpringLayout.EAST, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, scrollPane, -20, SpringLayout.SOUTH, contentPane);
        contentPane.add(scrollPane);
    }
}
