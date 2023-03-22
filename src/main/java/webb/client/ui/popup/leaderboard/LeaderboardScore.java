package webb.client.ui.popup.leaderboard;

public class LeaderboardScore implements Comparable<LeaderboardScore> {

    private final String name;
    private final int score;

    public LeaderboardScore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    @Override
    public int compareTo(LeaderboardScore o) {
        // Sort from highest to lowest
        return Integer.compare(o.score, this.score);
    }
}
