package webb.server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("puzzles")
public class PuzzleLevelController {
    @GetMapping()
    public ResponseEntity<Void> getPuzzleLevels() {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @PostMapping()
    public ResponseEntity<Void> createPuzzleLevel() {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Void> getPuzzleLevel(@PathVariable Integer id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @PutMapping ("{id}")
    public ResponseEntity<Void> updatePuzzleLevel(@PathVariable Integer id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePuzzleLevel(@PathVariable Integer id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
