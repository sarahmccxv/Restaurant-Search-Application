package use_case.search_restaurant;

import entity.Restaurant;

public interface SearchRestaurantDataAccessInterface {
    boolean existsByName(String identifier);
    Restaurant get(String restaurantName);
}
