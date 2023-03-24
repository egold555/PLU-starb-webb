package webb.client.ui.screens.test;

import java.awt.Container;
import java.awt.Dimension;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;
import webb.client.ui.components.WebbCustomScrollpane;
import webb.client.ui.constants.WebbColors;
import webb.client.ui.popup.leaderboard.LeaderboardScore;
import webb.client.ui.popup.leaderboard.WebbLeaderboardTable;
import webb.client.ui.screens.Screen;

/**
 * A screen for testing the leaderboard table
 */
public class ScreenComponetTesting extends Screen {
    @Override
    protected void populateComponents(Container contentPane, SpringLayout layout) {



        WebbLeaderboardTable table = new WebbLeaderboardTable(generateDummyData(100));


        WebbCustomScrollpane scrollPane = new WebbCustomScrollpane(
                WebbColors.c90,
                WebbColors.c6C,
                WebbColors.c6C.darker(),
                WebbColors.c6C.darker().darker()
        );

        // Make the scrollbar thinner
        scrollPane.setVerticalScrollbarWidth(10);

        layout.putConstraint(SpringLayout.NORTH, scrollPane, 0, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST, scrollPane, 0, SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.EAST, scrollPane, 0, SpringLayout.EAST, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, scrollPane, 0, SpringLayout.SOUTH, contentPane);
        contentPane.add(scrollPane);
    }

    /**
     * Generates dummy data for the leaderboard.
     * @param amount The amount of data to generate.
     * @return The generated data.
     */
    private static LeaderboardScore[] generateDummyData(int amount) {
        LeaderboardScore[] data = new LeaderboardScore[amount];
        for (int i = 0; i < amount; i++) {
            data[i] = new LeaderboardScore(UUID.randomUUID().toString(), ThreadLocalRandom.current().nextInt(0, 100000));
        }
        return data;
    }
}
