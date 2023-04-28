package webb.shared.dtos.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class UserStatsDTO {

    /**
     * @param maxSolveTime the maximum solve time of the user in ms
     * @return the maximum solve time of the user in ms
     */
    @NotNull
    private long maxSolveTime;


    /**
     * @param minSolveTime the minimum solve time of the user in ms
     * @return the minimum solve time of the user in ms
     */
    @NotNull
    private long minSolveTime;


    /**
     * @param avgSolveTime the average solve time of the user in ms
     * @return the average solve time of the user in ms
     */
    @NotNull
    private long avgSolveTime;


    /**
     * @param puzzlesComplete the number of puzzles completed by the user
     * @return the number of puzzles completed by the user
     */
    @NotNull
    private int puzzlesComplete;


    /**
     * @param puzzlesUntilNextTitle the number of puzzles until the user gets the next title
     * @return the number of puzzles until the user gets the next title
     */
    @NotNull
    private int puzzlesUntilNextTitle;


    /**
     * @param currentTitle the current title of the user
     * @return the current title of the user
     */
    @NotBlank
    private String currentTitle;

    /** Creates a new UserStats object
     * @param maxSolveTime the maximum solve time of the usser in ms
     * @param minSolveTime the minimum solve time of the user in ms
     * @param avgSolveTime the average solve time of the user in ms
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

    public float getTimeInSeconds(long time) {
        return (float) time / 1000;
    }
}
