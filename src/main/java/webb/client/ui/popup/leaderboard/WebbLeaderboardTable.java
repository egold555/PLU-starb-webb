package webb.client.ui.popup.leaderboard;

import java.util.Arrays;
import javax.swing.JTable;
import webb.client.ui.components.WebbTable;
import webb.client.ui.constants.WebbColors;
import webb.client.ui.constants.WebbFonts;

public class WebbLeaderboardTable extends WebbTable {
    public WebbLeaderboardTable(LeaderboardScore... scores) {
        super(new String[]{"Rank", "Name", "# Puzzles"}, generateFromLeaderboardScore(scores));
        setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_24);
        setAlternatingColors(WebbColors.c6C, WebbColors.c90);
        setHeaderColor(WebbColors.c6C.darker());
        setForeground(WebbColors.TEXT_COLOR_WHITE);
        setRowHeight(30);

        this.setTableEditingAllowed(false);

        this.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);

        this.setColWidth(0, 100);
        this.setColWidth(2, 200);

        this.removeCellLines();

    }

    private static String[][] generateFromLeaderboardScore(LeaderboardScore[] scores) {
        Arrays.sort(scores); // Sorts using the compareTo method in LeaderboardScore
        String[][] data = new String[scores.length][3];
        for (int i = 0; i < scores.length; i++) {
            data[i][0] = "" + (i + 1);
            data[i][1] = scores[i].getName();
            data[i][2] = "" + scores[i].getScore();
        }
        return data;
    }
}
