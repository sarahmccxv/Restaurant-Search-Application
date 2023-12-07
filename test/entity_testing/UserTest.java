package entity_testing;

import entity.Restaurant;
import entity.Review;
import entity.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    void testGetPassword() {
        User user = new User("u123", "Alice", "password123", "New York", LocalDateTime.now());
        assertEquals("password123", user.getPassword());
    }

    @Test
    void testGetLocation() {
        User user = new User("u123", "Alice", "password123", "New York", LocalDateTime.now());
        assertEquals("New York", user.getLocation());
    }

    @Test
    void testGetCreationTime() {
        LocalDateTime time = LocalDateTime.now();
        User user = new User("u123", "Alice", "password123", "New York", time);
        assertEquals(time, user.getCreationTime());
    }

    @Test
    void testSetLocation() {
        User user = new User("u123", "Alice", "password123", "New York", LocalDateTime.now());
        user.setLocation("San Francisco");
        assertEquals("San Francisco", user.getLocation());
    }

    @Test
    void testAddToFavourites() {
        User user = new User("u123", "Alice", "password123", "New York", LocalDateTime.now());
        Restaurant restaurant = new Restaurant("r1", "Restaurant A", "Address A", "123456789", null, null);
        user.addToFavourites(restaurant);
        assertTrue(user.getFavouritesList().contains("r1"));
    }

    @Test
    void testRemoveFavourite() {
        User user = new User("u123", "Alice", "password123", "New York", LocalDateTime.now());
        Restaurant restaurant = new Restaurant("r1", "Restaurant A", "Address A", "123456789", null, null);
        user.addToFavourites(restaurant);
        user.removeFavourite("r1");
        assertFalse(user.getFavouritesList().contains("r1"));
    }

    @Test
    void testAddReview() {
        User user = new User("u123", "Alice", "password123", "New York", LocalDateTime.now());
        Review review = new Review("review1", null, "r1", 4.5f, "Great food!", LocalDateTime.now());
        user.addReview(review);
        assertTrue(user.getReviewsList().contains(review.getReviewID()));
    }

    @Test
    void testRemoveReview() {
        User user = new User("u123", "Alice", "password123", "New York", LocalDateTime.now());
        Review review = new Review("review1", null, "r1", 4.5f, "Great food!", LocalDateTime.now());
        user.addReview(review);
        user.removeReview(review);
        assertFalse(user.getReviewsList().contains(review.getReviewID()));
    }
}
