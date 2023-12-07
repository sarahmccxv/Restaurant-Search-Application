package entity_testing;

import entity.Review;
import entity.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReviewTest {

    @Test
    public void testReviewAuthor() {
        User user = new User("userID123", "JohnDoe", "password", "Location", LocalDateTime.now());
        Review review = new Review("reviewID123", user, "restaurantID456", 4.5f, "Great food!", LocalDateTime.now());

        assertEquals(user, review.getAuthor(), "Review author should match the user who created it");
    }

    @Test
    public void testReviewContent() {
        User user = new User("userID123", "JohnDoe", "password", "Location", LocalDateTime.now());
        Review review = new Review("reviewID123", user, "restaurantID456", 4.5f, "Great food!", LocalDateTime.now());

        assertEquals("Great food!", review.getContent(), "Review content should match the provided content");
    }

    @Test
    public void testReviewRating() {
        User user = new User("userID123", "JohnDoe", "password", "Location", LocalDateTime.now());
        Review review = new Review("reviewID123", user, "restaurantID456", 4.5f, "Great food!", LocalDateTime.now());

        assertEquals(4.5f, review.getRating(), 0.01f, "Review rating should match the provided rating");
    }
}
