package factory_testing;

import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserFactoryTest {

    @Test
    void createUser_ReturnsValidUser() {
        // Arrange
        UserFactory userFactory = new UserFactory();
        String userID = "123";
        String username = "JohnDoe";
        String password = "password";
        String location = "New York";
        LocalDateTime creationTime = LocalDateTime.now();

        // Act
        User user = userFactory.create(userID, username, password, location, creationTime);

        // Assert
        assertNotNull(user);
        assertEquals(userID, user.getUserID());
        assertEquals(username, user.getUsername());
        assertEquals(password, user.getPassword());
        assertEquals(location, user.getLocation());
        assertEquals(creationTime, user.getCreationTime());
    }
}
