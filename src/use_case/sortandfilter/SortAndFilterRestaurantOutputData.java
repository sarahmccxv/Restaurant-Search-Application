package use_case.sortandfilter;

import entity.Restaurant;

import java.util.ArrayList;

public class SortAndFilterRestaurantOutputData {
    private final ArrayList<Restaurant> sortedRestaurants;
    private boolean useCaseFailed;

    public SortAndFilterRestaurantOutputData(ArrayList<Restaurant> sortedRestaurants, boolean useCaseFailed){
        this.sortedRestaurants = sortedRestaurants;
        this.useCaseFailed = useCaseFailed;
    }
    public ArrayList<Restaurant> getRestaurants(){
        return sortedRestaurants;
    }
}
