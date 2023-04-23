package webb.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import webb.shared.dtos.user.UserDTO;

public interface UserRepository extends MongoRepository<UserDTO, String> {
}
