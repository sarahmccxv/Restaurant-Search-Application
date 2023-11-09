package data_access;

import entity.Restaurant;
import entity.Review;

import java.util.ArrayList;

public interface RestaurantDB {
    public ArrayList<Restaurant> getLocalRestaurant(String locationName);
    public Restaurant getRestaurant(String restaurantID);
    public Review getReviews(String restaurantID);
}
