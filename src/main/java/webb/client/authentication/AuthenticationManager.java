package webb.client.authentication;

import webb.client.ui.helpers.http.HTTPRequestOptions;
import webb.client.ui.helpers.http.WebbWebUtilities;
import webb.shared.dtos.user.UserDTO;

public class AuthenticationManager {

    private AuthenticationManager() {}

    private static AuthenticationManager instance;
    private UserDTO currentUser;

    private static boolean FORCE_AUTHENTICATE = false;

    public static AuthenticationManager getInstance() {
        if(instance == null) {
            instance = new AuthenticationManager();
        }
        return instance;
    }

    public boolean authenticate(String username) {

        if(FORCE_AUTHENTICATE) {
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println("!!! FORCEFULLY AUTHENTICATING WITHOUT SERVER. EXPECT ERRORS. !!!");
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            currentUser = new UserDTO("FORCE-" + username);
            return true;
        }

        HTTPRequestOptions<UserDTO> options = new HTTPRequestOptions<>();
        options.setOverrideAuth(username);
        options.setDebug(true);

        currentUser = WebbWebUtilities.makeRequest("users/" + username, UserDTO.class, options);

        return currentUser != null;
    }

    public UserDTO getCurrentUser() {
        return currentUser;
    }
}
