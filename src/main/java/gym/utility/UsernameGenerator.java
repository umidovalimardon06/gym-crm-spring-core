package gym.utility;

import org.springframework.stereotype.Component;

@Component
public class UsernameGenerator {
    public String generateUsername(String firstName,String lastname) {
        return firstName + "." + lastname;
    }
}