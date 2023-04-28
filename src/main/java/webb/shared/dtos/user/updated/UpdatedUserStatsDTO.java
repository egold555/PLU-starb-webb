package webb.shared.dtos.user.updated;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@ToString
public class UpdatedUserStatsDTO {

    @PositiveOrZero(message = "puzzlesComplete must be greater than or equal to 0.")
    @Getter
    @Setter
    /**
     * @param puzzlesComplete the number of puzzles the user has completed
     * @return the number of puzzles the user has completed
     */
    private int puzzlesComplete;

    /**
     * Constructor for UpdatedUserStatsDTO
     * @param puzzlesComplete the number of puzzles the user has completed
     */
    public UpdatedUserStatsDTO(@JsonProperty(value = "puzzlesComplete" , required = true) int puzzlesComplete) {
        this.puzzlesComplete = puzzlesComplete;
    }

}
