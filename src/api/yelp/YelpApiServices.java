package api.yelp;

import api.Review.ReviewCriteria;
import api.Search.SearchCriteria;
import entity.Restaurant;
import entity.Review;
import entity.YelpReview;

import java.util.ArrayList;

public interface YelpApiServices {
    ArrayList<Restaurant> getRestaurants(SearchCriteria criteria);
    ArrayList<Restaurant> getLocalRestaurants(String location);
    Restaurant getRestaurantByID(String id);
    ArrayList<YelpReview> getReviews(ReviewCriteria reviewCriteria);
}
