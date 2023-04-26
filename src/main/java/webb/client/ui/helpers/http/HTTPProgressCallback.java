package webb.client.ui.helpers.http;

public interface HTTPProgressCallback {

    /**
     * Called when we download a new chunk of data.
     * @param progress from 0-1
     */
    void onProgressUpdate(float progress);
}
