package webb.shared.dtos.user.created;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@ToString
public class CreatedUserDTO {

    @NotBlank(message = "Username is required and must not be blank")
    @Id
    @Getter
    @Setter
    /**
     * @param username the username of the user
     * @return the username of the user
     */
    private String username;

    /**
     * Creates a new user
     * @param username the username of the user
     */
    public CreatedUserDTO(@JsonProperty("username") String username) {
        this.username = username;
    }

}
