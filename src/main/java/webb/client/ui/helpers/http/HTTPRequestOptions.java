package webb.client.ui.helpers.http;

import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * This class is used to store the options for an HTTP request.
 * @param <T> The type of the default value.
 */
public class HTTPRequestOptions<T> {

    private RequestType requestType = RequestType.GET;
    private ObjectNode postData = null;
    private HTTPProgressCallback progressCallback = null;
    private T defaultValue = null;
    private boolean authenticate = true;

    /**
     * Returns the progress callback. Default is NULL
     * @return The progress callback.
     */
    public HTTPProgressCallback getProgressCallback() {
        return progressCallback;
    }

    /**
     * Sets the progress callback for the request.
     * @param progressCallback The progress callback.
     */
    public void setProgressCallback(HTTPProgressCallback progressCallback) {
        this.progressCallback = progressCallback;
    }

    /**
     * Returns the post data for the request. Default is NULL
     * @return The post data.
     */
    public ObjectNode getPostData() {
        return postData;
    }

    /**
     * Sets the post data for the request. NOT used for GET requests.
     * @param postData The post data.
     */
    public void setPostData(ObjectNode postData) {
        this.postData = postData;
    }

    /**
     * Returns the request type. Default is GET
     * @return The request type.
     */
    public RequestType getRequestType() {
        return requestType;
    }

    /**
     * Sets the request type for the request.
     * @param requestType The request type.
     */
    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    /**
     * Returns the default value for the request. Default is NULL
     * @return The default value.
     */
    public T getDefaultValue() {
        return defaultValue;
    }

    /**
     * Sets the default value for the request.
     * @param defaultValue The default value.
     */
    public void setDefaultValue(T defaultValue) {
        this.defaultValue = defaultValue;
    }


    /**
     * Returns whether or not the request is an authenticated request. Default is true.
     * @return Whether or not the request is an authenticated request.
     */
    public boolean isAuthenticatedRequest() {
        return authenticate;
    }

    /**
     * Sets whether or not the request should use authentication.
     * @param authenticate Whether or not the request should use authentication.
     */
    public void setAuthenticatedRequest(boolean authenticate) {
        this.authenticate = authenticate;
    }
}
