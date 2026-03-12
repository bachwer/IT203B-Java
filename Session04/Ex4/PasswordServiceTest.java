package Session04.Ex4;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

public class PasswordServiceTest {
    private PasswordService service;



    @BeforeEach
    public void setUp(){
        service = new PasswordService();
    }


    @Test
    public void testStrongPassword(){
        String  result = service.evaluatePasswordStrength("Abc123!@");

        assertEquals("Manh", result);

    };

    @Test
    public void testMediumPasswords(){

        assertAll(
                () -> assertEquals("Trung bình", service.evaluatePasswordStrength("abc123!@")),
                () -> assertEquals("Trung bình", service.evaluatePasswordStrength("ABC123!@")),
                () -> assertEquals("Trung bình", service.evaluatePasswordStrength("Abcdef!@")),
                () -> assertEquals("Trung bình", service.evaluatePasswordStrength("Abc12345"))
        );
    }

    @Test
    public void testWeakPasswords(){

        assertAll(
                () -> assertEquals("Yếu", service.evaluatePasswordStrength("Ab1!")),
                () -> assertEquals("Yếu", service.evaluatePasswordStrength("password")),
                () -> assertEquals("Yếu", service.evaluatePasswordStrength("ABC12345"))
        );
    }


}
