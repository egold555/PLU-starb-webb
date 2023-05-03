package starb.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import webb.client.ui.helpers.http.HTTPRequestOptions;
import webb.client.ui.helpers.http.WebbWebUtilities;

public class TestPostRequest {

    public static void main(String[] args) {

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode root = mapper.createObjectNode();
        root.put("string", "hello");
        root.put("int", 1);
        root.put("boolean", true);

        HTTPRequestOptions<ObjectNode> options = new HTTPRequestOptions<>();
        options.setPostData(root);

        WebbWebUtilities.makeRequestAsync(
                "testing/echo-as-json",
                options,
                (response) -> {
                    System.out.println(response.toPrettyString());
                }
        );
        
        ObjectNode resp = WebbWebUtilities.makeRequest("testing/echo-as-json", options);
        System.out.println(resp.toPrettyString());
    }

}
