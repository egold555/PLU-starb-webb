package webb.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class JsonGeneratorTest {

    public static void main(String[] args) throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        ArrayNode puzzles = mapper.createArrayNode();

        int id = 0;
        for(File puzzleFile : new File("puzzles/").listFiles()) {

            if(puzzleFile.isDirectory() || !puzzleFile.getName().endsWith(".json")) {
                continue;
            }

            ObjectNode node = genPuzzleFile(mapper, puzzleFile, id);
            id++;
            puzzles.add(node);
        }

        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(puzzles);
        System.out.println(json);
    }

    static ObjectNode genPuzzleFile(ObjectMapper mapper, File file, int id) throws IOException {
        System.out.println("Generating puzzle file: " + file.getName());
        ObjectNode node = mapper.readValue(file, ObjectNode.class);

        final int solvedByNumPlayers = ThreadLocalRandom.current().nextInt(1, 1000);
        node.put("solvedByNumPlayers", solvedByNumPlayers);
        node.put("id", id);

        return node;
    }

}
