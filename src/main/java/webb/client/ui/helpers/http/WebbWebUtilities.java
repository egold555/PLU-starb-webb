package webb.client.ui.helpers.http;

import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WebbWebUtilities {

    //Do not instantiate
    private WebbWebUtilities() {}

    //TODO: Change me
    private static final String BASE_URL = "https://cs390.golde.org/api-mockup/v1/";

    //Define the codec for the ObjectMapper to parse.
    //Make sure if there is no empty constructor for the class
    //you can use the @JsonCreator annotation for every variable in the constructor.
    private static final ObjectCodec DEFAULT_JSON_CODEC = new ObjectMapper().getFactory().getCodec();

    //The threadpool to use for all the requests. Max 10 requests at a time.
    private static final ExecutorService THREAD_POOL = Executors.newFixedThreadPool(10);

    /**
     * Makes a request to the server and returns the response as a T object.
     * @param urlStr The URL to make the request to.
     * @param clazz The class to parse the response into.
     * @param options The options for the request.
     * @param futureReply The future reply to reply to.
     * @param <T> The type of object to parse the response into.
     */
    public static <T> void makeRequestAsync(String urlStr, Class<T> clazz, HTTPRequestOptions<T> options, FutureReply<T> futureReply) {
        THREAD_POOL.submit(() -> {
            T node = makeRequest(urlStr, clazz, options);
            futureReply.reply(node);
        });
    }

    /**
     * Makes a request to the server and returns the response as a ObjectNode
     * @param urlStr The URL to make the request to.
     * @param options The options for the request.
     * @param futureReply The future reply to reply to.
     */
    public static void makeRequestAsync(String urlStr, HTTPRequestOptions<ObjectNode> options, FutureReply<ObjectNode> futureReply) {
        THREAD_POOL.submit(() -> {
            ObjectNode node = makeRequest(urlStr, options);
            futureReply.reply(node);
        });
    }

    /**
     * Makes a request to the server and returns the response as a T object.
     * @param urlStr The URL to make the request to.
     * @param clazz The class to parse the response into.
     * @param options The options for the request.
     * @return The response from the server as a T object.
     * @param <T> The type of object to parse the response into.
     */
    public static <T> T makeRequest(String urlStr, Class<T> clazz, HTTPRequestOptions<T> options) {
        ObjectNode node = makeRequest(urlStr, options);
        if (node.get("success").asBoolean()) {
            try {
                return DEFAULT_JSON_CODEC.treeToValue(node.get("data"), clazz);
            }
            catch (Exception e) {
                e.printStackTrace();
                return options.getDefaultValue();
            }
        }
        else {
            return options.getDefaultValue();
        }
    }

    /**
     * Makes a request to the server and returns the response as a ObjectNode.
     * @param urlStr The URL to make the request to.
     * @param options The options for the request.
     * @return The response from the server as a ObjectNode.
     */
    public static ObjectNode makeRequest(String urlStr, HTTPRequestOptions<?> options) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode rootNode = mapper.createObjectNode();

        try {
            URL url = new URL(BASE_URL + urlStr);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(options.getRequestType().name());

            if(options.getRequestType() != RequestType.GET && options.getPostData() != null) {
                final String body = options.getPostData().toString();

                // Fix for some servers interpreting the request as a GET request if the URL doesn't end with a slash.
                // This took me an hour to figure out. I hate this.
                if(!urlStr.endsWith("/") && !urlStr.endsWith("?")) {
                    urlStr += "/";
                }

                con.setDoOutput(true);
                con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                con.setRequestProperty( "Content-Length", String.valueOf(body.length()));
                con.setRequestProperty("Accept", "application/json");


                OutputStream os = con.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
                osw.write(body);
                osw.flush();
                osw.close();
                os.flush();
                os.close();
            }


            int totalBytes = con.getContentLength();

            BufferedReader inputReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            //read 1024 bytes at a time
            char[] buffer = new char[1024];
            int bytesRead = 0;
            int bytesReadTotal = 0;
            StringBuilder content = new StringBuilder();

            while ((bytesRead = inputReader.read(buffer)) != -1) {
                content.append(buffer, 0, bytesRead);
                bytesReadTotal += bytesRead;
                if (options.getProgressCallback() != null) {
                    float percentComplete = (float) bytesReadTotal / totalBytes;
                    options.getProgressCallback().onProgressUpdate(percentComplete);
                }
            }

            inputReader.close();

            final int httpStatusCode = con.getResponseCode();

            // sometimes we don't math correctly even though we read the entire file
            if (options.getProgressCallback() != null) {
                options.getProgressCallback().onProgressUpdate(1.0f);
            }

            try {
                JsonNode jsonNode = mapper.readTree(content.toString());
                rootNode.put("success", true);
                rootNode.put("httpStatusCode", httpStatusCode);
                rootNode.put("dataSizeBytes", bytesRead);
                rootNode.set("data", jsonNode);
            }
            catch (Exception e) {
                rootNode.put("success", false);
                rootNode.put("httpStatusCode", httpStatusCode);
                rootNode.put("dataSizeBytes", bytesRead);
                rootNode.put("error", e.getMessage());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            rootNode.put("success", false);
            rootNode.put("error", e.getMessage());
        }

        return rootNode;
    }

    /**
     * Makes a GET request to the specified URL and returns the response as a JSON object.
     * @param urlStr The URL to make the request to.
     * @return The response as a JSON object.
     * {
     *     "success": true, //false if there was any error
     *     "httpStatusCode": 200, //The HTTP status code we successfully made the request to the server
     *     "error": "...", //Only present if success is false. Returns the error message from the catch statements
     *     "data": {...} //The data returned by the server if everything went well. Else its null.
     * }
     */

    @Deprecated
    public static ObjectNode getRequest(String urlStr) {
        return makeRequest(urlStr, new HTTPRequestOptions<>());
    }

    @Deprecated
    public static ObjectNode getRequest(String urlStr, HTTPProgressCallback progressCallback) {
        HTTPRequestOptions<ObjectNode> options = new HTTPRequestOptions<>();
        options.setProgressCallback(progressCallback);
        return makeRequest(urlStr, options);
    }

    /**
     * Makes a GET request to the specified URL and returns the response as a JSON object.
     * @param urlStr The URL to make the request to.
     * @param futureReply  The response as a JSON object.
     * {
     *     "success": true, //false if there was any error
     *     "httpStatusCode": 200, //The HTTP status code we successfully made the request to the server
     *     "error": "...", //Only present if success is false. Returns the error message from the catch statements
     *     "data": {...} //The data returned by the server if everything went well. Else its null.
     * }
     */
    @Deprecated
    public static void getRequestAsync(String urlStr, FutureReply<ObjectNode> futureReply) {
        makeRequestAsync(urlStr, new HTTPRequestOptions<>(), futureReply);
    }

    @Deprecated
    public static void getRequestAsync(String urlStr, FutureReply<ObjectNode> futureReply, HTTPProgressCallback progressCallback) {
        HTTPRequestOptions<ObjectNode> options = new HTTPRequestOptions<>();
        options.setProgressCallback(progressCallback);
        makeRequestAsync(urlStr, options, futureReply);
    }

    /**
     * Makes a GET request to the specified URL and returns the response as a Java object.
     * Returns null if there was an error.
     * @param urlStr The URL to make the request to.
     * @param clazz The class of the object to return.
     * @param futureReply The response as a Java object of T, or null if there was an error.
     */

    @Deprecated
    public static <T> void getRequestAsync(String urlStr, Class<T> clazz, FutureReply<T> futureReply) {
        makeRequestAsync(urlStr, clazz, new HTTPRequestOptions<>(), futureReply);
    }

    @Deprecated
    public static <T> void getRequestAsync(String urlStr, Class<T> clazz, FutureReply<T> futureReply, HTTPProgressCallback progressCallback) {
        HTTPRequestOptions<T> options = new HTTPRequestOptions<>();
        options.setProgressCallback(progressCallback);
        makeRequestAsync(urlStr, clazz, options, futureReply);
    }

    /**
     * Makes a GET request to the specified URL and returns the response as a Java object.
     * @param urlStr The URL to make the request to.
     * @param clazz The class of the object to return.
     * @param defaultValue The default value to return if we get an error.
     * @param futureReply The response as a Java object of T, or defaultValue if there was an error.
     */

    @Deprecated
    public static <T> void getRequestAsync(String urlStr, Class<T> clazz, T defaultValue, FutureReply<T> futureReply) {
        HTTPRequestOptions<T> options = new HTTPRequestOptions<>();
        options.setDefaultValue(defaultValue);
        makeRequestAsync(urlStr, clazz, options, futureReply);
    }

    @Deprecated
    public static <T> void getRequestAsync(String urlStr, Class<T> clazz, T defaultValue, FutureReply<T> futureReply, HTTPProgressCallback progressCallback) {
        HTTPRequestOptions<T> options = new HTTPRequestOptions<>();
        options.setDefaultValue(defaultValue);
        options.setProgressCallback(progressCallback);
        makeRequestAsync(urlStr, clazz, options, futureReply);
    }

    /**
     * Makes a GET request to the specified URL and returns the response as a Java object.
     * Returns null if there was an error.
     * @param urlStr The URL to make the request to.
     * @param clazz The class of the object to return.
     * @return The response as a Java object of T, or null if there was an error.
     */

    @Deprecated
    public static <T> T getRequest(String urlStr, Class<T> clazz) {
        return makeRequest(urlStr, clazz, new HTTPRequestOptions<>());
    }

    @Deprecated
    public static <T> T getRequest(String urlStr, Class<T> clazz, HTTPProgressCallback progressCallback) {
        HTTPRequestOptions<T> options = new HTTPRequestOptions<>();
        options.setProgressCallback(progressCallback);
        return makeRequest(urlStr, clazz, options);
    }

    /**
     * Makes a GET request to the specified URL and returns the response as a Java object.
     * @param urlStr The URL to make the request to.
     * @param clazz The class of the object to return.
     * @param defaultValue The default value to return if we get an error.
     * @return The response as a Java object of T, or defaultValue if there was an error.
     */

    @Deprecated
    public static <T> T getRequest(String urlStr, Class<T> clazz, T defaultValue) {
        HTTPRequestOptions<T> options = new HTTPRequestOptions<>();
        options.setDefaultValue(defaultValue);
        return makeRequest(urlStr, clazz, options);
    }

    @Deprecated
    public static <T> T getRequest(String urlStr, Class<T> clazz, T defaultValue, HTTPProgressCallback progressCallback) {
        HTTPRequestOptions<T> options = new HTTPRequestOptions<>();
        options.setDefaultValue(defaultValue);
        options.setProgressCallback(progressCallback);
        return makeRequest(urlStr, clazz, options);
    }

    /**
     * Makes a POST request to the specified URL and returns the response as a JSON object.
     * @param urlStr The URL to make the request to.
     * @param data The data to send to the server.
     * @return The response as a JSON object.
     * {
     *     "success": true, //false if there was any error
     *     "httpStatusCode": 200, //The HTTP status code we successfully made the request to the server
     *     "error": "...", //Only present if success is false. Returns the error message from the catch statements
     *     "data": {...} //The data returned by the server if everything went well. Else its null.
     * }
     */

    @Deprecated
    public static ObjectNode sendPostRequest(String urlStr, ObjectNode data) {

        HTTPRequestOptions<ObjectNode> options = new HTTPRequestOptions<>();
        options.setRequestType(RequestType.POST);
        options.setPostData(data);
        return makeRequest(urlStr, ObjectNode.class, options);

    }

    /**
     * Makes a POST request to the specified URL and returns the response as a JSON object.
     * @param urlStr The URL to make the request to.
     * @param data The data to send to the server.
     * @param futureReply  The response as a JSON object.
     * {
     *     "success": true, //false if there was any error
     *     "httpStatusCode": 200, //The HTTP status code we successfully made the request to the server
     *     "error": "...", //Only present if success is false. Returns the error message from the catch statements
     *     "data": {...} //The data returned by the server if everything went well. Else its null.
     * }
     */

    @Deprecated
    public static void sendPostRequestAsync(String urlStr, ObjectNode data, FutureReply<ObjectNode> futureReply) {
        HTTPRequestOptions<ObjectNode> options = new HTTPRequestOptions<>();
        options.setRequestType(RequestType.POST);
        options.setPostData(data);
        makeRequestAsync(urlStr, options, futureReply);
    }


}
