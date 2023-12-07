package use_case.sortandfilter;
import api.Search.SearchCriteria;
import entity.Restaurant;
import entity.User;
import api.yelp.YelpApiServices;
import java.util.ArrayList;

public interface SortAndFilterRestaurantDataAccessInterface {
    ArrayList<Restaurant> getRestaurants(SearchCriteria criteria);

}
