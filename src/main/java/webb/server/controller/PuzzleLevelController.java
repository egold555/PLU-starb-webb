package webb.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webb.server.repository.PuzzleRepository;
import webb.shared.dtos.puzzle.PuzzleLevelDTO;
import webb.shared.dtos.puzzle.created.CreatePuzzleLevelDTO;
import webb.shared.dtos.puzzle.updated.UpdatePuzzleLevelDTO;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
    public ResponseEntity<Integer> createPuzzleLevel(@RequestBody CreatePuzzleLevelDTO newPuzzle) {
        int id = (int)puzzleRepository.count()+1;
        PuzzleLevelDTO puzzle = new PuzzleLevelDTO(id, newPuzzle.getRegions(), newPuzzle.getSolution(), newPuzzle.getGridSize(),
                newPuzzle.getNumStars(), 0);
        puzzleRepository.save(puzzle);
        return new ResponseEntity<>(puzzle.getId(), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<PuzzleLevelDTO> getPuzzleLevel(@PathVariable Integer id) {
        PuzzleLevelDTO foundPuzzle = fetchPuzzle(id);
        return new ResponseEntity<>(foundPuzzle, HttpStatus.OK);
    }

    @PutMapping ("{id}")
    public ResponseEntity<Void> updatePuzzleLevel(@PathVariable Integer id, @RequestBody UpdatePuzzleLevelDTO updates) {
        PuzzleLevelDTO foundPuzzle = fetchPuzzle(id);

        foundPuzzle.setRegions(updates.getRegions());
        foundPuzzle.setSolution(updates.getSolution());
        foundPuzzle.setGridSize(updates.getGridSize());
        foundPuzzle.setNumStars(updates.getNumStars());
        foundPuzzle.setSolvedByNumPlayers(updates.getSolvedByNumPlayers());

        puzzleRepository.save(foundPuzzle);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePuzzleLevel(@PathVariable Integer id) {
        PuzzleLevelDTO foundPuzzle = fetchPuzzle(id);
        puzzleRepository.deleteById(foundPuzzle.getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private PuzzleLevelDTO fetchPuzzle(Integer id){
        Optional<PuzzleLevelDTO> puzzleFound = puzzleRepository.findById(id);
        return puzzleFound.orElseThrow(() -> new NoSuchElementException((String.format("Puzzle '%d' not found.", id))));
    }
}
