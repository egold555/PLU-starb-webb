package webb.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import webb.server.advice.exception.EntryAlreadyExistsException;
import webb.server.repository.UserRepository;
import webb.shared.dtos.user.UserDTO;
import webb.shared.dtos.user.UserStatsDTO;
import webb.shared.dtos.user.created.CreatedUserDTO;
import webb.shared.dtos.user.updated.UpdatedUserStatsDTO;

import java.nio.file.attribute.UserPrincipal;
import java.util.NoSuchElementException;
import java.util.Optional;

@Validated
@RestController
@RequestMapping("users")
public class UserController {
    /**
     * Repository object for managing User entities.
     */
    private final UserRepository userRepo;

    /**
     * Constructor for UserController class.
     * @param userRepo Repository object for managing User entities.
     */
    @Autowired
    public UserController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    /**
     * Endpoint for retrieving a specific User entity.
     * @param username Username of the User entity to retrieve.
     * @return ResponseEntity containing the retrieved UserDTO object.
     *
     * Possible response codes: either 200, 404
     */
    @GetMapping("{username}")
    public ResponseEntity<UserDTO> getPlayer(@PathVariable String username) {
        UserDTO foundUser = fetchUser(username);
        return new ResponseEntity<>(foundUser, HttpStatus.OK);
    }

    /**
     * Endpoint for creating a new User entity.
     * @param newUser DTO object representing the new User entity to create.
     * @return ResponseEntity containing the created UserDTO object.
     *
     * Possible response codes: either 201, 400, 409
     */
    @PostMapping("")
    public ResponseEntity<UserDTO> createPlayer(@RequestBody CreatedUserDTO newUser) {
        boolean userFound = userRepo.existsById(newUser.getUsername());
        if(userFound) throw new EntryAlreadyExistsException(String.format("Username `%s` already exists. Please choose something else.", newUser.getUsername()));

        UserDTO user = new UserDTO(newUser.getUsername());
        userRepo.save(user);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    /**
     * Endpoint for updating an existing User entity.
     * @param username Username of the User entity to update.
     * @param updates DTO object containing the updated UserStatsDTO object.
     * @return ResponseEntity indicating the success of the update operation.
     *
     * Possible response codes: either 204, 400, 401, 403
     * TODO: Add checks to see if user is authenticated and authorized
     */
    @PatchMapping("{username}")
    public ResponseEntity<Void> updatePlayer(@PathVariable String username, @RequestBody UpdatedUserStatsDTO updates) {
        UserDTO foundUser = fetchUser(username);

        UserStatsDTO foundUserStats = foundUser.getStats();
        foundUserStats.setPuzzlesComplete(updates.getPuzzlesComplete());

        userRepo.save(foundUser);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Endpoint for deleting an existing User entity.
     * @param username Username of the User entity to update.
     * @return ResponseEntity indicating the success of the update operation.
     *
     * Possible response codes: either 204, 400, 401, 403
     * TODO: Add checks to see if user is authenticated and authorized
     */
    @DeleteMapping("{username}")
    public ResponseEntity<Void> deletePlayer(@PathVariable String username) {
        UserDTO foundUser = fetchUser(username);
        userRepo.deleteById(foundUser.getUsername());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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