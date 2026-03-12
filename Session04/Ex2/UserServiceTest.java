package Session04.Ex2;


import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {


    @Test
    public void TC01_age18_valid(){

        UserService service = new UserService();


        boolean result = service.checkRegistrationAge(18);

        assertTrue(result);
    }


    @Test
    public void TC02_age17invalid(){
        UserService service = new UserService();

        boolean result = service.checkRegistrationAge(17);

        assertFalse(result);

    }


    @Test
    public void TC03_ageNegative_exception(){
        UserService service = new UserService();

        assertThrows(IllegalArgumentException.class, () -> {
            service.checkRegistrationAge(-1);
        });
    }


}
