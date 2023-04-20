package starb.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.File;
import java.util.Objects;

public class TempDataGenerator {

    // Generate the bulk of levels.json for me because I'm lazy
    public static void main(String[] args) {

        final File ROOT = new File("puzzles/");
        for(File file : Objects.requireNonNull(ROOT.listFiles())) {

            if(file.isDirectory()) {
                continue;
            }

            if(!file.getName().endsWith(".json")) {
                continue;
            }

            String fileName = file.getName();
            String puzzleName = fileName.substring(0, fileName.length() - 5);
            int stars = 2;
            boolean completed = false;

            ObjectMapper mapper = new ObjectMapper();
            ObjectNode node = mapper.createObjectNode();

            node.put("name", puzzleName);
            node.put("stars", stars);
            node.put("completed", completed);
            node.put("file", fileName);

            System.out.println(node.toString());
        }

    }

}
