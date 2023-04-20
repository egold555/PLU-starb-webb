package webb.client.ui.helpers;

import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.bson.json.JsonObject;

public class WebbWebUtilities {

    //Do not instantiate
    private WebbWebUtilities() {}

    //TODO: Change me
    private static final String BASE_URL = "https://cs390.golde.org/json-api-mockup/";

    //Define the codec for the ObjectMapper to parse.
    //Make sure if there is no empty constructor for the class
    //you can use the @JsonCreator annotation for every variable in the constructor.
    private static final ObjectCodec codec = new ObjectMapper().getFactory().getCodec();

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

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode rootNode = mapper.createObjectNode();

        try {
            URL url = new URL(BASE_URL + urlStr);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader inputReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = inputReader.readLine()) != null) {
                content.append(inputLine);
            }
            inputReader.close();

            final int httpStatusCode = con.getResponseCode();

            try {
                JsonNode jsonNode = mapper.readTree(content.toString());
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
            e.printStackTrace();
            rootNode.put("success", false);
            rootNode.put("error", e.getMessage());
        }

        return rootNode;
    }

    /**
     * Makes a GET request to the specified URL and returns the response as a Java object.
     * Returns null if there was an error.
     * @param urlStr The URL to make the request to.
     * @param clazz The class of the object to return.
     * @return The response as a Java object of T, or null if there was an error.
     */
    public static <T> T getRequest(String urlStr, Class<T> clazz) {
        return getRequest(urlStr, clazz, null);
    }

    /**
     * Makes a GET request to the specified URL and returns the response as a Java object.
     * @param urlStr The URL to make the request to.
     * @param clazz The class of the object to return.
     * @param defaultValue The default value to return if we get an error.
     * @return The response as a Java object of T, or defaultValue if there was an error.
     */
    public static <T> T getRequest(String urlStr, Class<T> clazz, T defaultValue) {
        ObjectNode node = getRequest(urlStr);

        if (node.get("success").asBoolean()) {
            try {
                return node.get("data").traverse(codec).readValueAs(clazz);
            }
            catch (IOException e) {
                e.printStackTrace();
                return defaultValue;
            }
        }
        return defaultValue;
    }

}
