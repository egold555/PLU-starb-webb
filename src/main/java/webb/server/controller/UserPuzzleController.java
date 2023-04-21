package webb.server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("puzzle")
public class UserPuzzleController {
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
