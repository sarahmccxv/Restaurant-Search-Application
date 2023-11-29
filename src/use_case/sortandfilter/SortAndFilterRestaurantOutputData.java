package use_case.sortandfilter;

import api.Search.SearchCriteria;
import entity.Restaurant;

import java.util.ArrayList;

public class SortAndFilterRestaurantOutputData {
    private final SearchCriteria criteria;
    private final ArrayList<Restaurant> sortedRestaurants;
    private boolean useCaseFailed;
    private String previousView;

    public SortAndFilterRestaurantOutputData(ArrayList<Restaurant> sortedRestaurants, SearchCriteria criteria, String previousView, boolean useCaseFailed){
        this.sortedRestaurants = sortedRestaurants;
        this.useCaseFailed = useCaseFailed;
        this.criteria = criteria;
        this.previousView = previousView;
    }
    public ArrayList<Restaurant> getRestaurants(){
        return sortedRestaurants;
    }
    public SearchCriteria getCriteria(){return criteria;}

    public String getPreviousView() {return previousView;}
}
