package webb.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import webb.server.repository.UserRepository;
import webb.shared.dtos.leaderboard.LeaderboardDTO;
import webb.shared.dtos.leaderboard.LeaderboardEntryDTO;
import webb.shared.dtos.user.UserDTO;
import webb.shared.dtos.user.UserDTOComparator;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="leaderboards")
public class LeaderboardController {
    /**
     * Repository object for managing User entities.
     */
    private final UserRepository userRepo;

    /**
     * Constructor for UserController class.
     * @param userRepo Repository object for managing User entities.
     */
    @Autowired
    public LeaderboardController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("users")
    public ResponseEntity<LeaderboardDTO> getUserLeaderboard() {
        List<UserDTO> users = userRepo.findAll();
        users.sort(new UserDTOComparator());
        List<LeaderboardEntryDTO> entries = users.stream()
                .map(user -> new LeaderboardEntryDTO(user.getUsername(), user.getStats().getPuzzlesComplete()))
                .toList();
        return new ResponseEntity<>(new LeaderboardDTO(entries), HttpStatus.OK);
    }
}