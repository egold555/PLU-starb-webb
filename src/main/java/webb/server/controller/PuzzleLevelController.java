package webb.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import webb.server.repository.PuzzleRepository;
import webb.shared.dtos.puzzle.PuzzleLevelDTO;
import webb.shared.dtos.puzzle.created.CreatePuzzleLevelDTO;
import webb.shared.dtos.puzzle.updated.UpdatePuzzleLevelDTO;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("puzzles/levels")
@Validated
@CrossOrigin("*")
public class PuzzleLevelController {

    private final PuzzleRepository puzzleRepository;

    /**
     * Constructor for PuzzleLevelController.
     * @param puzzleRepository Repository for managing Puzzle entities.
     */
    @Autowired
    public PuzzleLevelController(PuzzleRepository puzzleRepository) {
        this.puzzleRepository = puzzleRepository;
    }

    /**
     * Endpoint for retrieving all Puzzle entities.
     * @return List of all Puzzle entities
     */
    @GetMapping()
    public List<PuzzleLevelDTO> getPuzzleLevels() {
        return puzzleRepository.findAll();
    }

    /**
     * Endpoint for creating a new Puzzle entity
     * @param newPuzzle DTO object representing the new Puzzle entity to be created
     * @return ResponseEntity containing the id of the created PuzzleDTO object.
     *
     */
    @PostMapping()
    public ResponseEntity<Integer> createPuzzleLevel(@RequestBody CreatePuzzleLevelDTO newPuzzle) {
        int id = (int)puzzleRepository.count()+1;
        PuzzleLevelDTO puzzle = new PuzzleLevelDTO(id, newPuzzle.getRegions(), newPuzzle.getSolution(), newPuzzle.getGridSize(),
                newPuzzle.getNumStars(), 0);
        puzzleRepository.save(puzzle);
        return new ResponseEntity<>(puzzle.getId(), HttpStatus.CREATED);
    }

    /**
     * Endpoint for retrieving a specific Puzzle entity.
     * @param id ID of the Puzzle to retrieve.
     * @return ResponseEntity containing the PuzzleLevelDTO.
     */
    @GetMapping("{id}")
    public ResponseEntity<PuzzleLevelDTO> getPuzzleLevel(@PathVariable Integer id) {
        PuzzleLevelDTO foundPuzzle = fetchPuzzle(id);
        return new ResponseEntity<>(foundPuzzle, HttpStatus.OK);
    }

    /**
     * Endpoint for updating an existing Puzzle entity.
     * @param id ID of the Puzzle entity to update.
     * @param updates DTO Object containing the updated Puzzle fields.
     * @return ResponseEntity indicating the success of the update operation.
     *
     */
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

    /**
     * Endpoint for deleting an existing Puzzle entity.
     * @param id ID of the Puzzle entity to update.
     * @return ResponseEntity indicating the success of the update operation.
     *
     * TODO: Add checks to see if user is authenticated and authorized
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePuzzleLevel(@PathVariable Integer id) {
        PuzzleLevelDTO foundPuzzle = fetchPuzzle(id);
        puzzleRepository.deleteById(foundPuzzle.getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Helper method for fetching a Puzzle entity by ID.
     * @param id ID of the Puzzle entity to fetch.
     * @return PuzzleDTO object representing the fetched Puzzle entity.
     * @throws NoSuchElementException if the Puzzle entity could not be found.
     */
    private PuzzleLevelDTO fetchPuzzle(Integer id){
        Optional<PuzzleLevelDTO> puzzleFound = puzzleRepository.findById(id);
        return puzzleFound.orElseThrow(() -> new NoSuchElementException((String.format("Puzzle '%d' not found.", id))));
    }
}
