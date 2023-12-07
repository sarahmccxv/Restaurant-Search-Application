package factory_testing;

import entity.YelpReview;
import entity.YelpUser;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class YelpReviewFactoryTest {

    @Test
    public void testYelpReviewCreation() {
        // Create sample data for a YelpUser
        YelpUser yelpUser = new YelpUser("userID", "YelpUserName");

        // Create sample data for a YelpReview
        String reviewID = "123";
        String restaurantID = "456";
        Float rating = 4.5f;
        String content = "This restaurant is great on Yelp!";
        LocalDateTime creationTime = LocalDateTime.now();

        // Create an instance of YelpReview
        YelpReview yelpReview = new YelpReview(reviewID, yelpUser, restaurantID, rating, content, creationTime);

        // Perform assertions to test YelpReview creation
        assertNotNull(yelpReview);
        assertEquals(reviewID, yelpReview.getReviewID());
        assertEquals(yelpUser, yelpReview.getAuthor());
        assertEquals(restaurantID, yelpReview.getRestaurantID());
        assertEquals(rating, yelpReview.getRating());
        assertEquals(content, yelpReview.getContent());
        assertEquals(creationTime, yelpReview.getCreationTime());
    }
}

