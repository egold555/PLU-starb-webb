package webb.server;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import webb.server.controller.LeaderboardController;
import webb.server.repository.UserRepository;
import webb.server.service.UserService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class LeaderboardControllerTest {

    @Autowired
    private UserRepository ur;
    @Autowired
    private UserService us;
    @Autowired
    private LeaderboardController lbc;
    @Test
    void getUserLeaderboard() {
    }
}