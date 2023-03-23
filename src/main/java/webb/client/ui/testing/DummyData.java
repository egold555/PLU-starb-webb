package webb.client.ui.testing;

import java.util.concurrent.TimeUnit;

public class DummyData {

    public static class DummyStatistics {
        public static final String CURRENT_TITLE = "Cadet";
        public static final int GAMES_COMPLETED = 200;
        public static final int GAMES_MAX = 500;

        public static final long SOLVE_TIME_MIN = TimeUnit.SECONDS.toMillis(30);
        public static final long SOLVE_TIME_MAX = TimeUnit.MINUTES.toMillis(5) + TimeUnit.SECONDS.toMillis(10);
        public static final long SOLVE_TIME_AVERAGE = TimeUnit.MINUTES.toMillis(1) + TimeUnit.SECONDS.toMillis(33);
    }

    public static class DummyCongratulations {
        public static final long SOLVE_TIME = TimeUnit.MINUTES.toMillis(1) + TimeUnit.SECONDS.toMillis(2);
        public static final int PROGRESS_MIN = 0;
        public static final int PROGRESS_MAX = 4;
        public static final int PROGRESS_CURRENT = 2;
        public static final String CURRENT_TITLE = "Cadet";
        public static final String NEXT_TITLE = "Ensign";
    }

}
