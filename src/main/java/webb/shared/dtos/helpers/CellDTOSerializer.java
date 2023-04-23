package webb.shared.dtos.helpers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import webb.shared.dtos.puzzle.CellDTO;

import java.io.IOException;

public class CellDTOSerializer extends JsonSerializer<CellDTO> {
    @Override
    public void serialize(CellDTO cellDTO, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeStartArray();
        jsonGenerator.writeNumber(cellDTO.getRow());
        jsonGenerator.writeNumber(cellDTO.getCol());
        jsonGenerator.writeEndArray();
    }
}
