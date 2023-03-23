package webb.server.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import webb.model.User;

import java.io.File;
import java.util.*;

public class MockUserDatabase {
    private final Map<String, User> users = new HashMap<>();
    private final String FP = "src/main/java/webb/server/repository/data/userdata.json";

    public MockUserDatabase() {
        ObjectMapper mapper = new ObjectMapper();

        File file = new File(FP);

        try {
            List<User> userList = Arrays.asList(mapper.readValue(file, User[].class));

            for(User user: userList) {
                users.put(user.getUsername(), user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User get(String username) {
        return users.get(username);
    }

    public List<User> getAll() {
        return new ArrayList<>(users.values());
    }

    public boolean exists(String username) {
        User user = users.get(username);

        return user != null;
    }
}
