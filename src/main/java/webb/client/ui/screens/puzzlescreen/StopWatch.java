package webb.client.ui.screens.puzzlescreen;

/**
 * Seth's stopwatch. Ive added it here because
 * It was a seperate project he made, and emailed it to me
 *
 * @author Seth
 */
public class StopWatch {

    private long startTime;
    private long currentTime;
    private boolean running;
    private StopWatchCallback callback;

    public StopWatch(StopWatchCallback callback) {
        this.callback = callback;
    }

    public void start() {

        if(!running) {
            running = true;
            startTime = System.currentTimeMillis() - currentTime;
            new Thread(() -> {
                while (running) {
                    currentTime = System.currentTimeMillis() - startTime;
                    updateLabels();
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
                updateLabels();
            }).start();
        }
    }

    private void updateLabels() {
        if(callback != null) {
            callback.updateLabels(currentTime);
        }
    }

    public void stop() {
        running = false;
    }

    public void reset() {
        running = false;
        startTime = 0;
        currentTime = 0;
        updateLabels();
    }

    public interface StopWatchCallback {
        public void updateLabels(long currentTime);
    }

    public long getTime() {
        return currentTime;
    }

}
