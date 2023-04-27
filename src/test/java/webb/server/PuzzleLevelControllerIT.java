package webb.server;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import webb.shared.dtos.user.UserDTO;

import java.util.Collections;

@Testcontainers
@SpringBootTest(classes = WebbServer.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PuzzleLevelControllerIT {
    @Container
    public static MongoDBContainer container = new MongoDBContainer("mongo");

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", container::getReplicaSetUrl);
    }
    @Test
    void test() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("body", headers);
        String url = "http://localhost:" + port + "/users/starboAdmin";
        System.out.println("This is the print statement " + this.restTemplate.exchange(url, org.springframework.http.HttpMethod.GET, entity, UserDTO.class).getBody());
    }

}
