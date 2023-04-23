package webb.server.boostrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import webb.server.repository.UserRepository;
import webb.shared.dtos.user.UserDTO;

@Component
public class UserAdminBootstrap implements ApplicationListener<ApplicationReadyEvent> {
    private final UserRepository userRepo;

    @Value("${ADMIN_USERNAME:}")
    private String ADMIN_USERNAME;
    private final Logger logger = LoggerFactory.getLogger(PuzzleBootstrap.class);
    private final String PUZZLES_FP = "./puzzles";

    public UserAdminBootstrap(UserRepository repo) {
        this.userRepo = repo;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if(userRepo.count() == 0) return;

        if(ADMIN_USERNAME.isEmpty())
            throw new IllegalStateException("ADMIN_USERNAME required to launch the server. Please add this as env variable.");

        UserDTO admin = new UserDTO(ADMIN_USERNAME, null);
        userRepo.save(admin);
    }
}