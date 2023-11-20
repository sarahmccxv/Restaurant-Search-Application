package api.Parser;

import entity.Restaurant;
import entity.Review;

import java.util.ArrayList;

public interface Parser {
    ArrayList<Restaurant> parseMultipleRestaurants(String response);
    Restaurant parseSingleRestaurant(String response);
//    ArrayList<Review> parseReviews(String response);
    String parseError(String response);
}