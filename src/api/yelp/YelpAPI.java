package api.yelp;

import api.Parser.JSONParser;
import api.Search.SearchCriteria;
import entity.Restaurant;
import okhttp3.Response;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class YelpAPI {
    private static final YelpURLs yelpURLs = new YelpURLs();
    private static final YelpAPIClient yelpAPIClient = new YelpAPIClient();
    private final JSONParser parser = new JSONParser();

    public ArrayList<Restaurant> getLocalRestaurants(SearchCriteria criteria) {
        try (Response response = yelpAPIClient.makeGETRequest(yelpURLs.getURLByLocation(criteria))) {
            if (response.code() == 200) {
                return parser.parseMultipleRestaurants(response.body().string());
            } else {
                throw new RuntimeException(parser.parseError(response.body().string()));
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public Restaurant getRestaurantByID(String id) {
        try (Response response = yelpAPIClient.makeGETRequest(yelpURLs.getURLByID(id))) {
            if (response.code() == 200) {
                return parser.parseSingleRestaurant(response.body().string());
            } else {
                throw new RuntimeException(parser.parseError(response.body().string()));
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

//    public ArrayList<Review> getReviews(String id) {}
}