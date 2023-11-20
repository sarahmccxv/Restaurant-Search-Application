package use_case.sortandfilter;
import entity.Restaurant;
import entity.User;

import java.util.ArrayList;

public interface SortAndFilterRestaurantDataAccessInterface {
    Restaurant get(String restaurantName);
    void saveSort(User user);
}
