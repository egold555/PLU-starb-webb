package webb.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webb.server.repository.UserPuzzleRepository;
import org.springframework.data.mongodb.core.query.*;

@RestController
@RequestMapping("puzzle")
public class UserPuzzleController {

    private final UserPuzzleRepository userPuzzleRepo;
    @Autowired
    public UserPuzzleController(UserPuzzleRepository repo){this.userPuzzleRepo = repo;}

    @GetMapping("{username}")
    public ResponseEntity<Void> getUserPuzzleLevels(@PathVariable String username) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @GetMapping("{id}/{username}")
    public ResponseEntity<Void> getUserPuzzleLevel(@PathVariable Integer id, @PathVariable String username) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @PutMapping("/{id}/{username}")
    public ResponseEntity<Void> updateUserPuzzleLevel(@PathVariable Integer id, @PathVariable String username) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
