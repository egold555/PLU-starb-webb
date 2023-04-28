package webb.server;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import webb.server.advice.exception.EntryAlreadyExistsException;
import webb.server.controller.LeaderboardController;
import webb.server.controller.UserController;
import webb.server.repository.UserRepository;
import webb.server.service.UserService;
import webb.shared.dtos.leaderboard.LeaderboardDTO;
import webb.shared.dtos.leaderboard.LeaderboardEntryDTO;
import webb.shared.dtos.user.UserDTO;
import webb.shared.dtos.user.UserStatsDTO;
import webb.shared.dtos.user.updated.UpdatedUserStatsDTO;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserControllerIT {



    private UserDTO user1;
    private UserDTO user2;
    private UserDTO user3;
    private UserDTO user4;
    private UserDTO user5;

    private UserStatsDTO user1Stats = new UserStatsDTO((long)100, (long)10, (long)50, 10, 5, "Cadet");
    private UserStatsDTO user2Stats = new UserStatsDTO((long)100, (long)10, (long)50, 20, 5, "Cadet");
    private UserStatsDTO user3Stats = new UserStatsDTO((long)100, (long)10, (long)50, 5, 5, "Cadet");
    private UserStatsDTO user4Stats = new UserStatsDTO((long)100, (long)10, (long)50, 45, 5, "Cadet");
    private UserStatsDTO user5Stats = new UserStatsDTO((long)100, (long)10, (long)50, 0, 5, "Cadet");

    @Autowired
    private UserRepository ur;
    @Autowired
    private UserService us;
    @Autowired
    private UserController userController;

    @Autowired
    public UserControllerIT(UserRepository ur, UserService us, UserController userController) {
       this.ur = ur;
       this.us = us;
       this.userController = userController;
    }

    @BeforeEach
    void setUp(){
        ur.deleteAll();
        user1 = ur.save(new UserDTO("User1", user1Stats));
        user2 = ur.save(new UserDTO("User2", user2Stats));
        user3 = ur.save(new UserDTO("User3", user3Stats));
        user4 = ur.save(new UserDTO("User4", user4Stats));
        user5 = ur.save(new UserDTO("User5", user5Stats));

    }

    @Test
    void testGetUser200(){
    ResponseEntity<UserDTO> responseEntity = userController.getUser("User1");
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals("User1", responseEntity.getBody().getUsername());

    }
    @Test
    void testGetUser404(){
        assertThrows(NoSuchElementException.class,() -> userController.getUser("User9"));

    }

    @Test
    void testCreateUser201(){

        UserDTO newUser = new UserDTO("NewUser", new UserStatsDTO());

        ResponseEntity<UserDTO> responseEntity = userController.createUser(newUser);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        assertEquals("NewUser", responseEntity.getBody().getUsername());
    }

    @Test
    void testCreateUser409(){
        UserDTO newUser = new UserDTO("NewUser", new UserStatsDTO());

        ResponseEntity<UserDTO> responseEntity = userController.createUser(newUser);

        assertThrows(EntryAlreadyExistsException.class,() -> userController.createUser(newUser));

    }


    @Test
    void testupdateUser204() {
        UpdatedUserStatsDTO updatedUserStatsDTO = new UpdatedUserStatsDTO(10);
        ResponseEntity<Void> responseEntity = userController.updateUser("User5", updatedUserStatsDTO);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

        UserDTO updatedUser = userController.getUser("User5").getBody();
        assertEquals(10, updatedUser.getStats().getPuzzlesComplete());



    }

    @Test
    void testDeleteUser204(){
        Principal mockPrincipal = mock(Principal.class);
        UserService mockUserService = mock(UserService.class);

        UserController userController = new UserController(mockUserService);

        ResponseEntity<Void> response = userController.deleteUser(mockPrincipal, "testuser");

        verify(mockUserService).deleteUser(mockPrincipal, "testuser");

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

    }


}
