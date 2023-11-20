package use_case.sortandfilter;

import entity.FavouritesList;
import entity.Restaurant;
import entity.User;

import java.util.ArrayList;

public interface SortAndFilterRestaurantDataAccessInterface {
    boolean existsByName(String identifier);
    Restaurant get(String restaurantName);
    void saveSort(User user);
}
