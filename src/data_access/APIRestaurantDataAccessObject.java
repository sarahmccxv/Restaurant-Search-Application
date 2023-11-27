package data_access;

import api.Review.ReviewCriteria;
import api.Search.SearchCriteria;
import api.yelp.YelpAPI;
import api.yelp.YelpApiServices;
import entity.Restaurant;
import entity.YelpReview;
import use_case.view_restaurant.ViewRestaurantDataAccessInterface;

import java.util.ArrayList;

public class APIRestaurantDataAccessObject implements YelpApiServices, ViewRestaurantDataAccessInterface {

    private final YelpApiServices apiRestaurant;
    public APIRestaurantDataAccessObject(){
        this.apiRestaurant = new YelpAPI();
    }

    @Override
    public ArrayList<Restaurant> getRestaurants(SearchCriteria criteria) {
        return null;
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
    public ArrayList<YelpReview> getReviews(ReviewCriteria criteria) {
        return apiRestaurant.getReviews(criteria);
    }

    @Override
    public ArrayList<YelpReview> getReviewsByID(String id) {
        return apiRestaurant.getReviewsByID(id);
    }
}
