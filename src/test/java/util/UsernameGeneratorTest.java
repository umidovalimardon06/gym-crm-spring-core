package util;

import gym.utility.UsernameGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsernameGeneratorTest {
    private UsernameGenerator generator;

    @BeforeEach
    void setUp() {
        generator = new UsernameGenerator();
    }

    @Test
    void generateUsername_noCollision_returnsBase() {
        Set<String> existing = new HashSet<>();
        String username = generator.generateUsername("John", "Doe", existing);
        assertEquals("John.Doe", username);
    }

    @Test
    void generateUsername_oneCollision_appendsOne() {
        Set<String> existing = new HashSet<>();
        existing.add("John.Doe");
        String username = generator.generateUsername("John", "Doe", existing);
        assertEquals("John.Doe1", username);
    }

    @Test
    void generateUsername_multipleCollisions_incrementsSuffix() {
        Set<String> existing = new HashSet<>();
        existing.add("John.Doe");
        existing.add("John.Doe1");
        existing.add("John.Doe2");
        String username = generator.generateUsername("John", "Doe", existing);
        assertEquals("John.Doe3", username);
    }
}