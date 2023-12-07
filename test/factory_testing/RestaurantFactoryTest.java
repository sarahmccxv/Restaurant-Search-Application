package factory_testing;

import entity.RestaurantFactory;
import entity.Restaurant;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class RestaurantFactoryTest {

    @Test
    public void testCreateRestaurant() {
        // Create sample data for restaurant
        String restaurantID = "123";
        String restaurantName = "Sample Restaurant";
        String address = "123 Main St";
        String phoneNumber = "+1234567890";
        ArrayList<String> categories = new ArrayList<>();
        categories.add("Italian");
        categories.add("Fine Dining");
        String imageURL = "https://sampleurl.com/image.jpg";

        // Create an instance of RestaurantFactory
        RestaurantFactory restaurantFactory = new RestaurantFactory();

        // Create a restaurant using the factory
        Restaurant restaurant = restaurantFactory.create(restaurantID, restaurantName, address,
                phoneNumber, categories, imageURL);

        // Perform assertions to test if the restaurant was created correctly
        assertNotNull(restaurant);
        assertEquals(restaurantID, restaurant.getRestaurantID());
        assertEquals(restaurantName, restaurant.getRestaurantName());
        assertEquals(address, restaurant.getAddress());
        assertEquals(phoneNumber, restaurant.getPhoneNumber());
        assertEquals(categories, restaurant.getCategories());
        assertEquals(imageURL, restaurant.getImageURL());
    }
}
