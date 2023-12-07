package entity_testing;

import entity.Review;
import entity.ReviewList;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReviewListTest {

    private ReviewList reviewList;
    private User user;
    private Review review1;
    private Review review2;

    @BeforeEach
    public void setUp() {
        reviewList = new ReviewList();
        user = new User("userID1", "JohnDoe", "password", "Location", LocalDateTime.now());
        review1 = new Review("reviewID1", user, "restaurantID1", 4.5f, "Great service", LocalDateTime.now());
        review2 = new Review("reviewID2", user, "restaurantID2", 3.0f, "Average experience", LocalDateTime.now());
    }

    @Test
    public void testAddAndGetReviewList() {
        reviewList.add(review1);
        reviewList.add(review2);

        List<Review> expected = new ArrayList<>();
        expected.add(review1);
        expected.add(review2);

        List<Review> actual = reviewList.getReviewlist();

        assertEquals(expected.size(), actual.size());
        assertTrue(expected.containsAll(actual) && actual.containsAll(expected));
    }

    @Test
    public void testRemoveReview() {
        reviewList.add(review1);
        reviewList.add(review2);

        reviewList.remove(review1);

        List<Review> expected = new ArrayList<>();
        expected.add(review2);

        List<Review> actual = reviewList.getReviewlist();

        assertEquals(expected.size(), actual.size());
        assertTrue(expected.containsAll(actual) && actual.containsAll(expected));
    }

    @Test
    void testToString() {
        ReviewList reviewList = new ReviewList();

        Review review1 = new Review("1", new User("1", "Alice", "password", "Location", LocalDateTime.now()), "101", 4.5f, "Great food!", LocalDateTime.now());
        Review review2 = new Review("2", new User("2", "Bob", "password", "Location", LocalDateTime.now()), "102", 3.5f, "Nice ambiance.", LocalDateTime.now());

        reviewList.add(review1);
        reviewList.add(review2);

        String expectedString = review1.toString() + "," + review2.toString();
        String actualString = reviewList.toString();

        assertEquals(expectedString, actualString);
    }
}
