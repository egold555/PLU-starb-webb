package webb.shared.dtos.helpers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import webb.shared.dtos.puzzle.CellDTO;

import java.io.IOException;

public class CellDTODeserializer extends JsonDeserializer<CellDTO> {
    @Override
    public CellDTO deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        int row = node.get(0).asInt();
        int col = node.get(1).asInt();
        return new CellDTO(new int[] {row, col});
    }
}
