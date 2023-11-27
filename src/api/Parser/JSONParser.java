package api.Parser;

import entity.Restaurant;
import entity.YelpReview;
import org.json.JSONObject;

import java.util.ArrayList;

public class JSONParser implements Parser {
    @Override
    public ArrayList<Restaurant> parseMultipleRestaurants(String response) {
        return MultipleRestaurantsParser.parseFrom(new JSONObject(response));
    }

    @Override
    public Restaurant parseSingleRestaurant(String response) {
        return SingleRestaurantParser.parseFrom(new JSONObject(response));
    }

    @Override
    public ArrayList<YelpReview> parseReviews(String response, String restaurantID) {
        return ReviewsParser.parseFrom(new JSONObject(response), restaurantID);
    }

    @Override
    public String parseError(String response) {
        return ExceptionParser.parseFrom(new JSONObject(response));
    }
}
