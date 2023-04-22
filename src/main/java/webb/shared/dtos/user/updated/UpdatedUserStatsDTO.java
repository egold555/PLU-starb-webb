package webb.shared.dtos.user.updated;

public class UpdatedUserStatsDTO {

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
