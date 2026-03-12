package Session04.Ex1;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class UserValidatorTest {
    @Test
    public void TC01_validUsername(){
        UserValidator validator = new UserValidator();
        String name = "user123";

        boolean result = validator.isValidUsername(name);


        assertTrue(result);

    }

    @Test
    public void TC02_usernameTooShort(){
        UserValidator validator = new UserValidator();
        String name = "abc";

        boolean result = validator.isValidUsername(name);


        assertFalse(result);
    }


    @Test
    public void TC03_usernameContainsSpace(){
        UserValidator validator = new UserValidator();
        String name = "user name";

        boolean result = validator.isValidUsername(name);


        assertFalse(result);
    }




}
