import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Testnumbers {

    @Test
    public void testValidNumbers(){
        assertTrue(PersonValidator.phone("12233212"));
        assertTrue(PersonValidator.phone("+4712233212"));
        assertTrue(PersonValidator.phone("+447911123456"));
        assertTrue(PersonValidator.phone("7543010"));
        assertTrue(PersonValidator.phone("5417543010"));
        assertTrue(PersonValidator.phone("+15417543010"));
        assertTrue(PersonValidator.phone("15417543010"));
        assertTrue(PersonValidator.phone("0015417543010"));
    }

    @Test
    public void testInvalidNumbers(){
        assertFalse(PersonValidator.phone(""));
        assertFalse(PersonValidator.phone("Not a number"));
        assertFalse(PersonValidator.phone("-231"));
        assertFalse(PersonValidator.phone("123-norway"));
        assertFalse(PersonValidator.phone("1-541-æøå-3010"));
        assertFalse(PersonValidator.phone("1-541-abc-3010"));
        assertFalse(PersonValidator.phone("!%&/"));
        assertFalse(PersonValidator.phone("123 123     123 12"));
    }
}
