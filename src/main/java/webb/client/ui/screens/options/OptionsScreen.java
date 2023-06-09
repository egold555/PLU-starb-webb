package webb.client.ui.screens.options;

import java.awt.Container;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import webb.client.ui.WebbWindow;
import webb.client.ui.components.WebbBackButton;
import webb.client.ui.components.WebbCheckbox;
import webb.client.ui.constants.WebbColors;
import webb.client.ui.constants.WebbFonts;
import webb.client.ui.screens.Screen;
import webb.client.ui.screens.ScreenType;

public class OptionsScreen extends Screen {

    private WebbCheckbox bgMusicCheckbox;
    private WebbCheckbox sfxCheckbox;

    @Override
    protected void populateComponents(Container contentPane, SpringLayout layout) {

        JLabel titleText = new JLabel("Game Options");
        titleText.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_64);
        titleText.setForeground(WebbColors.TEXT_COLOR_BLACK);
        layout.putConstraint(SpringLayout.NORTH, titleText, 20, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, titleText, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
        this.add(titleText);

        //---------------------------------[ BG Music ]---------------------------------//
        JLabel bgMusicOption = new JLabel("BG Music");
        bgMusicOption.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_48);
        bgMusicOption.setForeground(WebbColors.TEXT_COLOR_BLACK);
        layout.putConstraint(SpringLayout.NORTH, bgMusicOption, 205, SpringLayout.NORTH, titleText);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, bgMusicOption, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
        this.add(bgMusicOption);

        bgMusicCheckbox = new WebbCheckbox((self, value) -> {
            System.out.println("BG Music checkbox pressed");
            WebbWindow.getInstance().getGameOptions().setBgMusicEnabled(value);
            saveOptions();
        });
        layout.putConstraint(SpringLayout.NORTH, bgMusicCheckbox, 200, SpringLayout.NORTH, titleText);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, bgMusicCheckbox, -150, SpringLayout.HORIZONTAL_CENTER, contentPane);
        this.add(bgMusicCheckbox);

        //---------------------------------[ SFX ]---------------------------------//
        JLabel sfxOption = new JLabel("SFX");
        sfxOption.setFont(WebbFonts.BALSAMIQ_SANS_REGULAR_48);
        sfxOption.setForeground(WebbColors.TEXT_COLOR_BLACK);
        layout.putConstraint(SpringLayout.NORTH, sfxOption, 105, SpringLayout.NORTH, bgMusicOption);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, sfxOption, -60, SpringLayout.HORIZONTAL_CENTER, bgMusicOption);
        this.add(sfxOption);

        sfxCheckbox = new WebbCheckbox((self, value) -> {
            System.out.println("SFX checkbox pressed");
            WebbWindow.getInstance().getGameOptions().setSfxEnabled(value);
            saveOptions();
        });
        layout.putConstraint(SpringLayout.NORTH, sfxCheckbox, 100, SpringLayout.NORTH, bgMusicOption);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, sfxCheckbox, -150, SpringLayout.HORIZONTAL_CENTER, bgMusicOption);
        this.add(sfxCheckbox);

        this.add(new WebbBackButton(contentPane, layout, (self, rightClicked) -> {
            System.out.println("Back button pressed");
            switchScreenTo(ScreenType.MAIN_MENU);
        }));

    }

    private void saveOptions() {
        try {
            WebbWindow.getInstance().getGameOptions().save();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onShow() {
        bgMusicCheckbox.setChecked(WebbWindow.getInstance().getGameOptions().isBgMusicEnabled());
        sfxCheckbox.setChecked(WebbWindow.getInstance().getGameOptions().isSfxEnabled());
    }
}
