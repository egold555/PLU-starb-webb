package webb.server.bootstrap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import webb.server.repository.UserRepository;
import webb.shared.dtos.user.UserDTO;

@Component
public class UserAdminBootstrap implements ApplicationListener<ApplicationReadyEvent> {
    private final UserRepository userRepo;

    @Value("${starbo.admin.username}")
    private String ADMIN_USERNAME;
    private final String PUZZLES_FP = "./puzzles";

    public UserAdminBootstrap(UserRepository repo) {
        this.userRepo = repo;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent e) {
        if(userRepo.count() != 0)
            if(userRepo.existsById(ADMIN_USERNAME)) return;

        UserDTO admin = new UserDTO(ADMIN_USERNAME);
        userRepo.save(admin);
    }
}