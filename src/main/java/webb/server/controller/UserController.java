package webb.server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {
    @GetMapping("{username}")
    public ResponseEntity<Void> getPlayer(@PathVariable String username) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @PostMapping()
    public ResponseEntity<Void> createPlayer() {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @PatchMapping("{username}")
    public ResponseEntity<Void> updatePlayer(@PathVariable String username) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @DeleteMapping("{username}")
    public ResponseEntity<Void> deletePlayer(@PathVariable String username) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
