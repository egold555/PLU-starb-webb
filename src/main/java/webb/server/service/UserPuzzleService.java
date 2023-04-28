package webb.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Service;
import webb.server.advice.exception.EntryAlreadyExistsException;
import webb.server.repository.UserPuzzleRepository;
import webb.shared.dtos.puzzle.user.UserPuzzleDTO;
import webb.shared.dtos.puzzle.user.created.CreateUserPuzzleDTO;
import webb.shared.dtos.puzzle.user.update.UpdateUserPuzzleDTO;
import webb.shared.dtos.user.UserStatsDTO;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserPuzzleService {

    private final UserPuzzleRepository userPuzzleRepo;

    @Autowired
    public UserPuzzleService(UserPuzzleRepository userPuzzleRepo) {
        this.userPuzzleRepo = userPuzzleRepo;
    }

    public void saveUserPuzzle(UserPuzzleDTO userPuzzleDTO) {
        userPuzzleExistsElseThrow(userPuzzleDTO.getId());
        userPuzzleRepo.save(userPuzzleDTO);
    }

    public void updateUserPuzzle(int id, String username, UpdateUserPuzzleDTO updateUserPuzzleDTO) {
        UserPuzzleDTO userPuzzleDTO = fetchUserPuzzle(id, username);

        userPuzzleDTO.setCompleted(updateUserPuzzleDTO.isCompleted());
        userPuzzleDTO.setSolveTime(updateUserPuzzleDTO.getSolveTime());
        userPuzzleDTO.setPlacedMarkers(updateUserPuzzleDTO.getPlacedMarkers());
        userPuzzleDTO.setPlacedStars(updateUserPuzzleDTO.getPlacedStars());
        userPuzzleDTO.setStarsRemaining(updateUserPuzzleDTO.getStarsRemaining());

        userPuzzleRepo.save(userPuzzleDTO);
    }

    public void deleteUserPuzzle(int id, String username) {
        UserPuzzleDTO userPuzzleDTO = fetchUserPuzzle(id, username);
        userPuzzleRepo.delete(userPuzzleDTO);
    }

    /**
     * Performs an update on the user's stats, based on the updated puzzle
     * Has the following side effects:
     * - Updates the user's average solve time
     * - Updates the user's min solve time
     * - Updates the user's max solve time
     * - Updates the user's total solve time <-- TODO: implement
     *
     * @param username the user's username
     */
    public UserStatsDTO getUpdatedUserStats(String username) {
        UserStatsDTO newUserStats = new UserStatsDTO();

        // TODO: deprecate use of AggregationResults, and use a rolling sum to calculate the average solve time
        // TODO: compare current solve time to min/max solve time, and update if necessary
        // Crunch the numbers for the new user's stats
        AggregationResults<UserStatsDTO> avgSolveTime = userPuzzleRepo.getAvgSolveTimeByUser(username);
        AggregationResults<UserStatsDTO> minSolveTime = userPuzzleRepo.getMinSolveTimeByUser(username);
        AggregationResults<UserStatsDTO> maxSolveTime = userPuzzleRepo.getMaxSolveTimeByUser(username);
        AggregationResults<UserStatsDTO> completedPuzzles = userPuzzleRepo.getPuzzlesCompletedByUser(username);

        // update the new user's stats
        newUserStats.setMaxSolveTime(maxSolveTime.getMappedResults().get(0).getMaxSolveTime());
        newUserStats.setMinSolveTime(minSolveTime.getMappedResults().get(0).getMinSolveTime());
        newUserStats.setAvgSolveTime(avgSolveTime.getMappedResults().get(0).getAvgSolveTime());

        if (completedPuzzles.getMappedResults().size() == 0)
            newUserStats.setPuzzlesComplete(0);
        else
            newUserStats.setPuzzlesComplete(completedPuzzles.getMappedResults().get(0).getPuzzlesComplete());

        return newUserStats;
    }
    /**
     * Helper method for fetching a User entity by username.
     *
     * @param userPuzzleId Username of the User entity to fetch.
     * @return UserDTO object representing the fetched User entity.
     * @throws NoSuchElementException if the User entity could not be found.
     */
    public UserPuzzleDTO fetchUserPuzzle(String userPuzzleId) {
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
    public UserPuzzleDTO fetchUserPuzzle(int id, String username) {
        // this class has a method that gets the id from a id and username, better to reuse that
        CreateUserPuzzleDTO form = new CreateUserPuzzleDTO(username, id);
        Optional<UserPuzzleDTO> userPuzzleFound = userPuzzleRepo.findById(form.getUserPuzzleId());
        return userPuzzleFound.orElseThrow(() -> new NoSuchElementException((String.format("UserPuzzle '%s' not found.", form.getUserPuzzleId()))));
    }

    /**
     * Helper method for fetching all User entities.
     *
     * @return List of UserDTO objects representing the fetched User entities.
     */
    public List<UserPuzzleDTO> fetchUserPuzzlesByUser(String username) {
        return userPuzzleRepo.findAllByUser(username);
    }

    /**
     * Helper method for fetching all User entities.
     *
     * @return List of UserDTO objects representing the fetched User entities.
     */
    public void userPuzzleExistsElseThrow(String userPuzzleId) {
        if (userPuzzleRepo.existsById(userPuzzleId))
            throw new EntryAlreadyExistsException(String.format("The user puzzle level `%s` already exists.", userPuzzleId));
    }
}