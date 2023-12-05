package factory_testing;

import entity.Review;
import entity.User;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class ReviewFactoryTest {

    @Test
    public void testReviewCreation() {
        // Create sample user for the review
        User author = new User("userID", "JohnDoe", "password", "New York", LocalDateTime.now());

        // Sample review data
        String reviewID = "123";
        String restaurantID = "456";
        Float rating = 4.5f;
        String content = "This is a sample review.";
        LocalDateTime creationTime = LocalDateTime.now();

        // Create an instance of Review
        Review review = new Review(reviewID, author, restaurantID, rating, content, creationTime);

        // Perform assertions to test review creation
        assertNotNull(review);
        assertEquals(reviewID, review.getReviewID());
        assertEquals(author, review.getAuthor());
        assertEquals(restaurantID, review.getRestaurantID());
        assertEquals(rating, review.getRating());
        assertEquals(content, review.getContent());
        assertEquals(creationTime, review.getCreationTime());
    }
}
