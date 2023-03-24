package webb.client.ui.helpers;

import java.util.concurrent.TimeUnit;

/**
 * Utility class for formatting text.
 */
public class WebbTextUtilities {
    private WebbTextUtilities() {}

    /**
     * Formats a time in milliseconds to a string in the format mm:ss.
     * @param time The time in milliseconds.
     * @return The formatted string.
     */
    public static String formatMinSec(long time) {
        long minutes = TimeUnit.MILLISECONDS.toMinutes(time);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(time) - TimeUnit.MINUTES.toSeconds(minutes);
        return String.format("%02d:%02d", minutes, seconds);
    }
}
