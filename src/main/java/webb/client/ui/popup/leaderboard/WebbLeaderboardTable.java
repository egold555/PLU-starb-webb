package webb.client.ui.popup.leaderboard;

import java.util.Collections;
import java.util.List;
import javax.swing.JTable;
import webb.client.ui.components.WebbTable;
import webb.client.ui.constants.WebbColors;
import webb.client.ui.constants.WebbFonts;
import webb.shared.dtos.leaderboard.LeaderboardEntryDTO;

/**
 * A table that displays the leaderboard scores.
 */
public class WebbLeaderboardTable extends WebbTable {
    /**
     * Creates a new table with the given scores.
     * @param scores The scores to display.
     */
    public WebbLeaderboardTable(List<LeaderboardEntryDTO> scores) {
        super(new String[]{"Rank", "Name", "Puzzles Completed"}, generateFromLeaderboardScore(scores));
        setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_24);
        setAlternatingColors(WebbColors.c6C, WebbColors.c90);
        setHeaderColor(WebbColors.c6C.darker());
        setForeground(WebbColors.TEXT_COLOR_WHITE);
        setRowHeight(30);

        this.setTableEditingAllowed(false);

        this.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);

        this.setColWidth(0, 100);
        this.setColWidth(2, 300);

        this.removeCellLines();

    }

    /**
     * Generates a 2D array of data from the given scores.
     * @param scores The scores to generate data from.
     * @return A 2D array of data.
     */
    private static String[][] generateFromLeaderboardScore(List<LeaderboardEntryDTO> scores) {
         // Sorts using the compareTo method in LeaderboardScore
        Collections.sort(scores);
        final int size = scores.size();
        String[][] data = new String[size][3];
        for (int i = 0; i < size; i++) {
            data[i][0] = String.valueOf(i + 1);
            data[i][1] = scores.get(i).getUsername();
            data[i][2] = String.valueOf(scores.get(i).getCompletedPuzzles());
        }
        return data;
    }
}
