package use_case.search_restaurant;

import entity.Restaurant;

import java.util.ArrayList;

public class SearchRestaurantOutputData {
    private final ArrayList<Restaurant> restaurants;
    private boolean useCaseFailed;

    public SearchRestaurantOutputData(ArrayList<Restaurant> restaurants, boolean useCaseFailed){
        this.restaurants = restaurants;
        this.useCaseFailed = useCaseFailed;
    }
    public ArrayList<Restaurant> getRestaurants(){
        return restaurants;
    }
}
