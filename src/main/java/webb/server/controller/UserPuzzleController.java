package webb.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import webb.server.advice.exception.EntryAlreadyExistsException;
import webb.server.repository.UserPuzzleRepository;
import webb.server.repository.UserRepository;
import webb.shared.dtos.puzzle.user.created.CreateOrDeleteUserPuzzleLevelDTO;
import webb.shared.dtos.puzzle.user.UserPuzzleDTO;
import webb.shared.dtos.puzzle.user.update.UpdateUserPuzzleDTO;
import webb.shared.dtos.user.UserDTO;
import webb.shared.dtos.user.UserStatsDTO;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Validated
@RestController
@RequestMapping("puzzles/users")

public class UserPuzzleController {

    private final UserPuzzleRepository userPuzzleRepo;

    // We need this userPuzzleRepo here because we'll "push" updates to the statistics everytime we update
    // a puzzle
    private final UserRepository userRepo;
    @Autowired
    public UserPuzzleController(UserPuzzleRepository repo, UserRepository userRepo){
        this.userPuzzleRepo = repo;
        this.userRepo = userRepo;
    }

    @GetMapping("{username}")
    public ResponseEntity<List<UserPuzzleDTO>> getUserPuzzleLevels(@PathVariable String username) {
        List<UserPuzzleDTO> foundUserPuzzles = userPuzzleRepo.findAllByUser(username);
        return new ResponseEntity<>(foundUserPuzzles, HttpStatus.OK);
    }

    @GetMapping("{id}/{username}")
    public ResponseEntity<UserPuzzleDTO> getUserPuzzleLevel(@PathVariable Integer id, @PathVariable String username) {
        UserPuzzleDTO foundUserPuzzle = fetchUserPuzzle(id, username);
        return new ResponseEntity<>(foundUserPuzzle, HttpStatus.OK);
    }

    @PutMapping("/{id}/{username}")
    public ResponseEntity<Void> updateUserPuzzleLevel(@PathVariable Integer id, @PathVariable String username, @RequestBody UpdateUserPuzzleDTO updatedUserPuzzleDTO) {
        UserPuzzleDTO userPuzzleDTO = fetchUserPuzzle(id, username);

        // Replace the values
        userPuzzleDTO.setCompleted(updatedUserPuzzleDTO.isCompleted());
        userPuzzleDTO.setSolveTime(updatedUserPuzzleDTO.getSolveTime());
        userPuzzleDTO.setPlacedMarkers(updatedUserPuzzleDTO.getPlacedMarkers());
        userPuzzleDTO.setPlacedStars(updatedUserPuzzleDTO.getPlacedStars());
        userPuzzleDTO.setStarsRemaining(updatedUserPuzzleDTO.getStarsRemaining());

        userPuzzleRepo.save(userPuzzleDTO);

        // Get ready to update user's statistics
        AggregationResults<UserStatsDTO> avgSolveTime = userPuzzleRepo.getAvgSolveTimeByUser(username);
        AggregationResults<UserStatsDTO> minSolveTime = userPuzzleRepo.getMinSolveTimeByUser(username);
        AggregationResults<UserStatsDTO> maxSolveTime = userPuzzleRepo.getMaxSolveTimeByUser(username);

        // TODO: This whole method is code smell (bloated controller), it needs a shared service with userController
        UserDTO userFound = fetchUser(username);


        // TODO: Titles need to calculated
        UserStatsDTO userFoundStats = userFound.getStats();
        userFoundStats.setMaxSolveTime(maxSolveTime.getMappedResults().get(0).getMaxSolveTime());
        userFoundStats.setMinSolveTime(minSolveTime.getMappedResults().get(0).getMinSolveTime());
        userFoundStats.setAvgSolveTime(minSolveTime.getMappedResults().get(0).getAvgSolveTime());

        if(userPuzzleDTO.isCompleted()){
            userFoundStats.setPuzzlesComplete(userFoundStats.getPuzzlesComplete() + 1);
        } else {
            if(userFoundStats.getPuzzlesComplete() > 0)
                userFoundStats.setPuzzlesComplete(userFoundStats.getPuzzlesComplete() - 1);
        }

        userRepo.save(userFound);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping()
    public ResponseEntity<UserPuzzleDTO> createUserPuzzleLevel(@RequestBody CreateOrDeleteUserPuzzleLevelDTO createdUserPuzzle) {
        UserPuzzleDTO userPuzzleDTO = new UserPuzzleDTO(createdUserPuzzle.getLevelId(), createdUserPuzzle.getUsername());

        if(userPuzzleRepo.existsById(userPuzzleDTO.getId())) throw new EntryAlreadyExistsException(String.format("The user puzzle level `%s` already exists.", userPuzzleDTO.getUsername()));

        userPuzzleRepo.save(userPuzzleDTO);
        return new ResponseEntity<>(userPuzzleDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}/{username}")
    public ResponseEntity<Void> deleteUserPuzzleLevel(@RequestBody CreateOrDeleteUserPuzzleLevelDTO deletedUserPuzzle) {
        UserPuzzleDTO userPuzzleDTO = fetchUserPuzzle(deletedUserPuzzle.getUserPuzzleId());
        userPuzzleRepo.deleteById(userPuzzleDTO.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Helper method for fetching a User entity by username.
     *
     * @param userPuzzleId Username of the User entity to fetch.
     * @return UserDTO object representing the fetched User entity.
     * @throws NoSuchElementException if the User entity could not be found.
     */
    private UserPuzzleDTO fetchUserPuzzle(String userPuzzleId) {
        Optional<UserPuzzleDTO> userFound = userPuzzleRepo.findById(userPuzzleId);
        return userFound.orElseThrow(() -> new NoSuchElementException((String.format("User '%s' not found.", userPuzzleId))));
    }

    /**
     * Alternative Helper method for fetching a User entity by username.
     *
     * @param id the id of the puzzle level
     * @param username the user's name
     * @return UserDTO object representing the fetched User entity.
     * @throws NoSuchElementException if the User entity could not be found.
     */
    private UserPuzzleDTO fetchUserPuzzle(int id, String username) {
        // this class has a method that gets the id from a id and username, better to reuse that
        CreateOrDeleteUserPuzzleLevelDTO form = new CreateOrDeleteUserPuzzleLevelDTO(username, id);
        Optional<UserPuzzleDTO> userPuzzleFound = userPuzzleRepo.findById(form.getUserPuzzleId());
        return userPuzzleFound.orElseThrow(() -> new NoSuchElementException((String.format("UserPuzzle '%s' not found.", form.getUserPuzzleId()))));
    }

    private boolean userPuzzleExists(int id, String username) {
        CreateOrDeleteUserPuzzleLevelDTO form = new CreateOrDeleteUserPuzzleLevelDTO(username, id);
        return userPuzzleRepo.existsById(form.getUserPuzzleId());
    }

    /**
     * Helper method for fetching a User entity by username.
     * @param username Username of the User entity to fetch.
     * @return UserDTO object representing the fetched User entity.
     * @throws NoSuchElementException if the User entity could not be found.
     */
    private UserDTO fetchUser(String username) {
        Optional<UserDTO> userFound = userRepo.findById(username);
        return userFound.orElseThrow(() -> new NoSuchElementException((String.format("User '%s' not found.", username))));
    }
}