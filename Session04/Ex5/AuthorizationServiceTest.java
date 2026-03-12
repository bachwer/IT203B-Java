package Session04.Ex5;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AuthorizationServiceTest {

    private AuthorizationService service;
    private User user;


    @Before
    public void setUp() {
        service = new AuthorizationService();
    }

    @After
    public void cleanUp() {
        user = null;
    }


    @Test
    public void adminCanLockUser() {
        user = new User(Role.ADMIN);

        boolean result = service.canPErFormAction(user, Action.LOCK_USER);
        assertTrue(result);
    }


    @Test
    public void adminCanViewProfile() {
        user = new User(Role.ADMIN);

        boolean result = service.canPErFormAction(user, Action.VIEW_PROFILE);

        assertTrue(result);
    }


    @Test
    public void moderatorCannotDeleteUser() {
        user = new User(Role.MODERATOR);

        boolean result = service.canPErFormAction(user, Action.DELETE_USER);

        assertFalse(result);
    }

    @Test
    public void moderatorCanLockUser() {
        user = new User(Role.MODERATOR);

        boolean result = service.canPErFormAction(user, Action.LOCK_USER);

        assertTrue(result);
    }

    @Test
    public void moderatorCanViewProfile() {
        user = new User(Role.MODERATOR);

        boolean result = service.canPErFormAction(user, Action.VIEW_PROFILE);

        assertTrue(result);
    }

    // ===== USER TESTS =====

    @Test
    public void userCannotDelete() {
        user = new User(Role.USER);

        boolean result = service.canPErFormAction(user, Action.DELETE_USER);

        assertFalse(result);
    }

    @Test
    public void userCannotLock() {
        user = new User(Role.USER);

        boolean result = service.canPErFormAction(user, Action.LOCK_USER);

        assertFalse(result);
    }

    @Test
    public void userCanViewProfile() {
        user = new User(Role.USER);

        boolean result = service.canPErFormAction(user, Action.VIEW_PROFILE);

        assertTrue(result);
    }
}