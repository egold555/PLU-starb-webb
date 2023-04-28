package webb.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import webb.shared.dtos.puzzle.PuzzleLevelDTO;

public interface PuzzleRepository extends MongoRepository<PuzzleLevelDTO, Integer> {
}
