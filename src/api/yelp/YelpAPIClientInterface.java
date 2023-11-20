package api.yelp;

import api.Exception.YelpRequestException;
import api.Search.SearchCriteria;

public interface YelpAPIClientInterface {
    void allRestaurantsMatching(SearchCriteria criteria);
    void RestaurantIDMatching(String id);
    String getResponseBody();
    void getFrom(String API_URL) throws YelpRequestException;
}

