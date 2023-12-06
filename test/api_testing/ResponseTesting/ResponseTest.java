package api_testing.ResponseTesting;

import api.Exception.YelpRequestException;
import api.response.ExceptionResponse;
import api.response.MultipleRestaurantResponse;
import api.response.Response;
import api.response.ReviewsResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ResponseTest {

    @Test
    public void testResponse() {
        String sampleResponse = "{\"name\": \"Sample Restaurant\", \"location\": \"Sample Location\"}";
        Response response = new Response(sampleResponse);
        assertEquals(sampleResponse, response.getResponse(), "Returned response should match provided response");
    }

    @Test
    public void testMultipleRestaurantResponse() {
        String sampleResponse = "{\"businesses\": []}";
        MultipleRestaurantResponse multipleRestaurantResponse = new MultipleRestaurantResponse(sampleResponse);
        assertNotNull(multipleRestaurantResponse.getRestaurants(), "Restaurants should not be null");
    }

    @Test
    public void testReviewsResponse() {
        String sampleResponse = "{\"reviews\": []}";
        ReviewsResponse reviewsResponse = new ReviewsResponse(sampleResponse);
        assertNotNull(reviewsResponse.getReviews("restaurantID"), "Reviews should not be null");
    }

//    @Test
//    public void testParseFrom() {
//        String sampleResponse = "{\"businesses\": [" +
//                "{\"id\": \"1\", \"name\": \"Restaurant A\", \"image_url\": \"urlA\", " +
//                "\"location\": {\"display_address\": [\"Address A\", \"City A\"]}, " +
//                "\"categories\": [{\"title\": \"Category A\"}, {\"title\": \"Category B\"}]}," +
//                "{\"id\": \"2\", \"name\": \"Restaurant B\", \"display_phone\": \"123-456\", \"image_url\": \"urlB\"," +
//                "\"location\": {\"display_address\": [\"Address B\", \"City B\"]}, " +
//                "\"categories\": [{\"title\": \"Category C\"}, {\"title\": \"Category D\"}]}" +
//                "]}";
//
//        // Create an instance of MultipleRestaurantsParser
//        MultipleRestaurantsParser parser = new MultipleRestaurantsParser();
//
//        // Parse the sample response
//        ArrayList<Restaurant> restaurants = parser.parseFrom(new JSONObject(sampleResponse));
//
//        // Verify the number of parsed restaurants and their details
//        assertEquals(2, restaurants.size(), "Should parse two restaurants");
//
//        // Verify details of the first restaurant
//        Restaurant restaurantA = restaurants.get(0);
//        assertEquals("1", restaurantA.getRestaurantID(), "Restaurant A ID should match");
//        assertEquals("Restaurant A", restaurantA.getRestaurantName(), "Restaurant A name should match");
//        assertEquals("", restaurantA.getPhoneNumber(), "Phone number should be empty");
//
//        // Verify details of the second restaurant
//        Restaurant restaurantB = restaurants.get(1);
//        assertEquals("2", restaurantB.getRestaurantID(), "Restaurant B ID should match");
//        assertEquals("Restaurant B", restaurantB.getRestaurantName(), "Restaurant B name should match");
//        assertEquals("123-456", restaurantB.getPhoneNumber(), "Phone number should match");
//    }

    @Test
    public void testExceptionResponse() {
        String sampleResponse = "{\"error\": {\"description\": \"Resource not found\"}}";
        ExceptionResponse exceptionResponse = new ExceptionResponse(sampleResponse, 404, "http://example.com");
        YelpRequestException yelpException = exceptionResponse.getYelpException();
    }
}
