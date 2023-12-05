package api.yelp;

import api.Review.ReviewCriteria;
import api.Search.SearchCriteria;
import entity.Restaurant;
import entity.YelpReview;

import java.util.ArrayList;

public interface YelpApiServices {
    ArrayList<Restaurant> getRestaurants(SearchCriteria criteria);
    ArrayList<Restaurant> getLocalRestaurants(String location);
    Restaurant getRestaurantByID(String id);
    ArrayList<Restaurant> getRestaurantByName(String location, String restaurantName);
    ArrayList<YelpReview> getReviews(ReviewCriteria reviewCriteria);
    ArrayList<YelpReview> getReviewsByID(String id);




}


