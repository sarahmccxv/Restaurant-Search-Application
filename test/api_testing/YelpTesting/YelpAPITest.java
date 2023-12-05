package api_testing.YelpTesting;

import api.Review.ReviewCriteria;
import api.Search.SearchPriceLevel;
import api.Search.SearchSortingMethods;
import api.yelp.YelpApiServices;
import entity.Restaurant;
import api.Search.SearchCriteria;
import api.yelp.YelpAPI;

import static org.junit.jupiter.api.Assertions.*;

import entity.Review;
import entity.YelpReview;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class YelpAPITest {
    private static final YelpApiServices yelpApiServices = new YelpAPI();
    private final String location = "Toronto";
    private final String expectedRestaurantID = "iGEvDk6hsizigmXhDKs2Vg";
    private final String expectedRestaurantName = "Seven Lives Tacos y Mariscos";
    private final String expectedRestaurantAddress = "72 Kensington Avenue, Toronto, ON M5T 2K1";
    private final String expectedPhoneNumber = "+1 416-393-4636";
    private final ArrayList<String> expectedRestaurantCategories = new ArrayList<>(List.of("Mexican"));

    @Test
    void getRestaurantsTest() {
        SearchCriteria searchCriteria = new SearchCriteria.Builder()
//                .setName(expectedRestaurantName)
                .setLocation(location)
                .setLimit(1)
//                .setSortingMethod(SearchSortingMethods.BEST_MATCH)
//                .setPriceLevel(SearchPriceLevel.CHEAP)
                .build();
        ArrayList<Restaurant> restaurantArrayList = yelpApiServices.getRestaurants(searchCriteria);

        assertEquals(expectedRestaurantID, restaurantArrayList.get(0).getRestaurantID());
        assertEquals(expectedRestaurantName, restaurantArrayList.get(0).getRestaurantName());
        assertEquals(expectedRestaurantAddress, restaurantArrayList.get(0).getAddress());
        assertEquals(expectedPhoneNumber, restaurantArrayList.get(0).getPhoneNumber());
        assertEquals(expectedRestaurantCategories, restaurantArrayList.get(0).getCategories());
    }

    @Test
    void getLocalRestaurantsTest() {
        ArrayList<Restaurant> restaurantArrayList = yelpApiServices.getLocalRestaurants(expectedRestaurantAddress);

        assertEquals(expectedRestaurantID, restaurantArrayList.get(0).getRestaurantID());
        assertEquals(expectedRestaurantName, restaurantArrayList.get(0).getRestaurantName());
        assertEquals(expectedRestaurantAddress, restaurantArrayList.get(0).getAddress());
        assertEquals(expectedPhoneNumber, restaurantArrayList.get(0).getPhoneNumber());
        assertEquals(expectedRestaurantCategories, restaurantArrayList.get(0).getCategories());
    }

    @Test
    void getRestaurantByIDTest() {
        Restaurant restaurant = yelpApiServices.getRestaurantByID(expectedRestaurantID);

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

        ArrayList<YelpReview> reviews = yelpApiServices.getReviews(reviewCriteria);

        assertEquals(expectedRestaurantID, reviews.get(0).getRestaurantID());
        assertEquals("Euan S.", reviews.get(0).getAuthor().getUsername());
        assertEquals("2023-11-14T09:18:13", reviews.get(0).getCreationTime().toString());
    }
}
