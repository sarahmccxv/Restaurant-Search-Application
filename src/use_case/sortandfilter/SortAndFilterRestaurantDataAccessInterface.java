package use_case.sortandfilter;

import entity.Restaurant;

import java.util.ArrayList;

public interface SortAndFilterRestaurantDataAccessInterface {
    boolean existsByName(String identifier);
    Restaurant get(String restaurantName);
}
