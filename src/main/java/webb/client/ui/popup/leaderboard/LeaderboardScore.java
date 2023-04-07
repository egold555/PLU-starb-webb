package webb.client.ui.popup.leaderboard;

/**
 * Represents a score in the leaderboard.
 */
public class LeaderboardScore implements Comparable<LeaderboardScore> {

    private final String name;
    private final int score;
    private final long time;

    /**
     * Creates a new LeaderboardScore.
     *
     * @param name  The name of the player
     * @param score The score of the player
     * @param time The completion time of the player (in milliseconds)
     */
    public LeaderboardScore(String name, int score, long time) {
        this.name = name;
        this.score = score;
        this.time = time;
    }

    /**
     * Gets the name of the player.
     *
     * @return The name of the player
     */
    public String getName() {return name;}

    /**
     * Gets the score of the player.
     *
     * @return The score of the player
     */
    public int getScore() {return score;}

    /**
     * Gets the completion time of the player.
     *
     * @return The completion time of the player (in milliseconds)
     */
    public long getTime() {return time;}

    /**
     * Compares this LeaderboardScore to another LeaderboardScore.
     * This sorts from highest to lowest.
     * If scores are equal, it sorts the time from highest to lowest.
     * If some miracle happens and scores and times are equal, it sorts by name from A to Z.
     *
     * @param o The other LeaderboardScore
     * @return 1 if this LeaderboardScore is greater than the other LeaderboardScore, -1 if this LeaderboardScore is less than the other LeaderboardScore, and 0 if they are equal
     */
    @Override
    public int compareTo(LeaderboardScore o) {

        // Sort by score, High to Low
        final int scoreCompare = Integer.compare(o.score, this.score);
        if(scoreCompare != 0) return scoreCompare;

        // If scores are equal, sort by time, Low to High
        final int timeCompare = Long.compare(this.time, o.time);
        if(timeCompare != 0) return timeCompare;

        // If scores and times are equal, sort by name, A to Z
        return this.name.compareTo(o.name);
    }
}
