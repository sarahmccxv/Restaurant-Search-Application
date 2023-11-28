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
    private final String expectedRestaurantID = "r_BrIgzYcwo1NAuG9dLbpg";
    private final String expectedRestaurantName = "Pai Northern Thai Kitchen";
    private final String expectedRestaurantAddress = "18 Duncan Street, Toronto, ON M5H 3G8";
    private final String expectedPhoneNumber = "+14169014724";
    private final ArrayList<String> expectedRestaurantCategories = new ArrayList<>(List.of("Thai"));

    @Test
    void getRestaurantsTest() {
        SearchCriteria searchCriteria = new SearchCriteria.Builder()
//                .setName(expectedRestaurantName)
                .setLocation(location)
                .setLimit(5)
                .setSortingMethod(SearchSortingMethods.BEST_MATCH)
                .setPriceLevel(SearchPriceLevel.CHEAP)
                .setCategory("Japanese")
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
        assertEquals("Jess F.", reviews.get(0).getAuthor().getUsername());
        assertEquals("2023-11-07T07:15:08", reviews.get(0).getCreationTime().toString());
    }
}
