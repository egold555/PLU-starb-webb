package webb.shared.dtos.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;
import webb.shared.dtos.user.created.CreatedUserDTO;

@Document("user")
public class UserDTO extends CreatedUserDTO{

    @NotNull
    private final UserStatsDTO stats;

    /** Creates a new user
     * @param username the username of the user
     * @param stats the statistics of the user
     */
    public UserDTO(@JsonProperty("username") String username, @JsonProperty("stats") UserStatsDTO stats) {
        super(username);
        this.stats = stats;
    }

    /**
     * @return the statistics of the user
     */
    public UserStatsDTO getStats() {return stats;}
}
