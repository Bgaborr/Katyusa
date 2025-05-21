import org.junit.jupiter.api.Test;
import org.models.Token;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TokenTest {

    @Test
    public void testTokenGettersAndSetters() {
        Token token = new Token(1,100,LocalDate.of(2024, 5, 20));
        assertEquals(1, token.getPlayerId(1));
        assertEquals(100, token.getTokens());
        assertEquals(LocalDate.of(2024, 5, 20), token.getLastDailyClaim());
    }

    @Test
    public void testTokenConstructor() {
        LocalDate date = LocalDate.of(2025, 5, 21);
        Token token = new Token(5, 250, date);

        assertEquals(5, token.getPlayerId(1));
        assertEquals(250, token.getTokens());
        assertEquals(date, token.getLastDailyClaim());
    }
}
