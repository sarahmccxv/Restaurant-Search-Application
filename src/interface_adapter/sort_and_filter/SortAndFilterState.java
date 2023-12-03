package interface_adapter.sort_and_filter;


import api.Search.SearchCriteria;
import api.Search.SearchPriceLevel;
import api.Search.SearchSortingMethods;
import entity.Restaurant;
import entity.User;

import java.util.ArrayList;

public class SortAndFilterState {
    private SearchSortingMethods searchSortingMethods;
    private SearchPriceLevel searchPriceLevel;
    private SearchCriteria criteria;
    private ArrayList<Restaurant> restaurants;
    private String location;
    private String category;
    private String previousView;
    private String userID;
    private String username;
    private String password;

    public SortAndFilterState() {
        criteria = new SearchCriteria.Builder()
                .build();
        restaurants = new ArrayList<>();
        previousView = "";
        searchPriceLevel = SearchPriceLevel.CHEAP;
        searchSortingMethods = SearchSortingMethods.BEST_MATCH;
    }

    public SearchCriteria getCriteria(){
        return this.criteria;
    }
    public String getLocation() { return location; }
    public ArrayList<Restaurant> getRestaurants(){return restaurants;}
    public String getCategory(){return category;}

    public SearchSortingMethods getSearchSortingMethods() {
        return searchSortingMethods;
    }

    public SearchPriceLevel getSearchPriceLevel() {
        return searchPriceLevel;
    }
    public String getPreviousView() {return previousView;}

    public String getUserID(){return userID;}
    public String getUsername(){return username;}
    public String getPassword(){return password;}


    public void setSearchSortingMethods(SearchSortingMethods searchSortingMethods) {
        this.searchSortingMethods = searchSortingMethods;
        this.criteria.setSortingMethod(searchSortingMethods);
    }

    public void setSearchPriceLevel(SearchPriceLevel searchPriceLevel) {
        this.searchPriceLevel = searchPriceLevel;
        this.criteria.setPriceLevel(searchPriceLevel);
    }
    public void setCriteria(SearchCriteria criteria){this.criteria = criteria;}
    public void setLocation(String location) {this.location = location;
    this.criteria.setLocation(location);}
    public void setRestaurants(ArrayList<Restaurant> restaurants){this.restaurants = restaurants;}
    public void setCategory(String category){this.category = category;
    this.criteria.setCategory(category);}
    public void setPreviousView(String previousView) {this.previousView = previousView;}

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}