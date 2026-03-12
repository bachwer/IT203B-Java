package Session04.Ex3;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class UserProcessorTest {


    private UserProcessor processor;


    @Before
    public void setUp(){
        processor = new UserProcessor();
    }


    @Test
    public void shouldReturnSameEmailWhenEmailIsValid(){
        String result = processor.processEmail("user@gmail.com");


        assertEquals("user@gmail.com", result);
    }

    @Test
    public void shouldThrowExceptionWhenEmailMissingAtSymbol(){
        assertThrows(IllegalArgumentException.class, () -> {
            processor.processEmail("usergmail.com");
        });
    }


    @Test
    public void shouldThrowExceptionWhenEmailMissingDomain(){
        assertThrows(IllegalArgumentException.class, () -> {
            processor.processEmail("user@");
        });
    }

    @Test
    public void shouldConvertEmailToLowercase(){
        String result = processor.processEmail("Example@Gmail.com");

        assertEquals("example@gmail.com", result);
    }
}
