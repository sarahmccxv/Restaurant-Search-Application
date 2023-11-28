package interface_adapter.search_restaurants;

import interface_adapter.ViewManagerModel;
import interface_adapter.view_restaurants.ViewRestaurantState;
import interface_adapter.view_restaurants.ViewRestaurantViewModel;
import use_case.search_restaurant.SearchRestaurantOutputBoundary;
import use_case.search_restaurant.SearchRestaurantOutputData;

public class SearchRestaurantPresenter implements SearchRestaurantOutputBoundary {
    private final ViewRestaurantViewModel viewRestaurantViewModel;
    private final ViewManagerModel viewManagerModel;


    public SearchRestaurantPresenter(ViewManagerModel viewManagerModel,
                                     ViewRestaurantViewModel viewRestaurantViewModel) {
        this.viewRestaurantViewModel = viewRestaurantViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(SearchRestaurantOutputData searchRestaurantOutputData) {
        ViewRestaurantState viewRestaurantState = viewRestaurantViewModel.getState();
        viewRestaurantState.setRestaurants(searchRestaurantOutputData.getRestaurants());
        this.viewRestaurantViewModel.setState(viewRestaurantState);
        this.viewRestaurantViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(viewRestaurantViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    public void prepareFailView(String error) {
        ViewRestaurantState searchRestaurantState = viewRestaurantViewModel.getState();
        this.viewRestaurantViewModel.setState(searchRestaurantState);
        this.viewRestaurantViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(viewRestaurantViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
