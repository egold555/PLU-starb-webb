package webb.shared.dtos.puzzle.user.created;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public class CreateUserPuzzleDTO {
    @NotBlank(message = "The username must not be blank.")
    private String username;

    @PositiveOrZero(message = "The id cannot be negative.")
    private int levelId;

    /**
     * Creates a new CreateUserPuzzleLevelDTO object
     *
     * @param username the username of the associated user
     * @param levelId  the id of the puzzle
     */
    public CreateUserPuzzleDTO(@JsonProperty(value = "username") String username, @JsonProperty(value = "levelId") int levelId) {
        this.username = username;
        this.levelId = levelId;
    }

    /**
     * @return the username of the associated user
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username of the associated user
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return the id of the puzzle level
     */
    public int getLevelId() {
        return this.levelId;
    }

    /**
     * @return the id of the userPuzzle
     */
    public String getUserPuzzleId() {
        return String.format("%d-%s", this.levelId, this.username);
    }

    /**
     * @param levelId the id of the puzzle level
     */
    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }

    @Override
    public String toString() {
        return "CreateUserPuzzleLevelDTO{" +
                "username='" + username + '\'' +
                ", levelId=" + levelId +
                '}';
    }
}