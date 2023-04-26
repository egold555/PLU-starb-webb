package webb.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import webb.server.service.UserService;
import webb.shared.dtos.user.UserDTO;
import webb.shared.dtos.user.UserStatsDTO;
import webb.shared.dtos.user.created.CreatedUserDTO;
import webb.shared.dtos.user.updated.UpdatedUserStatsDTO;

import java.security.Principal;
@Validated
@RestController
@RequestMapping("users")
@CrossOrigin("*")
public class UserController {
    /**
     * Service object for managing User entities.
     */
    private final UserService userService;

    /**
     * Constructor for UserController class.
     *
     * @param userService
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Endpoint for retrieving a specific User entity.
     * @param username Username of the User entity to retrieve.
     * @return ResponseEntity containing the retrieved UserDTO object.
     *
     * Possible response codes: either 200, 404
     */
    @GetMapping("{username}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String username) {
        UserDTO foundUser = userService.fetchUser(username);
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
    public ResponseEntity<UserDTO> createUser(@RequestBody CreatedUserDTO newUser) {
        UserDTO user = new UserDTO(newUser.getUsername());
        userService.createAccount(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    /**
     * Endpoint for updating an existing User entity.
     * @param username Username of the User entity to update.
     * @param updates DTO object containing the updated UserStatsDTO object.
     * @return ResponseEntity indicating the success of the update operation.
     *
     * Possible response codes: either 204, 400, 401, 403
     */
    @PatchMapping("{username}")
    public ResponseEntity<Void> updateUser(@PathVariable String username, @RequestBody UpdatedUserStatsDTO updates) {
        UserDTO foundUser = userService.fetchUser(username);

        UserStatsDTO foundUserStats = foundUser.getStats();
        foundUserStats.setPuzzlesComplete(updates.getPuzzlesComplete());

        userService.saveUser(foundUser);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Endpoint for deleting an existing User entity.
     * @param username Username of the User entity to update.
     * @return ResponseEntity indicating the success of the update operation.
     *
     * Possible response codes: either 204, 400, 401, 403
     */
    @DeleteMapping("{username}")
    public ResponseEntity<Void> deleteUser(Principal userPrincipal, @PathVariable String username) {
        userService.deleteUser(userPrincipal, username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}