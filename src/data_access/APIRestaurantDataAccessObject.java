package data_access;

import api.ApiRestaurant;
import api.ApiRestaurantInterface;
import entity.Restaurant;
import use_case.view_restaurant.ViewRestaurantDataAccessInterface;

import java.util.ArrayList;

public class APIRestaurantDataAccessObject implements ApiRestaurantInterface, ViewRestaurantDataAccessInterface {

    private final ApiRestaurant apiRestaurant;
    public APIRestaurantDataAccessObject(){
        this.apiRestaurant = new ApiRestaurant();
    }

    @Override
    public ArrayList<Restaurant> getLocalRestaurants(String locationName) {
        return apiRestaurant.getLocalRestaurants(locationName);
    }

    @Override
    public Restaurant getRestaurantByID(String restaurantID) {
        return apiRestaurant.getRestaurantByID(restaurantID);
    }

    @Override
    public Restaurant getRestaurantByPhoneNumber(String phoneNumber) {
        return apiRestaurant.getRestaurantByPhoneNumber(phoneNumber);
    }
}
