package webb.shared.dtos.puzzle.user.created;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CreateUserPuzzleDTO {

    /**
     * @param username the username of the associated user
     * @return the username of the associated user
     */
    @NotBlank(message = "The username must not be blank.")
    private String username;

    /**
     * @param levelId the id of the puzzle level
     * @return the id of the puzzle level
     */
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
     * @return the id of the userPuzzle
     */
    public String getUserPuzzleId() {
        return String.format("%d-%s", this.levelId, this.username);
    }


}