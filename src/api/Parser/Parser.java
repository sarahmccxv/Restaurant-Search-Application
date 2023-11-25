package api.Parser;

import entity.Restaurant;
import entity.Review;
import entity.YelpReview;

import java.util.ArrayList;

public interface Parser {
    ArrayList<Restaurant> parseMultipleRestaurants(String response);
    Restaurant parseSingleRestaurant(String response);
    ArrayList<YelpReview> parseReviews(String response, String restaurantID);
    String parseError(String response);
}