package entity;

import java.time.LocalDateTime;

public class UserFactory {
    public User create(String userID, String username, String password, String location,
                       LocalDateTime creationTime) {
        return new User(userID, username, password, location, creationTime);
    }
}
