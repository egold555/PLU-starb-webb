package webb.server.repository;

import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import webb.shared.dtos.puzzle.user.UserPuzzleDTO;
import webb.shared.dtos.user.UserStatsDTO;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;

public interface UserPuzzleRepository extends MongoRepository<UserPuzzleDTO, String> {
    public List<UserPuzzleDTO> findAllByUser(String username);

    @Aggregation(
            pipeline = {
                    "{$match: {user: ?0}}",
                    "{$group: {_id: '$user', minSolveTime: {$min: '$solveTime'}}}"
            }
    )
    AggregationResults<UserStatsDTO> getMinSolveTimeByUser(String username);

    @Aggregation(
            pipeline = {
                    "{$match: {user: ?0}}",
                    "{$group: {_id: '$user', avgSolveTime: {$avg: '$solveTime'}}}"
            }
    )
    AggregationResults<UserStatsDTO> getAvgSolveTimeByUser(String username);

    @Aggregation(
            pipeline = {
                    "{$match: {user: ?0}}",
                    "{$group: {_id: '$user', maxSolveTime: {$max: '$solveTime'}}}"
            }
    )
    AggregationResults<UserStatsDTO> getMaxSolveTimeByUser(String username);

    @Aggregation(
            pipeline = {
                    "{$match: {user: ?0, completed: true}}",
                    "{$group: {_id: '$user', puzzlesComplete: {$sum: 1}}}"
            }
    )
    AggregationResults<UserStatsDTO> getPuzzlesCompletedByUser(String username);
}
