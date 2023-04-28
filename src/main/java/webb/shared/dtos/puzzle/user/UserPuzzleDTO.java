package webb.shared.dtos.puzzle.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import webb.shared.dtos.puzzle.CellDTO;
import webb.shared.dtos.puzzle.user.update.UpdateUserPuzzleDTO;

@Document("user-puzzle")
@ToString
@Getter
@Setter
@NoArgsConstructor
public class UserPuzzleDTO extends UpdateUserPuzzleDTO {

    /**
     * @param levelId the id of the puzzle
     * @return the id of the puzzle
     */
    @PositiveOrZero(message = "The id cannot be negative.")
    private int levelId;

    /**
     * @param user the username of the user
     * @return the username of the user
     */
    @NotBlank(message = "The username must not be blank.")
    private String username;

    /**
     * @param id the id of the userpuzzle
     * @return the id of the puzzle
     */
    @Id
    private String id;

    /**
     * Creates a new UpdateUserPuzzleDTO object
     *
     * @param levelId        the id of the puzzle
     * @param user           the username of the associated user
     * @param completed      whether the puzzle has been completed
     * @param solveTime      the time it took to solve the puzzle in ms
     * @param placedMarkers  the list of markers placed on the puzzle
     * @param placedStars    the list of stars placed on the puzzle
     * @param starsRemaining the number of stars remaining on the puzzle
     */
    public UserPuzzleDTO(@JsonProperty("levelId") int levelId,
                         @JsonProperty("username") String user,
                         @JsonProperty("completed") boolean completed,
                         @JsonProperty("solveTime") long solveTime,
                         @JsonProperty("placedMarkers") List<CellDTO> placedMarkers,
                         @JsonProperty("placedStars") List<CellDTO> placedStars,
                         @JsonProperty("starsRemaining") int starsRemaining) {
        super(completed, solveTime, placedMarkers, placedStars, starsRemaining);
        this.levelId = levelId;
        this.username = user;
        this.id = generateId();
    }

    /**
     * Creates an empty UserPuzzleObject
     *
     * @param id       the id of the puzzle
     * @param username the username of the associated user
     */
    public UserPuzzleDTO(int id, String username) {
        // TODO: These must actually be populated with the correct values later on when they start playing the level
        super(false, 0, null, null, -1);
        this.levelId = id;
        this.username = username;
        this.id = generateId();
    }

    /**
     * @return the id of the puzzle
     */
    private String generateId() { return String.format("%d-%s", this.levelId, this.username); }


}
