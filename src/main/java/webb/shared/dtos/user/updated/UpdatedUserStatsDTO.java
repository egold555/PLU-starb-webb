package webb.shared.dtos.user.updated;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;


public class UpdatedUserStatsDTO {

    @PositiveOrZero(message = "puzzlesComplete must be greater than or equal to 0.")
    private int puzzlesComplete;

    /**
     * Constructor for UpdatedUserStatsDTO
     * @param puzzlesComplete the number of puzzles the user has completed
     */
    public UpdatedUserStatsDTO(@JsonProperty(value = "puzzlesComplete" , required = true) int puzzlesComplete) {
        this.puzzlesComplete = puzzlesComplete;
    }

    /**
     * @return the puzzlesComplete
     */
    public int getPuzzlesComplete() {return puzzlesComplete;}

    public void setPuzzlesComplete(int puzzlesComplete) {
        this.puzzlesComplete = puzzlesComplete;
    }
}
