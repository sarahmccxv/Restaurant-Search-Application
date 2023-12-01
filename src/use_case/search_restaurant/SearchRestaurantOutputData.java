package use_case.search_restaurant;

import entity.Restaurant;

import java.util.ArrayList;

public class SearchRestaurantOutputData {
    private final ArrayList<Restaurant> restaurants;
    private boolean useCaseFailed;
    private final String category;

    public SearchRestaurantOutputData(ArrayList<Restaurant> restaurants, boolean useCaseFailed, String category){
        this.restaurants = restaurants;
        this.useCaseFailed = useCaseFailed;
        this.category = category;
    }
    public ArrayList<Restaurant> getRestaurants(){
        return restaurants;
    }

    public String getCategory() {
        return category;
    }
}
