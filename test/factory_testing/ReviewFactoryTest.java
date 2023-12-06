package factory_testing;

import entity.Review;
import entity.ReviewFactory;
import entity.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ReviewFactoryTest {

    @Test
    void createReview_ReturnsValidReview() {
        // Arrange
        ReviewFactory reviewFactory = new ReviewFactory();
        String reviewID = "123";
        User author = new User("userID", "JohnDoe", "password", "New York", LocalDateTime.now());
        String restaurantID = "456";
        Float rating = 4.5f;
        String content = "Great place!";
        LocalDateTime creationTime = LocalDateTime.now();

        // Act
        Review review = reviewFactory.create(reviewID, author, restaurantID, rating, content, creationTime);

        // Assert
        assertNotNull(review);
        assertEquals(reviewID, review.getReviewID());
        assertEquals(author, review.getAuthor());
        assertEquals(restaurantID, review.getRestaurantID());
        assertEquals(rating, review.getRating());
        assertEquals(content, review.getContent());
        assertEquals(creationTime, review.getCreationTime());
    }
}
