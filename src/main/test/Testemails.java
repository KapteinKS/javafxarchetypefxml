import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Testemails {
    @Test
    public void testValidEmails() {
        assertTrue(PersonValidator.email("henrik.lieng@oslomet.no"));
        assertTrue(PersonValidator.email("example@example.com"));
        assertTrue(PersonValidator.email("uk@domain.co.uk"));
    }

    @Test
    public void testInvalidEmails() {
        assertFalse(PersonValidator.email(""));
        assertFalse(PersonValidator.email("henrik.lieng"));
        assertFalse(PersonValidator.email("@oslomet.no"));
        assertFalse(PersonValidator.email("henrik.lieng@invalid"));
        assertFalse(PersonValidator.email("test@"));
        assertFalse(PersonValidator.email(";bot@evil.com"));

    }
}
