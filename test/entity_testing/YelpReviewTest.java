package entity_testing;

import entity.YelpReview;
import entity.YelpUser;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class YelpReviewTest {

    @Test
    void testGetYelpReviewAuthor() {
        YelpUser author = new YelpUser("u1", "John Doe");
        YelpReview yelpReview = new YelpReview("r1", author, "restaurantID", 4.5f,
                                              "Great food!", LocalDateTime.now());

        assertEquals(author, yelpReview.getAuthor());
    }

    @Test
    void testGetYelpReviewToString() {
        YelpUser author = new YelpUser("u1", "John Doe");
        LocalDateTime now = LocalDateTime.now();
        YelpReview yelpReview = new YelpReview("r1", author, "restaurantID", 4.5f,
                                              "Great food!", now);

        String expected = "r1\n User: u1, John Doe\n Rating: 4.5\n CreationTime: " + now;
        assertEquals(expected, yelpReview.toString());
    }
}
