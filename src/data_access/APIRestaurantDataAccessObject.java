package data_access;

import api.Search.SearchCriteria;
import api.yelp.YelpAPI;
import api.yelp.YelpApiServices;
import entity.Restaurant;
import use_case.sortandfilter.SortAndFilterRestaurantDataAccessInterface;
import use_case.view_restaurant.ViewRestaurantDataAccessInterface;

import java.util.ArrayList;

public class APIRestaurantDataAccessObject implements YelpApiServices, ViewRestaurantDataAccessInterface, SortAndFilterRestaurantDataAccessInterface {

    private final YelpApiServices apiRestaurant;
    public APIRestaurantDataAccessObject(){
        this.apiRestaurant = new YelpAPI();
    }

    @Override
    public ArrayList<Restaurant> getRestaurants(SearchCriteria criteria) {
        return apiRestaurant.getRestaurants(criteria);
    }

    @Override
    public ArrayList<Restaurant> getLocalRestaurants(String locationName) {
        return apiRestaurant.getLocalRestaurants(locationName);
    }

    @Override
    public Restaurant getRestaurantByID(String restaurantID) {
        return apiRestaurant.getRestaurantByID(restaurantID);
    }

}
