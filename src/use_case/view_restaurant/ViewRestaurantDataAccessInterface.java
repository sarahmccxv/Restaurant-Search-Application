package use_case.view_restaurant;

import entity.Restaurant;

import java.util.ArrayList;

public interface ViewRestaurantDataAccessInterface {
    public ArrayList<Restaurant> getLocalRestaurants(String locationName);
    public Restaurant getRestaurantByID(String restaurantID);
    public Restaurant getRestaurantByPhoneNumber(String phoneNumber);
}
