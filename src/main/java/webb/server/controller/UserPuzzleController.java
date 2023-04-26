package webb.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import webb.server.service.UserPuzzleService;
import webb.server.service.UserService;
import webb.shared.dtos.puzzle.user.created.CreateUserPuzzleDTO;
import webb.shared.dtos.puzzle.user.UserPuzzleDTO;
import webb.shared.dtos.puzzle.user.update.UpdateUserPuzzleDTO;
import webb.shared.dtos.user.UserStatsDTO;

import java.security.Principal;
import java.util.List;

@Validated
@RestController
@RequestMapping("puzzles/users")
@CrossOrigin("*")
public class UserPuzzleController {

    private  final UserPuzzleService userPuzzleService;
    private  final UserService userService;

    @Autowired
    public UserPuzzleController(UserPuzzleService userPuzzleService, UserService userService){
        this.userPuzzleService = userPuzzleService;
        this.userService = userService;
    }

    @GetMapping("{username}")
    public ResponseEntity<List<UserPuzzleDTO>> getUserPuzzleLevels(@PathVariable String username) {
        userService.hasAccountElseThrow(username);
        List<UserPuzzleDTO> foundUserPuzzles = userPuzzleService.fetchUserPuzzlesByUser(username);
        return new ResponseEntity<>(foundUserPuzzles, HttpStatus.OK);
    }

    @GetMapping("{username}/{id}")
    public ResponseEntity<UserPuzzleDTO> getUserPuzzleLevel(@PathVariable Integer id, @PathVariable String username) {
        UserPuzzleDTO foundUserPuzzle = userPuzzleService.fetchUserPuzzle(id, username);
        return new ResponseEntity<>(foundUserPuzzle, HttpStatus.OK);
    }

    @PutMapping("/{username}/{id}")
    public ResponseEntity<Void> updateUserPuzzleLevel(Principal user, @PathVariable Integer id, @PathVariable String username, @RequestBody UpdateUserPuzzleDTO updatedUserPuzzleDTO) {
        userService.hasPermissionElseThrow(user, username);

        userPuzzleService.updateUserPuzzle(id, username, updatedUserPuzzleDTO);

        UserStatsDTO newUserStatsDTO = userPuzzleService.getUpdatedUserStats(username);
        userService.updateUserStats(username, newUserStatsDTO);

        userService.determineTitles(username);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping()
    public ResponseEntity<UserPuzzleDTO> createUserPuzzleLevel(Principal user, @RequestBody CreateUserPuzzleDTO createdUserPuzzle) {
        userService.hasPermissionElseThrow(user, createdUserPuzzle.getUsername());

        UserPuzzleDTO userPuzzleDTO = new UserPuzzleDTO(createdUserPuzzle.getLevelId(), createdUserPuzzle.getUsername());
        userPuzzleService.saveUserPuzzle(userPuzzleDTO);

        return new ResponseEntity<>(userPuzzleDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{username}/{id}")
    public ResponseEntity<Void> deleteUserPuzzleLevel(Principal user, @PathVariable int id, @PathVariable String username) {
        userService.hasPermissionElseThrow(user, username);

        userPuzzleService.deleteUserPuzzle(id, username);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}