package use_case.search_restaurant;

import entity.Restaurant;

import java.util.ArrayList;

public class SearchRestaurantInteractor implements SearchRestaurantInputBoundary{
    final SearchRestaurantDataAccessInterface searchRestaurantDataAccessObject;
    final SearchRestaurantOutputBoundary searchRestaurantPresenter;

    public SearchRestaurantInteractor(SearchRestaurantDataAccessInterface searchRestaurantDataAccessInterface, SearchRestaurantOutputBoundary searchRestaurantOutputBoundary){
        this.searchRestaurantDataAccessObject = searchRestaurantDataAccessInterface;
        this.searchRestaurantPresenter = searchRestaurantOutputBoundary;
    }
    @Override
    public void execute(SearchResaturantInputData searchResaturantInputData) {
        String restaurantName = searchResaturantInputData.getRestaurantName();
        String location = searchResaturantInputData.getLocation();
            ArrayList<Restaurant> restaurant = searchRestaurantDataAccessObject.getRestaurantByName(location, restaurantName);
            SearchRestaurantOutputData searchRestaurantOutputData = new SearchRestaurantOutputData(restaurant, false);
            searchRestaurantPresenter.prepareSuccessView(searchRestaurantOutputData);
        }
    }
