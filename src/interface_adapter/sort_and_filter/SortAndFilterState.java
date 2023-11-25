package interface_adapter.sort_and_filter;


import api.Search.SearchCriteria;
import api.Search.SearchPriceLevel;
import api.Search.SearchSortingMethods;
import entity.Restaurant;
import entity.User;

import java.util.ArrayList;

public class SortAndFilterState {
    private SearchCriteria criteria;
//    private SearchSortingMethods selectedSortingMethod;
//    private SearchPriceLevel selectedPriceLevel;
//    private String enteredCategory;
    private ArrayList<Restaurant> restaurants;
    private String location = "";

    public SortAndFilterState() {
        criteria = new SearchCriteria.Builder().setLocation(location)
//                .setSortingMethod(selectedSortingMethod)
//                .setPriceLevel(selectedPriceLevel)
//                .setCategory(enteredCategory)
                .build();
        restaurants = new ArrayList<>();
    }

//    public SearchSortingMethods getSortingMethod(){
//        return selectedSortingMethod;
//    }
//    public SearchPriceLevel getSelectedPriceLevel(){
//        return selectedPriceLevel;
//    }
//    public String getCategory(){
//        return enteredCategory;
//    }
    public SearchCriteria getCriteria(){
        return criteria;
    }
    public String getLocation() { return location; }
//    public void setSortingMethod(SearchSortingMethods searchSortingMethods){this.selectedSortingMethod = searchSortingMethods;}
//    public void setSelectedPriceLevel(SearchPriceLevel searchPriceLevel){this.selectedPriceLevel = searchPriceLevel;}
//    public void setCategory(String category){this.enteredCategory = category;}
    public void setCriteria(SearchCriteria criteria){this.criteria = criteria;}
    public void setLocation(String location) {this.location = location;}
//    public ArrayList<Restaurant> getRestaurants(SearchCriteria criteria) {
//        return getRestaurants(criteria);
//    }
}