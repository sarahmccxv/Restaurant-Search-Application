package api.yelp;

import api.Search.SearchCriteria;
import entity.Restaurant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface YelpApiServices {
    ArrayList<Restaurant> getLocalRestaurants(SearchCriteria criteria);
    Restaurant getRestaurantByID(String id);

    static ArrayList<String> getSortingMethods() {
        return new ArrayList<>(Arrays.asList("best_match", "rating", "review_count", "distance"));
    }
}
