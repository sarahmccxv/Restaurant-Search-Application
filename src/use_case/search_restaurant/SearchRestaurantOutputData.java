package use_case.search_restaurant;

import entity.Restaurant;

import java.util.ArrayList;

public class SearchRestaurantOutputData {
    private final ArrayList<Restaurant> restaurants;
    private boolean useCaseFailed;
    private final String restaurantName;

    public SearchRestaurantOutputData(ArrayList<Restaurant> restaurants, boolean useCaseFailed, String restaurantName){
        this.restaurants = restaurants;
        this.useCaseFailed = useCaseFailed;
        this.restaurantName = restaurantName;
    }
    public ArrayList<Restaurant> getRestaurants(){
        return restaurants;
    }

    public String getRestaurantName() {
        return restaurantName;
    }
}
