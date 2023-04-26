package webb.shared.dtos.user.created;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;

public class CreatedUserDTO {

    @NotBlank(message = "Username is required and must not be blank")
    @Id
    private String username;

    /**
     * Creates a new user
     * @param username the username of the user
     */
    public CreatedUserDTO(@JsonProperty("username") String username) {
        this.username = username;
    }

    /**
     * @return the username of the user
     */
    public String getUsername() {return username;}

    /**
     * This is necessary for spring boot to initialize class using setters
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }
}
