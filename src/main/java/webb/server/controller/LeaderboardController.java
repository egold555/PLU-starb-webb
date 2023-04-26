package webb.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import webb.server.repository.UserRepository;
import webb.server.service.UserService;
import webb.shared.dtos.leaderboard.LeaderboardDTO;

import java.security.Principal;

@RestController
@RequestMapping(path="leaderboards")
@CrossOrigin("*")
public class LeaderboardController {
    /**
     * service for managing users
     */
    private final UserService userService;

    /**
     * Constructor for UserController class.
     *
     * @param userService
     */
    @Autowired
    public LeaderboardController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Endpoint for retrieving all User entities.
     * @return List of all User entities
     */
    @GetMapping("users")
    public ResponseEntity<LeaderboardDTO> getUserLeaderboard() {
        return new ResponseEntity<>(userService.getUserLeaderboard(), HttpStatus.OK);
    }
}