package gym.utility;

import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UsernameGenerator {
    public String generateUsername(String firstName, String lastName, Set<String> existing) {
        String base = firstName + "." + lastName;

        if (!existing.contains(base)) {
            return base;
        }

        int suffix = 1;
        while (existing.contains(base + suffix)) {
            suffix++;
        }
        return base + suffix;
    }
}