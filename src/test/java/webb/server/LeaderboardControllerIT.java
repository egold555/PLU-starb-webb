package webb.server;


import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest
public class LeaderboardControllerIT {
    public static MongoDBContainer container = new MongoDBContainer("mongo:5.0");
}
