import gym.utility.PasswordGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PasswordGeneratorTest {
    @Mock
    private Random random;

    private static final String ALLOWED
            = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";

    @Test
    void shouldGeneratePasswordWithLength10() {
        PasswordGenerator generator = new PasswordGenerator();
        generator.setRandom(random);

        String password = generator.generatePassword();
        assertEquals(10, password.length());
    }

    @Test
    void shouldContainOnlyAllowedCharacters() {
        PasswordGenerator generator = new PasswordGenerator();
        generator.setRandom(random);

        String password = generator.generatePassword();
        assertTrue(password.matches("[A-Za-z0-9!@#$%^&*()]{10}"));
    }

    @Test
    void shouldGenerateDifferentPasswordsOnMultipleCalls() {
        PasswordGenerator generator = new PasswordGenerator();

        generator.setRandom(new java.util.Random());

        String p1 = generator.generatePassword();
        String p2 = generator.generatePassword();
        String p3 = generator.generatePassword();

        assertNotEquals(p1, p2);
        assertNotEquals(p2, p3);
    }

}