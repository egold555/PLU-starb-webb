package webb.server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="leaderboards")
public class LeaderboardController {
    @GetMapping("users")
    public ResponseEntity<Void> getUserLeaderboard() {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}