package api_testing.ParserTesting;

import api.Parser.*;
import entity.*;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JSONParserTest {

    private JSONParser jsonParser;

    @BeforeEach
    public void setUp() {
        jsonParser = new JSONParser();
    }

    @Test
    public void testParseMultipleRestaurant() {
        String jsonResponse = "{\"businesses\": [" +
                "{\"id\": \"1\", \"name\": \"Restaurant A\", \"display_phone\": \"123-456\", \"image_url\": \"urlA\"," +
                "\"location\": {\"display_address\": [\"Address A\", \"City A\"]}, " +
                "\"categories\": [{\"title\": \"Category A\"}, {\"title\": \"Category B\"}]}," +
                "{\"id\": \"2\", \"name\": \"Restaurant B\", \"display_phone\": \"789-012\", \"image_url\": \"urlB\"," +
                "\"location\": {\"display_address\": [\"Address B\", \"City B\"]}, " +
                "\"categories\": [{\"title\": \"Category C\"}, {\"title\": \"Category D\"}]}]}";

        JSONObject jsonObject = new JSONObject(jsonResponse);

        // Mock the RestaurantFactory
        RestaurantFactory restaurantFactory = mock(RestaurantFactory.class);
        when(restaurantFactory.create("1", "Restaurant A", "Address A, City A", "123-456",
                new ArrayList<>(List.of("Category A", "Category B")), "urlA"))
                .thenReturn(new Restaurant("1", "Restaurant A", "Address A, City A", "123-456",
                        new ArrayList<>(List.of("Category A", "Category B")), "urlA"));

        when(restaurantFactory.create("2", "Restaurant B", "Address B, City B", "789-012",
                new ArrayList<>(List.of("Category C", "Category D")), "urlB"))
                .thenReturn(new Restaurant("2", "Restaurant B", "Address B, City B", "789-012",
                        new ArrayList<>(List.of("Category C", "Category D")), "urlB"));

        // Create an instance of MultipleRestaurantsParser
        MultipleRestaurantsParser parser = new MultipleRestaurantsParser();

        // Parse the JSON and test the result
        ArrayList<Restaurant> restaurants = parser.parseFrom(jsonObject);

        assertEquals(2, restaurants.size(), "Should parse two restaurants");

        Restaurant restaurantA = restaurants.get(0);
        assertEquals("1", restaurantA.getRestaurantID(), "Restaurant A ID should match");
        assertEquals("Restaurant A", restaurantA.getRestaurantName(), "Restaurant A name should match");
        assertEquals("Address A, City A", restaurantA.getAddress(), "Restaurant A address should match");
        assertEquals("123-456", restaurantA.getPhoneNumber(), "Restaurant A phone number should match");
        assertEquals(2, restaurantA.getCategories().size(), "Restaurant A should have two categories");
        assertEquals("urlA", restaurantA.getImageURL(), "Restaurant A image URL should match");

        Restaurant restaurantB = restaurants.get(1);
        assertEquals("2", restaurantB.getRestaurantID(), "Restaurant B ID should match");
        assertEquals("Restaurant B", restaurantB.getRestaurantName(), "Restaurant B name should match");
        assertEquals("Address B, City B", restaurantB.getAddress(), "Restaurant B address should match");
        assertEquals("789-012", restaurantB.getPhoneNumber(), "Restaurant B phone number should match");
        assertEquals(2, restaurantB.getCategories().size(), "Restaurant B should have two categories");
        assertEquals("urlB", restaurantB.getImageURL(), "Restaurant B image URL should match");
    }

    @Test
    public void testParseSingleRestaurant() {
                String jsonResponse = "{\"id\": \"1\", \"name\": \"Restaurant A\", \"display_phone\": \"123-456\", \"image_url\": \"urlA\"," +
                "\"location\": {\"display_address\": [\"Address A\", \"City A\"]}, " +
                "\"categories\": [{\"title\": \"Category A\"}, {\"title\": \"Category B\"}]}";

        JSONObject jsonObject = new JSONObject(jsonResponse);

        // Mock the RestaurantFactory
        RestaurantFactory restaurantFactory = mock(RestaurantFactory.class);
        when(restaurantFactory.create("1", "Restaurant A", "Address A, City A", "123-456",
                new ArrayList<>(List.of("Category A", "Category B")), "urlA"))
                .thenReturn(new Restaurant("1", "Restaurant A", "Address A, City A", "123-456",
                        new ArrayList<>(List.of("Category A", "Category B")), "urlA"));

        // Create an instance of SingleRestaurantParser
        SingleRestaurantParser parser = new SingleRestaurantParser();

        // Parse the JSON and test the result
        Restaurant restaurant = parser.parseFrom(jsonObject);

        assertEquals("1", restaurant.getRestaurantID(), "Restaurant ID should match");
        assertEquals("Restaurant A", restaurant.getRestaurantName(), "Restaurant name should match");
        assertEquals("Address A, City A", restaurant.getAddress(), "Restaurant address should match");
        assertEquals("123-456", restaurant.getPhoneNumber(), "Restaurant phone number should match");
        assertEquals(2, restaurant.getCategories().size(), "Restaurant should have two categories");
        assertEquals("urlA", restaurant.getImageURL(), "Restaurant image URL should match");
    }


    @Test
    public void testParseReviews() {
        String jsonResponse = "{\"reviews\": [" +
                "{\"id\": \"1\", \"user\": {\"id\": \"u1\", \"name\": \"User A\"}, \"rating\": 4.5, \"text\": \"Great food!\", \"time_created\": \"2023-01-15 18:30:00\"}," +
                "{\"id\": \"2\", \"user\": {\"id\": \"u2\", \"name\": \"User B\"}, \"rating\": 3.0, \"text\": \"Nice ambiance!\", \"time_created\": \"2023-01-16 12:45:00\"}" +
                "]}";

        JSONObject jsonObject = new JSONObject(jsonResponse);

        // Mock the YelpReviewFactory and YelpUserFactory
        YelpReviewFactory reviewFactory = mock(YelpReviewFactory.class);
        YelpUserFactory userFactory = mock(YelpUserFactory.class);

        YelpUser userA = new YelpUser("u1", "User A");
        YelpUser userB = new YelpUser("u2", "User B");

        when(userFactory.create("u1", "User A")).thenReturn(userA);
        when(userFactory.create("u2", "User B")).thenReturn(userB);

        when(reviewFactory.create("1", userA, "restaurantID", 4.5f, "Great food!", LocalDateTime.parse("2023-01-15 18:30:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))))
                .thenReturn(new YelpReview("1", userA, "restaurantID", 4.5f, "Great food!", LocalDateTime.parse("2023-01-15 18:30:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));

        when(reviewFactory.create("2", userB, "restaurantID", 3.0f, "Nice ambiance!", LocalDateTime.parse("2023-01-16 12:45:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))))
                .thenReturn(new YelpReview("2", userB, "restaurantID", 3.0f, "Nice ambiance!", LocalDateTime.parse("2023-01-16 12:45:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));

        // Create an instance of ReviewsParser
        ReviewsParser reviewsParser = new ReviewsParser();

        // Parse the JSON and test the result
        ArrayList<YelpReview> reviews = reviewsParser.parseFrom(jsonObject, "restaurantID");

        assertEquals(2, reviews.size(), "Should parse two reviews");

        YelpReview review1 = reviews.get(0);
        assertEquals("1", review1.getReviewID(), "Review 1 ID should match");
//        assertEquals(userA, review1.getAuthor(), "Review 1 user should match");
        assertEquals("restaurantID", review1.getRestaurantID(), "Review 1 restaurant ID should match");
        assertEquals(4.5f, review1.getRating(), 0.001, "Review 1 rating should match");
        assertEquals("Great food!", review1.getContent(), "Review 1 content should match");

        YelpReview review2 = reviews.get(1);
        assertEquals("2", review2.getReviewID(), "Review 2 ID should match");
//        assertEquals(userB, review2.getAuthor(), "Review 2 user should match");
        assertEquals("restaurantID", review2.getRestaurantID(), "Review 2 restaurant ID should match");
        assertEquals(3.0f, review2.getRating(), 0.001, "Review 2 rating should match");
        assertEquals("Nice ambiance!", review2.getContent(), "Review 2 content should match");
    }

    @Test
    public void testParseFromSuccess() {
        // Mock JSON response for an error
        String jsonResponse = "{\"error\": {\"description\": \"Resource not found\"}}";
        JSONObject jsonObject = new JSONObject(jsonResponse);

        // Create an instance of ExceptionParser
        ExceptionParser exceptionParser = new ExceptionParser();

        // Parse the JSON and test the result
        String errorDescription = exceptionParser.parseFrom(jsonObject);
        assertEquals("Resource not found", errorDescription, "Error description should match");
    }

    @Test
    public void testParseFromExceptionThrown() {
        // Mock JSON response without the "error" key
        String jsonResponse = "{\"other\": \"data\"}";
        JSONObject jsonObject = new JSONObject(jsonResponse);

        // Create an instance of ExceptionParser
        ExceptionParser exceptionParser = new ExceptionParser();

        // Ensure that parsing without the "error" key throws a RuntimeException
        assertThrows(RuntimeException.class, () -> exceptionParser.parseFrom(jsonObject),
                "Parsing without 'error' key should throw JSONException");
    }
}
