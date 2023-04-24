package webb.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webb.server.repository.PuzzleRepository;
import webb.shared.dtos.puzzle.PuzzleLevelDTO;

import java.util.List;

@RestController
@RequestMapping("puzzles")
public class PuzzleLevelController {

    private final PuzzleRepository puzzleRepository;

    @Autowired
    public PuzzleLevelController(PuzzleRepository puzzleRepository) {
        this.puzzleRepository = puzzleRepository;
    }

    @GetMapping()
    public List<PuzzleLevelDTO> getPuzzleLevels() {
        return puzzleRepository.findAll();
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
