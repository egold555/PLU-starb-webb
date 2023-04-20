package webb.client.ui.screens.selectpuzzle;

import com.fasterxml.jackson.annotation.JsonProperty;

/*
Represents a level on the pick a level screen
 */
public class Level {

    private final String name;
    private final int stars;
    private boolean completed;
    private final String file;

    public Level(@JsonProperty("name") String name, @JsonProperty("stars") int stars, @JsonProperty("completed") boolean completed, @JsonProperty("file") String file) {
        this.name = name;
        this.stars = stars;
        this.completed = completed;
        this.file = file;
    }

    /**
     * @return the name of the level to be displayed
     */
    public String getName() {return name;}

    /**
     * @return the number of stars the level has
     */
    public int getStars() {return stars;}

    /**
     * @return whether or not the level is completed
     */
    public boolean isCompleted() {return completed;}

    /**
     * Sets whether or not the level is completed
     * @param completed True if completed, false otherwise
     */
    public void setCompleted(boolean completed) {this.completed = completed;}

    /**
     * @return the file name of the level on the server
     */
    public String getFile() {return file;}

}
