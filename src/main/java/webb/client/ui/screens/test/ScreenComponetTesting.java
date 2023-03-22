package webb.client.ui.screens.test;

import java.awt.Container;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.SpringLayout;
import webb.client.ui.popup.leaderboard.WebbLeaderboardTable;
import webb.client.ui.popup.leaderboard.WebbTableScroller;
import webb.client.ui.screens.Screen;

public class ScreenComponetTesting extends Screen {
    @Override
    protected void populateComponents(Container contentPane, SpringLayout layout) {



        WebbLeaderboardTable table = new WebbLeaderboardTable(generateDummyData(1000));


        WebbTableScroller scrollPane = new WebbTableScroller(table);

        layout.putConstraint(SpringLayout.NORTH, scrollPane, 0, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST, scrollPane, 0, SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.EAST, scrollPane, 0, SpringLayout.EAST, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, scrollPane, 0, SpringLayout.SOUTH, contentPane);
        contentPane.add(scrollPane);
    }

    private static String[][] generateDummyData(int amount) {
        String[][] data = new String[amount][3];
        for (int i = 0; i < amount; i++) {
            data[i][0] = "" + i;
            data[i][1] = UUID.randomUUID().toString();
            data[i][2] = "" + ThreadLocalRandom.current().nextInt(0, 100);
        }
        return data;
    }
}
