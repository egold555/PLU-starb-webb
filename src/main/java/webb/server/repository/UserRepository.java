package webb.server.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import webb.shared.dtos.user.UserDTO;

import java.util.List;

public interface UserRepository extends MongoRepository<UserDTO, String> {
}
