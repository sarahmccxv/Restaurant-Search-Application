package use_case.search_restaurant;

import entity.Restaurant;

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
        if (!searchRestaurantDataAccessObject.existsByName(restaurantName)) {
            searchRestaurantPresenter.prepareFailView("Restaurant does not exist, please try another one.");
        } else {
            Restaurant restaurant = searchRestaurantDataAccessObject.get(restaurantName);

            SearchRestaurantOutputData searchRestaurantOutputData = new SearchRestaurantOutputData(restaurantName, false);
            searchRestaurantPresenter.prepareSuccessView(searchRestaurantOutputData);
        }
    }
}
