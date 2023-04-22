package webb.shared.dtos.user.created;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreatedUserDTO {

    private final String username;

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

}
