package interface_adapter.view_restaurants;

import interface_adapter.ViewManagerModel;
import interface_adapter.view_favourites.ViewFavouritesState;
import use_case.view_restaurant.ViewRestaurantOutputBoundary;
import use_case.view_restaurant.ViewRestaurantOutputData;
import view.ViewManager;

public class ViewRestaurantPresenter implements ViewRestaurantOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final ViewRestaurantViewModel viewRestaurantViewModel;

    public ViewRestaurantPresenter(ViewManagerModel viewManagerModel,
                                   ViewRestaurantViewModel viewRestaurantViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.viewRestaurantViewModel = viewRestaurantViewModel;
    }
    @Override
    public void prepareSuccessView(ViewRestaurantOutputData viewRestaurantOutputData) {
        ViewRestaurantState viewRestaurantState = viewRestaurantViewModel.getState();
        viewRestaurantState.setUserID(viewRestaurantState.getUserID());
        viewRestaurantState.setUsername(viewRestaurantOutputData.getUsername());
        viewRestaurantState.setPassword(viewRestaurantOutputData.getPassword());
        viewRestaurantState.setRestaurants(viewRestaurantOutputData.getLocalRestaurants());
        System.out.println("Restaurants received at presentor are " + viewRestaurantOutputData.getLocalRestaurants().toString());
        this.viewRestaurantViewModel.setState(viewRestaurantState);
        this.viewRestaurantViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(viewRestaurantViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
