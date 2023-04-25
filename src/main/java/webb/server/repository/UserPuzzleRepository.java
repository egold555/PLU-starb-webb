package webb.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import webb.shared.dtos.puzzle.user.UserPuzzleDTO;

public interface UserPuzzleRepository extends MongoRepository<UserPuzzleDTO, String> {
}
