package interface_adapter.sort_and_filter;


import api.Search.SearchCriteria;
import api.Search.SearchPriceLevel;
import api.Search.SearchSortingMethods;
import entity.Restaurant;
import entity.User;

import java.util.ArrayList;

public class SortAndFilterState {
    private SearchCriteria criteria;
    private SearchSortingMethods selectedSortingMethod;
    private SearchPriceLevel selectedPriceLevel;
    private String enteredCategory;
    private ArrayList<Restaurant> restaurants;

    public SortAndFilterState() {
        criteria = new SearchCriteria.Builder()
                .setSortingMethod(selectedSortingMethod)
                .setPriceLevel(selectedPriceLevel)
                .setCategory(enteredCategory)
                .build();
        restaurants = new ArrayList<>();
    }

    private ArrayList<Restaurant> getRestaurants(SearchCriteria criteria) {
        return getRestaurants(criteria);
    }
}