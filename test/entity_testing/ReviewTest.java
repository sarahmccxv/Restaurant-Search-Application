package entity_testing;

import entity.Review;
import entity.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReviewTest {
    @Test
    public void testReviewConstructor(){
        User user = new User("100000", "user", "123", "Toronto", LocalDateTime.now());
        Review review = new Review("100001", user, "rest001", 5f, "Good",
                LocalDateTime.now());
        assertEquals("100001", review.getReviewID());
    }
}
