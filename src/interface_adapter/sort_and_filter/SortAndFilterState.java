package interface_adapter.sort_and_filter;


import api.Search.SearchCriteria;
import api.Search.SearchPriceLevel;
import api.Search.SearchSortingMethods;
import entity.Restaurant;
import entity.User;

import java.util.ArrayList;

public class SortAndFilterState {
    private SearchCriteria criteria;
    private ArrayList<Restaurant> restaurants;
    private String location = "";
    private String category;

    public SortAndFilterState() {
        criteria = new SearchCriteria.Builder().setLocation(location)
                .build();
        restaurants = new ArrayList<>();
    }

    public SearchCriteria getCriteria(){
        return criteria;
    }
    public String getLocation() { return location; }
    public ArrayList<Restaurant> getRestaurants(){return restaurants;}
    public String getCategory(){return category;}
    public void setCriteria(SearchCriteria criteria){this.criteria = criteria;}
    public void setLocation(String location) {this.location = location;}
    public void setRestaurants(ArrayList<Restaurant> restaurants){this.restaurants = restaurants;}
    public void setCategory(String category){this.category = category;}
}