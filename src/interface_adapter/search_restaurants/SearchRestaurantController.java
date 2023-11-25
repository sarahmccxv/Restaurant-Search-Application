package interface_adapter.search_restaurants;

import use_case.search_restaurant.SearchResaturantInputData;
import use_case.search_restaurant.SearchRestaurantInputBoundary;
import use_case.search_restaurant.SearchRestaurantInteractor;
import use_case.search_restaurant.SearchRestaurantOutputBoundary;

public class SearchRestaurantController {
    final SearchRestaurantInputBoundary searchRestaurantInteractor;

    public SearchRestaurantController(SearchRestaurantInputBoundary searchRestaurantInteractor) {
        this.searchRestaurantInteractor = searchRestaurantInteractor;
    }

    public void execute(String location, String restaurantName) {
        SearchResaturantInputData searchResaturantInputData = new SearchResaturantInputData(location, restaurantName);
        searchRestaurantInteractor.execute(searchResaturantInputData);
    }
}