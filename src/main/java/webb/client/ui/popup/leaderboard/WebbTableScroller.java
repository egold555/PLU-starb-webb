package webb.client.ui.popup.leaderboard;

import javax.swing.JScrollPane;
import webb.client.ui.components.WebbCustomScrollbar;
import webb.client.ui.components.WebbTable;
import webb.client.ui.constants.WebbColors;

public class WebbTableScroller extends JScrollPane {

    public WebbTableScroller(WebbTable table) {
        super(table);
        this.getVerticalScrollBar().setUI(new WebbCustomScrollbar(
                WebbColors.c90,
                WebbColors.c6C,
                WebbColors.c6C.darker(),
                WebbColors.c6C.darker().darker()
        ));
    }
}

