package entity_testing;

import entity.CommonReview;
import entity.CommonUser;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class CommonReviewTest {

    @Test
    void testCommonReviewInitialization() {
        CommonUser author = new CommonUser("userID", "username");
        LocalDateTime creationTime = LocalDateTime.now();
        CommonReview commonReview = new CommonReview("1", author, "101", 4.5f, "Great experience!", creationTime);

        assertNotNull(commonReview);
        assertEquals("1", commonReview.getReviewID());
        assertEquals(author, commonReview.getAuthor());
        assertEquals("101", commonReview.getRestaurantID());
        assertEquals(4.5f, commonReview.getRating());
        assertEquals("Great experience!", commonReview.getContent());
        assertEquals(creationTime, commonReview.getCreationTime());
    }

    @Test
    void testToString() {
        CommonUser author = new CommonUser("userID", "username");
        LocalDateTime creationTime = LocalDateTime.now();
        CommonReview commonReview = new CommonReview("1", author, "101", 4.5f, "Great experience!", creationTime);

        String expectedString = "1\n User: userID, username\n Rating: 4.5\n CreationTime: " + creationTime;
        assertEquals(expectedString, commonReview.toString());
    }
}
