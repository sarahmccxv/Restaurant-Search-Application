package api_testing;

import api.yelp.YelpApiServices;
import entity.Restaurant;
import api.Search.SearchCriteria;
import api.yelp.YelpAPI;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class YelpAPITest {
    private static YelpApiServices yelpApiServices = new YelpAPI();
    private final String location = "Toronto";
    private final String expectedRestaurantID = "r_BrIgzYcwo1NAuG9dLbpg";
    private final String expectedRestaurantName = "Pai Northern Thai Kitchen";
    private final String expectedRestaurantAddress = "18 Duncan Street, Toronto, ON M5H 3G8";
    private final String expectedPhoneNumber = "+14169014724";
    private final ArrayList<String> expectedRestaurantCategories = new ArrayList<>(List.of("thai"));

//
//    @BeforeAll
//    static void SetUp() {
//        restaurantDataAccessObject = new ApiRestaurantSearch();
//    }

    @Test
    void getLocalRestaurantTest() {
        SearchCriteria searchCriteria = new SearchCriteria.Builder()
                .setLocation("Toronto")
                .setLimit(1)
                .setSortingMethod("best_match")
                .setCategory(new ArrayList<>(Arrays.asList("italian", "pizza")))
                .build();
        ArrayList<Restaurant> restaurantArrayList = yelpApiServices.getLocalRestaurants(searchCriteria);

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

//    @Test
//    void getRestaurantByPhoneNumberTest() {
//        Restaurant restaurant = restaurantDataAccessObject.getRestaurantByPhoneNumber(expectedPhoneNumber);
//
//        assertEquals(expectedRestaurantID, restaurant.getRestaurantID());
//        assertEquals(expectedRestaurantName, restaurant.getRestaurantName());
//        assertEquals(expectedRestaurantAddress, restaurant.getAddress());
//        assertEquals(expectedRestaurantCategories, restaurant.getCategories());
//    }
}
