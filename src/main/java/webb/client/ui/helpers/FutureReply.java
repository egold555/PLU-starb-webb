package webb.client.ui.helpers;

/**
 * A callback interface for when a future reply is received.
 *
 * @param <T> The type of the reply.
 */
public interface FutureReply<T> {

    /**
     * Called when a reply is received.
     *
     * @param reply The reply.
     */
    void reply(T reply);

}
