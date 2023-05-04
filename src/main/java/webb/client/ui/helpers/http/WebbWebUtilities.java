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
import webb.client.authentication.AuthenticationManager;
import webb.client.ui.WebbWindow;

public class WebbWebUtilities {

    //Do not instantiate
    private WebbWebUtilities() {}

    //TODO: Change me
    //private static final String BASE_URL = "https://cs390.golde.org/api-mockup/v1/";
    private static final String BASE_URL = "http://10.0.0.23:1337/";

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
                //TODO: Handle this better with a popup or something.
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

            if(options.isDebug()) {
                System.out.println("Making " + options.getRequestType() + " to " + url.toString());
                System.out.println("  ----- Request -----");
            }

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(options.getRequestType().name());

            if(options.getRequestType() != RequestType.GET && options.getPostData() != null) {
                final String body = options.getPostData().toString();

                // Fix for some servers interpreting the request as a GET request if the URL doesn't end with a slash.
                // This took me an hour to figure out. I hate this.
                if(!urlStr.endsWith("/") && !urlStr.endsWith("?")) {
                    urlStr += "/";
                }

                if(options.isDebug()) {
                    System.out.println("  Request Body: " + body);
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

            if(options.isAuthenticatedRequest()) {

                String auth = options.getOverrideAuth() != null ? options.getOverrideAuth() : AuthenticationManager.getInstance().getCurrentUser().getUsername();
                con.setRequestProperty("Authorization", auth);

                if(options.isDebug()) {
                    System.out.println("  Auth: " + auth);
                }
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

            if(options.isDebug()) {
                System.out.println();
                System.out.println(" ----- Response -----");
            }

            final int httpStatusCode = con.getResponseCode();

            if(options.isDebug()) {
                System.out.println("  Bytes Read: " + bytesReadTotal);
                System.out.println("  Response Code: " + httpStatusCode);
                System.out.println("  Response Body: " + content.toString());
            }

            // sometimes we don't math correctly even though we read the entire file
            if (options.getProgressCallback() != null) {
                options.getProgressCallback().onProgressUpdate(1.0f);
            }

            try {
                JsonNode jsonNode = mapper.readTree(content.toString());
                rootNode.put("success", true);
                rootNode.put("httpStatusCode", httpStatusCode);
                rootNode.put("dataSizeBytes", bytesReadTotal);
                rootNode.set("data", jsonNode);

                if(options.isDebug()) {
                    System.out.println("  Successfully Parsed JSON: " + jsonNode.toString());
                }
            }
            catch (Exception e) {
                handleError(e);
                rootNode.put("success", false);
                rootNode.put("httpStatusCode", httpStatusCode);
                rootNode.put("dataSizeBytes", bytesReadTotal);
                rootNode.put("error", e.getMessage());

                if(options.isDebug()) {
                    System.out.println("  Failed to parse JSON: " + e.getMessage());
                }
            }

            if(options.isDebug()) {
                System.out.println("---------- END REQUEST ----------");
                System.out.println();
            }
        }
        catch (IOException e) {
            handleError(e);
            rootNode.put("success", false);
            rootNode.put("error", e.getMessage());
        }

        return rootNode;
    }

    private static void handleError(Exception e) {
        WebbWindow.getInstance().handleError(e);
    }

}
