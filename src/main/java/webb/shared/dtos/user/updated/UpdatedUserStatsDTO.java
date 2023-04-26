package webb.shared.dtos.user.updated;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

public class UpdatedUserStatsDTO {

    /**
     * @return the puzzlesComplete
     */
    @NotBlank(message = "puzzlesComplete is required.")
    @Getter
    private final int puzzlesComplete;

    /**
     * Constructor for UpdatedUserStatsDTO
     * @param puzzlesComplete the number of puzzles the user has completed
     */
    public UpdatedUserStatsDTO(int puzzlesComplete) {
        this.puzzlesComplete = puzzlesComplete;
    }

}
