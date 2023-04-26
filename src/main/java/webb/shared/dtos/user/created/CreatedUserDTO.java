package webb.shared.dtos.user.created;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.springframework.data.annotation.Id;

public class CreatedUserDTO {

    /**
     * @return the username of the user
     */
    @NotBlank(message = "Username is required.")
    @Id
    @Getter
    private final String username;

    /**
     * Creates a new user
     * @param username the username of the user
     */
    public CreatedUserDTO(@JsonProperty("username") String username) {
        this.username = username;
    }

}
