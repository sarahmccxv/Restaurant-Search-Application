package entity_testing;

import entity.YelpUser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class YelpUserTest {

    @Test
    void testGetYelpUserUsername() {
        YelpUser yelpUser = new YelpUser("u1", "John Doe");

        assertEquals("John Doe", yelpUser.getUsername());
    }

    @Test
    void testGetYelpUserUserID() {
        YelpUser yelpUser = new YelpUser("u1", "John Doe");

        assertEquals("u1", yelpUser.getUserID());
    }
}
