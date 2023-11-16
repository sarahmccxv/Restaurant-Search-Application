package api;

import entity.Restaurant;
import entity.Review;

import java.util.ArrayList;

public interface ApiRestaurantInterface {
    public ArrayList<Restaurant> getLocalRestaurants(String locationName);
    public Restaurant getRestaurantByID(String restaurantID);
    public Restaurant getRestaurantByPhoneNumber(String phoneNumber);
//    public Review getReviews(String restaurantID);
}
