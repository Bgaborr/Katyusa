
import org.classes.UserValidator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SimpleUserValidatorTest {
    private final UserValidator validator = new UserValidator();

    @Test
    void emailTests() {
        assertAll("Érvénytelen email címek",
                () -> assertFalse(validator.isValidEmail(null)),
                () -> assertFalse(validator.isValidEmail("")),
                () -> assertFalse(validator.isValidEmail("invalid")),
                () -> assertFalse(validator.isValidEmail("invalid@")),
                () -> assertFalse(validator.isValidEmail("@domain.com"))
        );

        assertAll("Érvényes email címek",
                () -> assertTrue(validator.isValidEmail("a@b.c")),
                () -> assertTrue(validator.isValidEmail("user@example.com")),
                () -> assertTrue(validator.isValidEmail("first.last@domain.co"))
        );
    }

    @Test
    void passwordTests() {
        assertAll("Érvénytelen jelszavak",
                () -> assertFalse(validator.isValidPassword(null)),
                () -> assertFalse(validator.isValidPassword("")),
                () -> assertFalse(validator.isValidPassword("1234567"))
        );

        assertAll("Érvényes jelszavak",
                () -> assertTrue(validator.isValidPassword("12345678")),
                () -> assertTrue(validator.isValidPassword("password")),
                () -> assertTrue(validator.isValidPassword("!@#$%^&*"))
        );
    }
}