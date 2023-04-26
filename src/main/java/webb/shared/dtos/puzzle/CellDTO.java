package webb.shared.dtos.puzzle;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import webb.shared.dtos.helpers.CellDTODeserializer;
import webb.shared.dtos.helpers.CellDTOSerializer;

/**
 * Represents a cell from the JSON file.
 * https://cs.plu.edu/courses/protected/cs390/2023s/project/iteration2.html
 */
@JsonDeserialize(using = CellDTODeserializer.class)
@JsonSerialize(using = CellDTOSerializer.class)
@ToString
@EqualsAndHashCode
public class CellDTO {

    /**
     * @return the row of the cell
     */
    @NotNull
    @Getter
    private final int row;


    /**
     * @return the column of the cell
     */
    @NotNull
    @Getter
    private final int col;

    @JsonCreator
    public CellDTO(int[] values) {
        row = values[0];
        col = values[1];
    }
}
