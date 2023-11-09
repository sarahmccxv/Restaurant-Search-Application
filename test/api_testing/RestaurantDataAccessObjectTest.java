package api_testing;

import entity.Restaurant;
import entity.RestaurantFactory;
import api.ApiRestaurant;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class RestaurantDataAccessObjectTest {
    private static ApiRestaurant restaurantDataAccessObject;
    private final String location = "Toronto";
    private final String expectedRestaurantID = "r_BrIgzYcwo1NAuG9dLbpg";
    private final String expectedRestaurantName = "Pai Northern Thai Kitchen";
    private final String expectedRestaurantAddress = "18 Duncan Street";
    private final String expectedPhoneNumber = "+14169014724";
    private final ArrayList<String> expectedRestaurantCategories = new ArrayList<>(List.of("Thai"));


    @BeforeAll
    static void SetUp() {
        restaurantDataAccessObject = new ApiRestaurant(new RestaurantFactory());
    }

    @Test
    void getLocalRestaurantTest() {
        ArrayList<Restaurant> restaurantArrayList = restaurantDataAccessObject.getLocalRestaurants(location);

        assertEquals(expectedRestaurantID, restaurantArrayList.get(0).getRestaurantID());
        assertEquals(expectedRestaurantName, restaurantArrayList.get(0).getRestaurantName());
        assertEquals(expectedRestaurantAddress, restaurantArrayList.get(0).getAddress());
        assertEquals(expectedPhoneNumber, restaurantArrayList.get(0).getPhoneNumber());
        assertEquals(expectedRestaurantCategories, restaurantArrayList.get(0).getCategories());
    }

    @Test
    void getRestaurantByIDTest() {
        Restaurant restaurant = restaurantDataAccessObject.getRestaurantByID(expectedRestaurantID);

        assertEquals(expectedRestaurantName, restaurant.getRestaurantName());
        assertEquals(expectedRestaurantAddress, restaurant.getAddress());
        assertEquals(expectedPhoneNumber, restaurant.getPhoneNumber());
        assertEquals(expectedRestaurantCategories, restaurant.getCategories());
    }

    @Test
    void getRestaurantByPhoneNumberTest() {
        Restaurant restaurant = restaurantDataAccessObject.getRestaurantByPhoneNumber(expectedPhoneNumber);

        assertEquals(expectedRestaurantID, restaurant.getRestaurantID());
        assertEquals(expectedRestaurantName, restaurant.getRestaurantName());
        assertEquals(expectedRestaurantAddress, restaurant.getAddress());
        assertEquals(expectedRestaurantCategories, restaurant.getCategories());
    }
}
