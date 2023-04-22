package webb.shared.dtos.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserStatsDTO {

    // In milliseconds
    private final long maxSolveTime;
    private final long minSolveTime;
    private final long avgSolveTime;
    private final int puzzlesComplete;

    private final int puzzlesUntilNextTitle;
    private final String currentTitle;

    /** Creates a new UserStats object
     * @param maxSolveTime the maximum solve time of the user in ms
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


}
