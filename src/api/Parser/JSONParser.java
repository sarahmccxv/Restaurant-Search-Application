package api.Parser;

import entity.Restaurant;
import entity.YelpReview;
import org.json.JSONObject;

import java.util.ArrayList;

public class JSONParser implements Parser {
    private MultipleRestaurantsParser multipleRestaurantsParser;
    private SingleRestaurantParser singleRestaurantParser;
    private ReviewsParser reviewsParser;
    private ExceptionParser exceptionParser;

    public JSONParser() {
        multipleRestaurantsParser = new MultipleRestaurantsParser();
        singleRestaurantParser = new SingleRestaurantParser();
        reviewsParser = new ReviewsParser();
        exceptionParser = new ExceptionParser();
    }

    @Override
    public ArrayList<Restaurant> parseMultipleRestaurants(String response) {
        return multipleRestaurantsParser.parseFrom(new JSONObject(response));
    }

    @Override
    public Restaurant parseSingleRestaurant(String response) {
        return singleRestaurantParser.parseFrom(new JSONObject(response));
    }

    @Override
    public ArrayList<YelpReview> parseReviews(String response, String restaurantID) {
        return reviewsParser.parseFrom(new JSONObject(response), restaurantID);
    }

    @Override
    public String parseError(String response) {
        return exceptionParser.parseFrom(new JSONObject(response));
    }
}
