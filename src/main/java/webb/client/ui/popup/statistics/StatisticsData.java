package webb.client.ui.popup.statistics;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StatisticsData {

    private final String currentTitle;
    private final int gamesCompleted;
    private final int gamesMax;

    private final long solveTimeMin;
    private final long solveTimeMax;
    private final long solveTimeAverage;

    /**
     * Creates a new popup with the given title.
     *
     * @param currentTitle Title of the player
     * @param gamesCompleted Number of games completed
     * @param gamesMax Number of games max
     * @param solveTimeMin Minimum time to solve a puzzle in milliseconds
     * @param solveTimeMax Maximum time to solve a puzzle in milliseconds
     * @param solveTimeAverage Average time to solve a puzzle in milliseconds
     *
     */
    public StatisticsData(@JsonProperty("currentTitle") String currentTitle,
                          @JsonProperty("gamesCompleted") int gamesCompleted,
                          @JsonProperty("gamesMax") int gamesMax,
                          @JsonProperty("solveTimeMin") long solveTimeMin,
                          @JsonProperty("solveTimeMax") long solveTimeMax,
                          @JsonProperty("solveTimeAverage") long solveTimeAverage) {
        this.currentTitle = currentTitle;
        this.gamesCompleted = gamesCompleted;
        this.gamesMax = gamesMax;
        this.solveTimeMin = solveTimeMin;
        this.solveTimeMax = solveTimeMax;
        this.solveTimeAverage = solveTimeAverage;
    }

    /**
     * @return the current title
     */
    public String getCurrentTitle() {return currentTitle;}

    /**
     * @return the games completed
     */
    public int getGamesCompleted() {return gamesCompleted;}

    /**
     * @return the amount of games played total
     */
    public int getGamesMax() {return gamesMax;}

    /**
     * @return the minimum time to solve a puzzle in milliseconds
     */
    public long getSolveTimeMin() {return solveTimeMin;}

    /**
     * @return the maximum time to solve a puzzle in milliseconds
     */
    public long getSolveTimeMax() {return solveTimeMax;}

    /**
     * @return the average time to solve a puzzle in milliseconds
     */
    public long getSolveTimeAverage() {return solveTimeAverage;}

}
