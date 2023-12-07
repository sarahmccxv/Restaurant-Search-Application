package factory_testing;

import entity.YelpUser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class YelpUserFactoryTest {

    @Test
    public void testYelpUserCreation() {
        // Create sample data for a YelpUser
        String userID = "userID123";
        String username = "YelpUserName";

        // Create an instance of YelpUser
        YelpUser yelpUser = new YelpUser(userID, username);

        // Perform assertions to test YelpUser creation
        assertNotNull(yelpUser);
        assertEquals(userID, yelpUser.getUserID());
        assertEquals(username, yelpUser.getUsername());
    }
}
