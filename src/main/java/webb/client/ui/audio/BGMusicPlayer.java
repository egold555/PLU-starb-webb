package webb.client.ui.audio;

import webb.client.ui.WebbWindow;

public class BGMusicPlayer {

    private SoundPlayer sp;
    private String currentSong;

    public void playBG(String name) {

        if(!WebbWindow.getInstance().getGameOptions().isBgMusicEnabled()) {
            return;
        }

        if(currentSong != null && currentSong.equals(name)) {
            return;
        }

        currentSong = name;

        if(sp != null) {
            sp.fadeVolumeOverXSeconds(0, 1_000, () -> {
                sp = new SoundPlayer(name);
                sp.start();
                sp.setLoop();
            });
        }
        else {
            sp = new SoundPlayer(name);
            sp.start();
            sp.setLoop();
        }

    }

    public String getCurrentSong() {
        return currentSong;
    }
}
