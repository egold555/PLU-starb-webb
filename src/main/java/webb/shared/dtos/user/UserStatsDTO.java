package webb.shared.dtos.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserStatsDTO {

    // In milliseconds
    private long maxSolveTime;
    private long minSolveTime;
    private long avgSolveTime;

    private int puzzlesComplete;

    /** Creates a new UserStats object
     * @param maxSolveTime the maximum solve time of the user in ms
     * @param minSolveTime the minimum solve time of the user in ms
     * @param avgSolveTime the average solve time of the user in ms
     * @param puzzlesComplete the number of puzzles completed by the user
     */
    public UserStatsDTO(
            @JsonProperty("maxSolveTime") long maxSolveTime,
            @JsonProperty("minSolveTime") long minSolveTime,
            @JsonProperty("avgSolveTime") long avgSolveTime,
            @JsonProperty("puzzlesComplete") int puzzlesComplete) {
        this.maxSolveTime = maxSolveTime;
        this.minSolveTime = minSolveTime;
        this.avgSolveTime = avgSolveTime;
        this.puzzlesComplete = puzzlesComplete;
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


}
