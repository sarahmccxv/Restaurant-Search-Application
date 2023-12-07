package entity_testing;

import entity.CommonUser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CommonUserTest {

    @Test
    void testCommonUserInitialization() {
        CommonUser commonUser = new CommonUser("userID123", "JohnDoe");

        assertNotNull(commonUser);
        assertEquals("userID123", commonUser.getUserID());
        assertEquals("JohnDoe", commonUser.getUsername());
    }
}
