package api_testing.YelpTesting;

import api.Review.ReviewCriteria;
import api.Search.SearchCriteria;
import api.yelp.YelpApiServices;
import entity.Restaurant;
import entity.YelpReview;
import entity.YelpUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


public class YelpAPITest {
    @Mock
    private YelpApiServices yelpApiServices; // mock the YelpApiServices interface
    private final String location = "Toronto";
    private final String expectedRestaurantID = "iGEvDk6hsizigmXhDKs2Vg";
    private final String expectedRestaurantName = "Seven Lives Tacos y Mariscos";
    private final String expectedRestaurantAddress = "72 Kensington Avenue, Toronto, ON M5T 2K1";
    private final String expectedPhoneNumber = "+1 416-393-4636";
    private final ArrayList<String> expectedRestaurantCategories = new ArrayList<>(List.of("Mexican"));
    private final String expectedImageURL = "https://s3-media2.fl.yelpcdn.com/bphoto/4opY5aNRjkYO_BAm4Lma1A/o.jpg";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // initialize the mock objects
    }

    @Test
    void getRestaurantsTest() {
        SearchCriteria searchCriteria = new SearchCriteria.Builder()
                .setLocation(location)
                .setLimit(1)
                .build();
        ArrayList<Restaurant> restaurantArrayList = new ArrayList<>(); // create a fake list of restaurants
        Restaurant restaurant = new Restaurant(expectedRestaurantID, expectedRestaurantName, expectedRestaurantAddress, expectedPhoneNumber, expectedRestaurantCategories, expectedImageURL);
        restaurantArrayList.add(restaurant); // add the fake restaurant to the fake list
        when(yelpApiServices.getRestaurants(searchCriteria)).thenReturn(restaurantArrayList); // stub the getRestaurants method to return the fake list

        assertEquals(expectedRestaurantID, restaurantArrayList.get(0).getRestaurantID());
        assertEquals(expectedRestaurantName, restaurantArrayList.get(0).getRestaurantName());
        assertEquals(expectedRestaurantAddress, restaurantArrayList.get(0).getAddress());
        assertEquals(expectedPhoneNumber, restaurantArrayList.get(0).getPhoneNumber());
        assertEquals(expectedRestaurantCategories, restaurantArrayList.get(0).getCategories());
    }

    @Test
    void getLocalRestaurantsTest() {
        ArrayList<Restaurant> restaurantArrayList = new ArrayList<>(); // create a fake list of restaurants
        Restaurant restaurant = new Restaurant(expectedRestaurantID, expectedRestaurantName, expectedRestaurantAddress, expectedPhoneNumber, expectedRestaurantCategories, expectedImageURL); // create a fake restaurant
        restaurantArrayList.add(restaurant); // add the fake restaurant to the fake list
        when(yelpApiServices.getLocalRestaurants(expectedRestaurantAddress)).thenReturn(restaurantArrayList); // stub the getLocalRestaurants method to return the fake list

        assertEquals(expectedRestaurantID, restaurantArrayList.get(0).getRestaurantID());
        assertEquals(expectedRestaurantName, restaurantArrayList.get(0).getRestaurantName());
        assertEquals(expectedRestaurantAddress, restaurantArrayList.get(0).getAddress());
        assertEquals(expectedPhoneNumber, restaurantArrayList.get(0).getPhoneNumber());
        assertEquals(expectedRestaurantCategories, restaurantArrayList.get(0).getCategories());
    }

    @Test
    void getRestaurantByIDTest() {
        Restaurant restaurant = new Restaurant(expectedRestaurantID, expectedRestaurantName, expectedRestaurantAddress, expectedPhoneNumber, expectedRestaurantCategories, expectedImageURL); // create a fake restaurant
        when(yelpApiServices.getRestaurantByID(expectedRestaurantID)).thenReturn(restaurant); // stub the getRestaurantByID method to return the fake restaurant

        assertEquals(expectedRestaurantName, restaurant.getRestaurantName());
        assertEquals(expectedRestaurantAddress, restaurant.getAddress());
        assertEquals(expectedPhoneNumber, restaurant.getPhoneNumber());
        assertEquals(expectedRestaurantCategories, restaurant.getCategories());
    }

    @Test
    void getReviewsTest() {
        ReviewCriteria reviewCriteria = new ReviewCriteria.Builder()
                .setRestaurantID(expectedRestaurantID)
                .setLimit(1)
                .build();

        ArrayList<YelpReview> reviews = new ArrayList<>();
        YelpUser author = new YelpUser("", "Euan S.");
        LocalDateTime creationTime = LocalDateTime.now();
        YelpReview review = new YelpReview(null, author, expectedRestaurantID, null, null, creationTime); // create a fake review
        reviews.add(review);
        when(yelpApiServices.getReviews(reviewCriteria)).thenReturn(reviews); // stub the getReviews method to return the fake list

        assertEquals(expectedRestaurantID, reviews.get(0).getRestaurantID());
        assertEquals("Lina H.", reviews.get(0).getAuthor().getUsername());
        assertEquals("2023-12-04T08:20:46", reviews.get(0).getCreationTime().toString());
        assertEquals("Euan S.", reviews.get(0).getAuthor().getUsername());
        assertEquals(creationTime.toString(), reviews.get(0).getCreationTime().toString());
    }
}
