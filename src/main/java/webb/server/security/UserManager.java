package webb.server.security;

import webb.model.User;
import webb.server.repository.MockUserDatabase;

public class UserManager {
    private static final MockUserDatabase users = new MockUserDatabase();;

    private static User currentUser;

    public static boolean authenticate(User user)  {
        if(users.exists(user.getUsername())) {
            return false;
        }

        currentUser = user;
        System.out.println("Authenticated!");
        return true;
    }
}