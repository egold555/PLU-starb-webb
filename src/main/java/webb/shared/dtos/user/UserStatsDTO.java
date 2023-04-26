package webb.shared.dtos.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserStatsDTO {

    // In milliseconds
    @NotNull
    private long maxSolveTime;
    @NotNull
    private long minSolveTime;
    @NotNull
    private long avgSolveTime;
    @NotNull
    private int puzzlesComplete;

    @NotNull
    private int puzzlesUntilNextTitle;
    @NotBlank
    private String currentTitle;

    /** Creates a new UserStats object
     * @param maxSolveTime the maximum solve time of the usser in ms
     * @param minSolveTime the minimum solve time of the user in ms
     * @param avgSolveTime the average solve time of the user in ms
     * @param puzzlesComplete the number of puzzles completed by the user
     * @param puzzlesUntilNextTitle the number of puzzles until the user gets the next title
     * @param currentTitle the current title of the user
     *
     */
    public UserStatsDTO(
            @JsonProperty("maxSolveTime") long maxSolveTime,
            @JsonProperty("minSolveTime") long minSolveTime,
            @JsonProperty("avgSolveTime") long avgSolveTime,
            @JsonProperty("puzzlesComplete") int puzzlesComplete,
            @JsonProperty("puzzlesUntilNextTitle") int puzzlesUntilNextTitle,
            @JsonProperty("currentTitle") String currentTitle) {
        this.maxSolveTime = maxSolveTime;
        this.minSolveTime = minSolveTime;
        this.avgSolveTime = avgSolveTime;
        this.puzzlesComplete = puzzlesComplete;
        this.puzzlesUntilNextTitle = puzzlesUntilNextTitle;
        this.currentTitle = currentTitle;
    }

    public UserStatsDTO() {
        this.maxSolveTime = 0;
        this.minSolveTime = 0;
        this.avgSolveTime = 0;
        this.puzzlesComplete = 0;
        this.puzzlesUntilNextTitle = 0;
        this.currentTitle = "";
    }

    /**
     * @return the maximum solve time of the user in ms
     */
    public long getMaxSolveTime() {return maxSolveTime;}

    /**
     * @return the minimum solve time of the user in ms
     */
    public long getMinSolveTime() {return minSolveTime;}

    /**
     * @return the average solve time of the user in ms
     */
    public long getAvgSolveTime() {return avgSolveTime;}

    /**
     * @return the number of puzzles completed by the user
     */
    public int getPuzzlesComplete() {return puzzlesComplete;}

    /**
     * @return the number of puzzles until the user gets the next title
     */
    public int getPuzzlesUntilNextTitle() {return puzzlesUntilNextTitle;}

    /**
     * @return the current title of the user
     */
    public String getCurrentTitle() {return currentTitle;}

    public void setMaxSolveTime(long maxSolveTime) {
        this.maxSolveTime = maxSolveTime;
    }

    public void setMinSolveTime(long minSolveTime) {
        this.minSolveTime = minSolveTime;
    }

    public void setAvgSolveTime(long avgSolveTime) {
        this.avgSolveTime = avgSolveTime;
    }

    public void setPuzzlesComplete(int puzzlesComplete) {
        this.puzzlesComplete = puzzlesComplete;
    }

    public void setPuzzlesUntilNextTitle(int puzzlesUntilNextTitle) {
        this.puzzlesUntilNextTitle = puzzlesUntilNextTitle;
    }

    public void setCurrentTitle(String currentTitle) {
        this.currentTitle = currentTitle;
    }
}
