package use_case.sortandfilter;

import api.Exception.YelpRequestException;
import api.Search.SearchCriteria;
import api.Search.SearchPriceLevel;
import api.Search.SearchSortingMethods;
import api.yelp.YelpAPI;
import entity.Restaurant;
import api.yelp.YelpApiServices;

import java.util.*;

public class SortAndFilterRestaurantInteractor implements SortAndFilterRestaurantInputBoundary {
    final SortAndFilterRestaurantDataAccessInterface sortAndFilterRestaurantDataAccessObject;
    final SortAndFilterRestaurantOutputBoundary sortAndFilterRestaurantPresenter;

    public SortAndFilterRestaurantInteractor(SortAndFilterRestaurantDataAccessInterface sortAndFilterRestaurantDataAccessInterface, SortAndFilterRestaurantOutputBoundary sortAndFilterRestaurantOutputBoundary){
        this.sortAndFilterRestaurantDataAccessObject = sortAndFilterRestaurantDataAccessInterface;
        this.sortAndFilterRestaurantPresenter = sortAndFilterRestaurantOutputBoundary;
    }
    @Override
    public void execute(SortAndFilterResaturantInputData sortAndFilterResaturantInputData) {
        String name = sortAndFilterResaturantInputData.getName();
        String location = sortAndFilterResaturantInputData.getLocation();
        int limit = sortAndFilterResaturantInputData.getLimit();
        SearchSortingMethods sortingMethod = sortAndFilterResaturantInputData.getSortingMethod();
        SearchPriceLevel priceLevel = sortAndFilterResaturantInputData.getPriceLevel();
        String category = sortAndFilterResaturantInputData.getCategory();
        YelpApiServices yelpApiServices = new YelpAPI();
        SearchCriteria criteria = new SearchCriteria.Builder()
                .setName(name)
                .setLocation(location)
                .setLimit(limit)
                .setSortingMethod(sortingMethod)
                .setPriceLevel(priceLevel)
                .setCategory(category)
                .build();
        try {
            ArrayList<Restaurant> sorted = yelpApiServices.getRestaurants(criteria);
            SortAndFilterRestaurantOutputData sortAndFilterRestaurantOutputData = new SortAndFilterRestaurantOutputData(sorted, false);
            sortAndFilterRestaurantPresenter.prepareSuccessView(sortAndFilterRestaurantOutputData);
        } catch (RuntimeException e){
            sortAndFilterRestaurantPresenter.prepareFailView("No restaurant satisfies such filter and sort.");
        }
    }
}
