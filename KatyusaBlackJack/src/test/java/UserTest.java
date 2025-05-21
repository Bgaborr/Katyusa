import org.junit.jupiter.api.Test;
import org.models.User;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testDefaultConstructorAndSetters() {
        User user = new User();
        user.setId(1);
        user.setUsername("tesztuser");
        user.setPassword("titkosjelszo");
        user.setEmail("teszt@example.com");

        assertEquals(1, user.getId());
        assertEquals("tesztuser", user.getUsername());
        assertEquals("titkosjelszo", user.getPassword());
        assertEquals("teszt@example.com", user.getEmail());
    }

    @Test
    public void testConstructorWithParameters() {
        User user = new User("alma", "korte123", "alma@pelda.com");

        assertEquals("alma", user.getUsername());
        assertEquals("korte123", user.getPassword());
        assertEquals("alma@pelda.com", user.getEmail());
        assertEquals(0, user.getId());  // mivel nem állítottuk be
    }
}
