package use_case.sortandfilter;

import api.Search.SearchCriteria;
import entity.Restaurant;

import java.util.ArrayList;

public class SortAndFilterRestaurantOutputData {
    private final SearchCriteria criteria;
    private final ArrayList<Restaurant> sortedRestaurants;
    private boolean useCaseFailed;

    public SortAndFilterRestaurantOutputData(ArrayList<Restaurant> sortedRestaurants, SearchCriteria criteria, boolean useCaseFailed){
        this.sortedRestaurants = sortedRestaurants;
        this.useCaseFailed = useCaseFailed;
        this.criteria = criteria;
    }
    public ArrayList<Restaurant> getRestaurants(){
        return sortedRestaurants;
    }
    public SearchCriteria getCriteria(){return criteria;}
}
