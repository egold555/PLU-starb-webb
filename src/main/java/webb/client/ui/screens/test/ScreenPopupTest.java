package webb.client.ui.screens.test;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.SpringLayout;
import webb.client.ui.components.WebbBackButton;
import webb.client.ui.components.WebbButton;
import webb.client.ui.constants.WebbFonts;
import webb.client.ui.popup.ExampleWebbPopup;
import webb.client.ui.popup.PopupStatistics;
import webb.client.ui.screens.Screen;
import webb.client.ui.testing.DummyData;
import webb.client.ui.testing.DummyData.DummyStatistics;

public class ScreenPopupTest extends Screen {

    @Override
    protected void populateComponents(Container contentPane, SpringLayout layout) {

        WebbBackButton backButton = new WebbBackButton(contentPane, layout, () -> {
            this.switchScreenTo(ScreenType.MAIN_MENU);
        });
        add(backButton);

        WebbButton btn1 = new WebbButton("Example", () -> {
            ExampleWebbPopup popup = new ExampleWebbPopup();
            showPopup(popup);
        });
        btn1.setPreferredSize(new Dimension(142, 43));
        btn1.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_32);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btn1, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
        layout.putConstraint(SpringLayout.NORTH, btn1, 50, SpringLayout.NORTH, contentPane);

        this.add(btn1);

        WebbButton btn2 = new WebbButton("Statistics", () -> {
            showPopup(new PopupStatistics(
                    DummyStatistics.CURRENT_TITLE,
                    DummyStatistics.GAMES_COMPLETED,
                    DummyStatistics.GAMES_MAX,
                    DummyStatistics.SOLVE_TIME_MIN,
                    DummyStatistics.SOLVE_TIME_MAX,
                    DummyStatistics.SOLVE_TIME_AVERAGE
            ));
        });
        btn2.setPreferredSize(new Dimension(142, 43));
        btn2.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_32);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btn2, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
        layout.putConstraint(SpringLayout.NORTH, btn2, 10, SpringLayout.SOUTH, btn1);

        this.add(btn2);

        WebbButton btn3 = new WebbButton("BTN3", () -> {

        });
        btn3.setPreferredSize(new Dimension(142, 43));
        btn3.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_32);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btn3, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
        layout.putConstraint(SpringLayout.NORTH, btn3, 10, SpringLayout.SOUTH, btn2);

        this.add(btn3);


        WebbButton btn4 = new WebbButton("BTN4", () -> {

        });
        btn4.setPreferredSize(new Dimension(142, 43));
        btn4.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_32);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btn4, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
        layout.putConstraint(SpringLayout.NORTH, btn4, 10, SpringLayout.SOUTH, btn3);

        this.add(btn4);

    }

    @Override
    public void onShow() {
        showPopup(new PopupStatistics(
                DummyStatistics.CURRENT_TITLE,
                DummyStatistics.GAMES_COMPLETED,
                DummyStatistics.GAMES_MAX,
                DummyStatistics.SOLVE_TIME_MIN,
                DummyStatistics.SOLVE_TIME_MAX,
                DummyStatistics.SOLVE_TIME_AVERAGE
        ));
    }

}
