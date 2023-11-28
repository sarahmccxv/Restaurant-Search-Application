package data_access;

import api.Review.ReviewCriteria;
import api.Search.SearchCriteria;
import api.Review.ReviewCriteria;
import api.yelp.YelpAPI;
import api.yelp.YelpApiServices;
import entity.Restaurant;
import entity.YelpReview;
import use_case.search_restaurant.SearchRestaurantDataAccessInterface;
import use_case.sortandfilter.SortAndFilterRestaurantDataAccessInterface;
import use_case.view_restaurant.ViewRestaurantDataAccessInterface;

import java.util.ArrayList;

public class APIRestaurantDataAccessObject implements YelpApiServices, ViewRestaurantDataAccessInterface, SortAndFilterRestaurantDataAccessInterface, SearchRestaurantDataAccessInterface {

    private final YelpApiServices apiRestaurant;

    public APIRestaurantDataAccessObject() {
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

    @Override
    public ArrayList<Restaurant> getRestaurantByName(String location, String restaurantName) {
        return apiRestaurant.getRestaurantByName(location, restaurantName);
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