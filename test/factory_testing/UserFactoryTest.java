package factory_testing;


import entity.Restaurant;
import entity.Review;
import entity.User;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;


public class UserFactoryTest {

    private User user;
    private Restaurant restaurant;
    private Review review;

    @BeforeEach
    public void setup() {
        // Sample user data
        String userID = "user123";
        String username = "JohnDoe";
        String password = "password";
        String location = "New York";
        LocalDateTime creationTime = LocalDateTime.now();

        // Sample restaurant data
        restaurant = new Restaurant("123", "Sample Restaurant", "123 Main St",
                "+1234567890", new ArrayList<>(), "https://sampleurl.com/image.jpg");

        // Sample review data
        review = new Review("456", user, restaurant.getRestaurantID(), 4.0f,
                "Great experience!", LocalDateTime.now());

        // Create a user for each test
        user = new User(userID, username, password, location, creationTime);
    }

    @Test
    public void testUserCreation() {
        assertNotNull(user);
        assertEquals("user123", user.getUserID());
        assertEquals("JohnDoe", user.getUsername());
        assertEquals("password", user.getPassword());
        assertEquals("New York", user.getLocation());
        assertNotNull(user.getCreationTime());
    }

    @Test
    public void testFavouritesList() {
        assertFalse(user.getFavouritesList().contains(restaurant.getRestaurantID()));
        user.addToFavourites(restaurant);
        assertTrue(user.getFavouritesList().contains(restaurant.getRestaurantID()));
        user.removeFavourite(restaurant.getRestaurantID());
        assertFalse(user.getFavouritesList().contains(restaurant.getRestaurantID()));
    }

//    @Test
//    public void testReviewsList() {
//        assertFalse(user.getReviewsList().contains(review));
//        user.addReview(review);
//        assertTrue(user.getReviewsList().contains(review));
//        user.removeReview(review);
//        assertFalse(user.getReviewsList().contains(review));
//    }
}

