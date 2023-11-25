package api.yelp;

import api.Search.SearchCriteria;
import entity.Restaurant;
import entity.Review;

import java.util.ArrayList;

public interface YelpApiServices {
    ArrayList<Restaurant> getRestaurants(SearchCriteria criteria);
    ArrayList<Restaurant> getLocalRestaurants(String location);
    Restaurant getRestaurantByID(String id);
    ArrayList<Restaurant> getRestaurantByName(String location, String restaurantName);

}
