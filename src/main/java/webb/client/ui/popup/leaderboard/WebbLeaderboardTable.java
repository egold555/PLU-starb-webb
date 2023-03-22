package webb.client.ui.popup.leaderboard;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import webb.client.ui.components.WebbTable;
import webb.client.ui.constants.WebbColors;
import webb.client.ui.constants.WebbFonts;

public class WebbLeaderboardTable extends WebbTable {
    public WebbLeaderboardTable(String[][] data) {
        super(new String[]{"Rank", "Name", "# Puzzles"}, data);
        setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_24);
        setAlternatingColors(WebbColors.c6C, WebbColors.c90);
        setHeaderColor(WebbColors.c6C.darker());
        setForeground(WebbColors.TEXT_COLOR_WHITE);
        setRowHeight(30);

        this.setTableEditingAllowed(false);

        this.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);

        this.setColWidth(0, 100);
        this.setColWidth(2, 200);

       // resizeColumnWidth(this);
    }
}
