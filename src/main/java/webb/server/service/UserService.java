package webb.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import webb.server.advice.exception.EntryAlreadyExistsException;
import webb.server.advice.exception.ForbiddenActionException;
import webb.server.repository.UserRepository;
import webb.shared.dtos.leaderboard.LeaderboardDTO;
import webb.shared.dtos.leaderboard.LeaderboardEntryDTO;
import webb.shared.dtos.user.UserDTO;
import webb.shared.dtos.user.UserDTOComparator;
import webb.shared.dtos.user.UserStatsDTO;
import webb.shared.models.title.TITLES;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepo;
    private final String ADMIN_USERNAME;

    @Autowired
    public UserService(UserRepository userRepo, @Value("${starbo.admin.username}") String adminUsername) {
        this.userRepo = userRepo;
        ADMIN_USERNAME = adminUsername;
    }

    /**
     * Checks if a user is an admin.
     * @param username Username of the user to check.
     * @return true if the user is an admin, false otherwise.
     */
    public boolean isAdmin(String username) {
        return username.equals(ADMIN_USERNAME);
    }

    /**
     * Checks if a user is an admin, if not throws an exception.
     * @param username Username of the user to check.
     * @throws ForbiddenActionException if the user is not an admin.
     */
    public void isAdminElseThrow(String username) {
        if(!isAdmin(username)) throw new ForbiddenActionException("You are are not authorized to perform this action.");
    }

    /**
     * Checks if a user exists in the database.
     * @param username Username of the user to check.
     * @return true if the user exists, false otherwise.
     */
    public boolean hasAccount(String username) {
        return userRepo.existsById(username);
    }

    /**
     * Checks if a user exists in the database.
     * @param username Username of the user to check.
     * @return true if the user exists, false otherwise.
     */
    public void hasAccountElseThrow(String username) {
        if(!hasAccount(username)) throw new NoSuchElementException(String.format("User '%s' not found.", username));
    }

    /**
     * Creates a new user in the database.
     * @param newUser UserDTO object representing the new user to create.
     * @throws EntryAlreadyExistsException if the username already exists.
     */
    public void createAccount(UserDTO newUser) {
        String username = newUser.getUsername();
        if(hasAccount(username)) throw new EntryAlreadyExistsException(String.format("Username `%s` already exists. Please choose something else.", username));
        userRepo.save(newUser);
    }

    public void hasPermissionElseThrow(Principal userPrincipal, String username) {
        if(isAdmin(userPrincipal.getName())) return;
        if(!userPrincipal.getName().equals(username)) throw new ForbiddenActionException("You are not authorized to perform this action.");
    }

    /**
     * Helper method for fetching a User entity by username.
     * @param username Username of the User entity to fetch.
     * @return UserDTO object representing the fetched User entity.
     * @throws NoSuchElementException if the User entity could not be found.
     */
    public UserDTO fetchUser(String username) {
        Optional<UserDTO> userFound = userRepo.findById(username);
        return userFound.orElseThrow(() -> new NoSuchElementException((String.format("User '%s' not found.", username))));
    }

    /**
     * Deletes a user from the database. If user being deleted is the user currently logged in or the admin, if not throws an exception.
     * @param userPrincipal the user currently logged in
     * @param username the user to be deleted
     */
    public void deleteUser(Principal userPrincipal, String username) {
        // if they aren't admin or admin is trying to delete themselves
        if (!userPrincipal.getName().equals(username) || isAdmin(username))
                throw new ForbiddenActionException("You may not delete this user.");

        UserDTO user = fetchUser(username);
        userRepo.deleteById(user.getUsername());
    }

    /**
     * Updates a user in the database.
     * @param user UserDTO object representing the user to update.
     */
    public void saveUser(UserDTO user) {
        userRepo.save(user);
    }

    /**
     * Fetches the user leaderboard.
     * @return
     */
    public LeaderboardDTO getUserLeaderboard() {
        List<UserDTO> users = userRepo.findAll();
        users.sort(new UserDTOComparator());
        List<LeaderboardEntryDTO> entries = users.stream()
                .map(user -> new LeaderboardEntryDTO(user.getUsername(), user.getStats().getPuzzlesComplete()))
                .toList();
        return new LeaderboardDTO(entries);
    }

    /**
     * Updates a user's stats.
     * @param username
     * @param newUserStats
     */
    public void updateUserStats(String username, UserStatsDTO newUserStats) {
        UserDTO userFound = fetchUser(username);
        userFound.setStats(newUserStats);
        saveUser(userFound);
    }


    /**
     * Determines the user's current title and how many puzzles they need to complete to get the next one.
     * As of now, the titles are determined by the number of puzzles completed. Every 4 puzzles completed, the user gets a new title.
     * @param username
     */
    public void determineTitles(String username) {
        UserDTO user = fetchUser(username);
        List<TITLES> titles = List.of(TITLES.values());

        int titleIndex = user.getStats().getPuzzlesComplete() / 4;
        int nextTitleIndex = titleIndex + 1;

        // if the user has completed all the puzzles, set the title to the last one
        if (titleIndex >= titles.size()) {
            titleIndex = titles.size() - 1;
        }

        TITLES currentTitle = titles.get(titleIndex);
        user.getStats().setCurrentTitle(currentTitle.toString());
        user.getStats().setPuzzlesUntilNextTitle((titleIndex + 1) * 4 - user.getStats().getPuzzlesComplete());

        if(nextTitleIndex > titles.size() - 1) {
            user.getStats().setNextTitle("You have completed all the puzzles!");
        } else {
            TITLES nextTitle = titles.get(nextTitleIndex);
            user.getStats().setNextTitle(nextTitle.toString());
        }
        
        saveUser(user);
    }
}