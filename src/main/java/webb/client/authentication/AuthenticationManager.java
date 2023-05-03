package webb.client.authentication;

import webb.client.ui.helpers.http.HTTPRequestOptions;
import webb.client.ui.helpers.http.WebbWebUtilities;
import webb.shared.dtos.user.UserDTO;

public class AuthenticationManager {

    private AuthenticationManager() {}

    private static AuthenticationManager instance;
    private UserDTO currentUser;

    public static AuthenticationManager getInstance() {
        if(instance == null) {
            instance = new AuthenticationManager();
        }
        return instance;
    }

    public boolean authenticate(String username) {

        HTTPRequestOptions<UserDTO> options = new HTTPRequestOptions<>();
        options.setAuthenticatedRequest(false);

        currentUser = WebbWebUtilities.makeRequest("users/" + username, UserDTO.class, options);

        return currentUser != null;
    }

    public UserDTO getCurrentUser() {
        return currentUser;
    }
}
