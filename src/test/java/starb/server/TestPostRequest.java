package starb.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import webb.client.ui.helpers.WebbWebUtilities;

public class TestPostRequest {

    public static void main(String[] args) {

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode root = mapper.createObjectNode();
        root.put("string", "hello");
        root.put("int", 1);
        root.put("boolean", true);

//        WebbWebUtilities.sendPostRequestAsync("testing/echo-as-json", root, (response) -> {
//            System.out.println(response.toPrettyString());
//        });

        ObjectNode resp = WebbWebUtilities.sendPostRequest("testing/echo-as-json", root);
        System.out.println(resp.toPrettyString());
    }

}
