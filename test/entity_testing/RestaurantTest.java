package entity_testing;

import entity.Restaurant;
import entity.Review;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class RestaurantTest {

    @Test
    void testRestaurantInitialization() {
        ArrayList<String> categories = new ArrayList<>();
        categories.add("Italian");
        Restaurant restaurant = new Restaurant("101", "Pizza Palace", "123 Main St", "123-456-7890", categories, "image-url");

        assertNotNull(restaurant);
        assertEquals("101", restaurant.getRestaurantID());
        assertEquals("Pizza Palace", restaurant.getRestaurantName());
        assertEquals("123 Main St", restaurant.getAddress());
        assertEquals("123-456-7890", restaurant.getPhoneNumber());
        assertEquals(categories, restaurant.getCategories());
        assertEquals("image-url", restaurant.getImageURL());
        assertEquals(0, restaurant.getReviewSize());
        assertNotNull(restaurant.getReviews());
    }

    @Test
    void testAddReview() {
        ArrayList<String> categories = new ArrayList<>();
        categories.add("Italian");
        Restaurant restaurant = new Restaurant("101", "Pizza Palace", "123 Main St", "123-456-7890", categories, "image-url");

        Review review = new Review("1", null, "101", 4.5f, "Great pizza!", null);
        restaurant.addReviewandRating(review);

        assertEquals(1, restaurant.getReviewSize());
        assertTrue(restaurant.getReviews().contains(review));
    }

    @Test
    void testToString() {
        ArrayList<String> categories = new ArrayList<>();
        categories.add("Italian");
        Restaurant restaurant = new Restaurant("101", "Pizza Palace", "123 Main St", "123-456-7890", categories, "image-url");

        String expectedString = "Pizza Palace\nPhone Number: 123-456-7890\nAddress: 123 Main St";
        assertEquals(expectedString, restaurant.toString());
    }
}
