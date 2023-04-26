package webb.shared.dtos.puzzle.user.created;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public class CreateOrDeleteUserPuzzleLevelDTO {


    @NotBlank(message = "The username must not be blank.")
    private String username;

    @PositiveOrZero(message = "The id cannot be negative.")
    private int levelId;

    public CreateOrDeleteUserPuzzleLevelDTO(@JsonProperty(value = "username") String username, @JsonProperty(value = "levelId") int levelId) {
        this.username = username;
        this.levelId = levelId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getLevelId() {
        return this.levelId;
    }

    public String getUserPuzzleId() {
        return String.format("%d-%s", this.levelId, this.username);
    }

    @Override
    public String toString() {
        return "CreateUserPuzzleLevelDTO{" +
                "username='" + username + '\'' +
                ", levelId=" + levelId +
                '}';
    }
}