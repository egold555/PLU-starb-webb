package webb.client.ui.helpers;

import java.util.concurrent.TimeUnit;

public class WebbTextUtilities {
    private WebbTextUtilities() {}

    public static String formatMinSec(long time) {
        long minutes = TimeUnit.MILLISECONDS.toMinutes(time);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(time) - TimeUnit.MINUTES.toSeconds(minutes);
        return String.format("%02d:%02d", minutes, seconds);
    }
}
