package webb.client.ui.popup.leaderboard;

/**
 * Represents a score in the leaderboard.
 */
public class LeaderboardScore implements Comparable<LeaderboardScore> {

    private final String name;
    private final int score;

    /**
     * Creates a new LeaderboardScore.
     *
     * @param name  The name of the player
     * @param score The score of the player
     */
    public LeaderboardScore(String name, int score) {
        this.name = name;
        this.score = score;
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
     * Compares this LeaderboardScore to another LeaderboardScore.
     * This sorts from highest to lowest.
     *
     * @param o The other LeaderboardScore
     * @return 1 if this LeaderboardScore is greater than the other LeaderboardScore, -1 if this LeaderboardScore is less than the other LeaderboardScore, and 0 if they are equal
     */
    @Override
    public int compareTo(LeaderboardScore o) {
        return Integer.compare(o.score, this.score);
    }
}
