
import org.junit.jupiter.api.Test;
import org.models.User;
import org.session.Session;
import static org.junit.jupiter.api.Assertions.*;

class SessionTest {
    @Test
    public void testSessionSetAndGetUser() {
        User user = new User("Teszt", "1234", "email@test.com");
        Session.setUser(user);
        assertEquals("Teszt", Session.getUser().getUsername());
    }
}
