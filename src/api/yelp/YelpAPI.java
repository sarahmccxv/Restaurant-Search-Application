package api.yelp;

import api.Search.SearchCriteria;
import api.response.ExceptionResponse;
import api.response.MultipleRestaurantResponse;
import api.response.SingleRestaurantResponse;
import entity.Restaurant;
import okhttp3.Response;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class YelpAPI {
    private static final YelpURLs yelpURLs = new YelpURLs();
    private static final YelpAPIClient yelpAPIClient = new YelpAPIClient();

    public ArrayList<Restaurant> getLocalRestaurants(SearchCriteria criteria) {
        String url = yelpURLs.getURLByLocation(criteria);
        try (Response response = yelpAPIClient.makeGETRequest(url)) {
            if (response.code() == 200) {
                MultipleRestaurantResponse multipleRestaurantResponse = new MultipleRestaurantResponse(response.body().string());
                return multipleRestaurantResponse.getRestaurants();
            } else {
                ExceptionResponse exceptionResponse = new ExceptionResponse(response.body().string(), response.code(), url);
                throw exceptionResponse.getYelpException();
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public Restaurant getRestaurantByID(String id) {
        String url = yelpURLs.getURLByID(id);
        try (Response response = yelpAPIClient.makeGETRequest(url)) {
            if (response.code() == 200) {
                SingleRestaurantResponse singleRestaurantResponse = new SingleRestaurantResponse(response.body().string());
                return singleRestaurantResponse.getRestaurant();
            } else {
                ExceptionResponse exceptionResponse = new ExceptionResponse(response.body().string(), response.code(), url);
                throw exceptionResponse.getYelpException();
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

//    public ArrayList<Review> getReviews(String id) {}
}