package Session04.Ex6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    private UserService service;
    private User existingUser;
    private List<User> users;

    @BeforeEach
    void setUp(){
        service = new UserService();
        existingUser = new User("user@gmail.com", LocalDate.of(2000,1,1));

        users = new ArrayList<>();
        users.add(existingUser);
    }

    @Test
    void shouldUpdateProfileSuccessfullyWhenEmailAndBirthDateValid(){

        UserProfile newProfile =
                new UserProfile("new@gmail.com", LocalDate.of(1999,1,1));

        User result = service.updateProfile(existingUser,newProfile,users);

        assertNotNull(result);
    }

    @Test
    void shouldRejectUpdateWhenBirthDateInFuture(){

        UserProfile newProfile =
                new UserProfile("new@gmail.com", LocalDate.now().plusDays(1));

        User result = service.updateProfile(existingUser,newProfile,users);

        assertNull(result);
    }

    @Test
    void shouldRejectUpdateWhenEmailAlreadyExists(){

        users.add(new User("dup@gmail.com", LocalDate.of(1990,1,1)));

        UserProfile newProfile =
                new UserProfile("dup@gmail.com", LocalDate.of(1999,1,1));

        User result = service.updateProfile(existingUser,newProfile,users);

        assertNull(result);
    }

    @Test
    void shouldAllowUpdateWhenEmailNotChanged(){

        UserProfile newProfile =
                new UserProfile("user@gmail.com", LocalDate.of(1995,1,1));

        User result = service.updateProfile(existingUser,newProfile,users);

        assertNotNull(result);
    }

    @Test
    void shouldUpdateSuccessfullyWhenUserListEmpty(){

        List<User> emptyUsers = new ArrayList<>();

        UserProfile newProfile =
                new UserProfile("new@gmail.com", LocalDate.of(1999,1,1));

        User result = service.updateProfile(existingUser,newProfile,emptyUsers);

        assertNotNull(result);
    }

    @Test
    void shouldRejectWhenEmailDuplicateAndBirthDateFuture(){

        users.add(new User("dup@gmail.com", LocalDate.of(1990,1,1)));

        UserProfile newProfile =
                new UserProfile("dup@gmail.com", LocalDate.now().plusDays(5));

        User result = service.updateProfile(existingUser,newProfile,users);

        assertNull(result);
    }
}