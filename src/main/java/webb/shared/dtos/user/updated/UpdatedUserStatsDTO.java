package webb.shared.dtos.user.updated;

import jakarta.validation.constraints.NotBlank;

public class UpdatedUserStatsDTO {

    @NotBlank(message = "puzzlesComplete is required.")
    private final int puzzlesComplete;

    /**
     * Constructor for UpdatedUserStatsDTO
     * @param puzzlesComplete the number of puzzles the user has completed
     */
    public UpdatedUserStatsDTO(int puzzlesComplete) {
        this.puzzlesComplete = puzzlesComplete;
    }

    /**
     * @return the puzzlesComplete
     */
    public int getPuzzlesComplete() {return puzzlesComplete;}
}
