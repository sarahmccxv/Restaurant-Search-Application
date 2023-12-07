package use_case.sortandfilter;

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
        SearchCriteria criteria = sortAndFilterResaturantInputData.getCriteria();
        String previousView = sortAndFilterResaturantInputData.getPreviousView();
        System.out.println("Interactor executed");
        ArrayList<Restaurant> sorted = sortAndFilterRestaurantDataAccessObject.getRestaurants(criteria);
        SortAndFilterRestaurantOutputData sortAndFilterRestaurantOutputData = new SortAndFilterRestaurantOutputData(sorted, criteria, previousView, false);
        sortAndFilterRestaurantPresenter.prepareSuccessView(sortAndFilterRestaurantOutputData);
    }
}
