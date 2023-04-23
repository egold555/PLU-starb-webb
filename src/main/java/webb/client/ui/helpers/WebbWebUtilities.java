package webb.client.ui.helpers;

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

    public static ObjectNode getRequest(String urlStr) {
        return getRequest(urlStr, (HTTPProgressCallback) null);
    }

    public static ObjectNode getRequest(String urlStr, HTTPProgressCallback progressCallback) {

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode rootNode = mapper.createObjectNode();

        try {
            URL url = new URL(BASE_URL + urlStr);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int totalBytes = con.getContentLength();

            BufferedReader inputReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            //read 1024 bytes at a time
            char[] buffer = new char[1024];
            int bytesRead = 0;
            int bytesReadTotal = 0;
            StringBuilder content = new StringBuilder();

            while((bytesRead = inputReader.read(buffer)) != -1) {
                content.append(buffer, 0, bytesRead);
                bytesReadTotal += bytesRead;
                if (progressCallback != null) {
                    float percentComplete = (float) bytesReadTotal / totalBytes;
                    progressCallback.onProgressUpdate(percentComplete);
                }
            }

//            String inputLine;
//            StringBuilder content = new StringBuilder();
//            int bytesRead = 0;
//            while ((inputLine = inputReader.readLine()) != null) {
//                content.append(inputLine);
//                if (progressCallback != null) {
//                    bytesRead += inputLine.getBytes(StandardCharsets.UTF_8).length;
//                    float percentComplete = (float) bytesRead / totalBytes;
//                    progressCallback.onProgressUpdate(percentComplete);
//                }
//            }

            inputReader.close();

            final int httpStatusCode = con.getResponseCode();

            if (progressCallback != null) {
               // progressCallback.onProgressUpdate(1.0f);
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
     * @param futureReply  The response as a JSON object.
     * {
     *     "success": true, //false if there was any error
     *     "httpStatusCode": 200, //The HTTP status code we successfully made the request to the server
     *     "error": "...", //Only present if success is false. Returns the error message from the catch statements
     *     "data": {...} //The data returned by the server if everything went well. Else its null.
     * }
     */
    public static void getRequestAsync(String urlStr, FutureReply<ObjectNode> futureReply) {
        getRequestAsync(urlStr, futureReply, (HTTPProgressCallback) null);
    }
    public static void getRequestAsync(String urlStr, FutureReply<ObjectNode> futureReply, HTTPProgressCallback progressCallback) {
        THREAD_POOL.submit(() -> {
            ObjectNode node = getRequest(urlStr, progressCallback);
            futureReply.reply(node);
        });
    }

    /**
     * Makes a GET request to the specified URL and returns the response as a Java object.
     * Returns null if there was an error.
     * @param urlStr The URL to make the request to.
     * @param clazz The class of the object to return.
     * @param futureReply The response as a Java object of T, or null if there was an error.
     */
    public static <T> void getRequestAsync(String urlStr, Class<T> clazz, FutureReply<T> futureReply) {
        getRequestAsync(urlStr, clazz, null, futureReply, null);
    }
    public static <T> void getRequestAsync(String urlStr, Class<T> clazz, FutureReply<T> futureReply, HTTPProgressCallback progressCallback) {
        getRequestAsync(urlStr, clazz, null, futureReply, progressCallback);
    }

    /**
     * Makes a GET request to the specified URL and returns the response as a Java object.
     * @param urlStr The URL to make the request to.
     * @param clazz The class of the object to return.
     * @param defaultValue The default value to return if we get an error.
     * @param futureReply The response as a Java object of T, or defaultValue if there was an error.
     */
    public static <T> void getRequestAsync(String urlStr, Class<T> clazz, T defaultValue, FutureReply<T> futureReply) {
        getRequestAsync(urlStr, clazz, defaultValue, futureReply, (HTTPProgressCallback) null);
    }
    public static <T> void getRequestAsync(String urlStr, Class<T> clazz, T defaultValue, FutureReply<T> futureReply, HTTPProgressCallback progressCallback) {
        getRequestAsync(urlStr, (node) -> {
            if (node.get("success").asBoolean()) {
                try {
                    futureReply.reply(DEFAULT_JSON_CODEC.treeToValue(node.get("data"), clazz));
                }
                catch (Exception e) {
                    e.printStackTrace();
                    futureReply.reply(defaultValue);
                }
            }
            else {
                futureReply.reply(defaultValue);
            }
        }, progressCallback);
    }

    /**
     * Makes a GET request to the specified URL and returns the response as a Java object.
     * Returns null if there was an error.
     * @param urlStr The URL to make the request to.
     * @param clazz The class of the object to return.
     * @return The response as a Java object of T, or null if there was an error.
     */
    public static <T> T getRequest(String urlStr, Class<T> clazz) {
        return getRequest(urlStr, clazz, (HTTPProgressCallback) null);
    }
    public static <T> T getRequest(String urlStr, Class<T> clazz, HTTPProgressCallback progressCallback) {
        return getRequest(urlStr, clazz, null, progressCallback);
    }

    /**
     * Makes a GET request to the specified URL and returns the response as a Java object.
     * @param urlStr The URL to make the request to.
     * @param clazz The class of the object to return.
     * @param defaultValue The default value to return if we get an error.
     * @return The response as a Java object of T, or defaultValue if there was an error.
     */
    public static <T> T getRequest(String urlStr, Class<T> clazz, T defaultValue) {
        return getRequest(urlStr, clazz, defaultValue, null);
    }
    public static <T> T getRequest(String urlStr, Class<T> clazz, T defaultValue, HTTPProgressCallback progressCallback) {
        ObjectNode node = getRequest(urlStr, progressCallback);
        if (node.get("success").asBoolean()) {
            try {
                return DEFAULT_JSON_CODEC.treeToValue(node.get("data"), clazz);
            }
            catch (Exception e) {
                e.printStackTrace();
                return defaultValue;
            }
        }
        else {
            return defaultValue;
        }
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
    public static ObjectNode sendPostRequest(String urlStr, ObjectNode data) {

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode rootNode = mapper.createObjectNode();

        try {

            final String body = data.toString();

            // Fox for some servers interpreting the request as a GET request if the URL doesn't end with a slash.
            // This took me an hour to figure out. I hate this.
            if(!urlStr.endsWith("/") && !urlStr.endsWith("?")) {
                urlStr += "/";
            }

            URL url = new URL(BASE_URL + urlStr);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("POST");
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
            con.connect();

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));

            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }

            final int httpStatusCode = con.getResponseCode();

            if(response.toString().isEmpty() || httpStatusCode == 204) {
                rootNode.put("success", true);
                rootNode.put("httpStatusCode", httpStatusCode);
                return rootNode;
            }

            try {
                JsonNode jsonNode = mapper.readTree(response.toString());
                rootNode.put("success", true);
                rootNode.put("httpStatusCode", httpStatusCode);
                rootNode.set("data", jsonNode);
            }
            catch (Exception e) {
                rootNode.put("success", false);
                rootNode.put("httpStatusCode", httpStatusCode);
                rootNode.put("error", e.getMessage());
            }
        }
        catch (IOException e) {
            rootNode.put("success", false);
            rootNode.put("error", e.getMessage());
        }

        return rootNode;
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
    public static void sendPostRequestAsync(String urlStr, ObjectNode data, FutureReply<ObjectNode> futureReply) {
        THREAD_POOL.submit(() -> {
            ObjectNode node = sendPostRequest(urlStr, data);
            futureReply.reply(node);
        });
    }


}
