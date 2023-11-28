package use_case.search_restaurant;

import entity.Restaurant;

import java.util.ArrayList;

public interface SearchRestaurantDataAccessInterface {

    ArrayList<Restaurant> getRestaurantByName(String location, String restaurantName);
}
