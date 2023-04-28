package webb.server;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import webb.server.controller.LeaderboardController;
import webb.server.repository.UserRepository;
import webb.server.service.UserService;
import webb.shared.dtos.leaderboard.LeaderboardDTO;
import webb.shared.dtos.leaderboard.LeaderboardEntryDTO;
import webb.shared.dtos.user.UserDTO;
import webb.shared.dtos.user.UserStatsDTO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class LeaderboardControllerTest {
    private UserDTO user1;
    private UserDTO user2;
    private UserDTO user3;
    private UserDTO user4;
    private UserDTO user5;
    private LeaderboardEntryDTO user1lb;
    private LeaderboardEntryDTO user2lb;
    private LeaderboardEntryDTO user3lb;
    private LeaderboardEntryDTO user4lb;
    private LeaderboardEntryDTO user5lb;

    private UserStatsDTO user1Stats = new UserStatsDTO((long)100, (long)10, (long)50, 10, 5, "Cadet");
    private UserStatsDTO user2Stats = new UserStatsDTO((long)100, (long)10, (long)50, 20, 5, "Cadet");
    private UserStatsDTO user3Stats = new UserStatsDTO((long)100, (long)10, (long)50, 5, 5, "Cadet");
    private UserStatsDTO user4Stats = new UserStatsDTO((long)100, (long)10, (long)50, 45, 5, "Cadet");
    private UserStatsDTO user5Stats = new UserStatsDTO((long)100, (long)10, (long)50, 0, 5, "Cadet");

    @Autowired
    private UserRepository ur;
    @Autowired
    private UserService us;
    @Autowired
    private LeaderboardController lbc;

    @BeforeEach
    void setUp(){
        ur.deleteAll();
        user1 = ur.save(new UserDTO("User1", user1Stats));
        user2 = ur.save(new UserDTO("User2", user2Stats));
        user3 = ur.save(new UserDTO("User3", user3Stats));
        user4 = ur.save(new UserDTO("User4", user4Stats));
        user5 = ur.save(new UserDTO("User5", user5Stats));

        user1lb = new LeaderboardEntryDTO("User1", 10);
        user2lb = new LeaderboardEntryDTO("User2", 20);
        user3lb = new LeaderboardEntryDTO("User3", 5);
        user4lb = new LeaderboardEntryDTO("User4", 45);
        user5lb = new LeaderboardEntryDTO("User5", 0);
    }

    @Test
    void getUserLeaderboard() {
        LeaderboardDTO lb = lbc.getUserLeaderboard().getBody();
        List<LeaderboardEntryDTO> entries = lb.getData();
        assertEquals(entries.get(0), user4lb);
        assertEquals(entries.get(1), user2lb);
        assertEquals(entries.get(2), user1lb);
        assertEquals(entries.get(3), user3lb);
        assertEquals(entries.get(4), user5lb);
    }
}