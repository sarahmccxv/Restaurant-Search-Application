package interface_adapter.restaurant;

import interface_adapter.ViewManagerModel;
import use_case.restaurant.RestaurantOutputBoundary;
import use_case.restaurant.RestaurantOutputData;

public class RestaurantPresenter implements RestaurantOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final RestaurantViewModel restaurantViewModel;

    public RestaurantPresenter(ViewManagerModel viewManagerModel,
                               RestaurantViewModel restaurantViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.restaurantViewModel = restaurantViewModel;
    }
    @Override
    public void prepareSuccessView(RestaurantOutputData restaurantOutputData) {
        RestaurantState restaurantState = restaurantViewModel.getState();
        restaurantState.setUserID(restaurantOutputData.getUserID());
        restaurantState.setUsername(restaurantOutputData.getUsername());
        restaurantState.setPassword(restaurantOutputData.getPassword());
        restaurantState.setRestaurant(restaurantOutputData.getRestaurant());
        restaurantState.setPreviousView(restaurantOutputData.getPreviousView());
        this.restaurantViewModel.setState(restaurantState);
        this.restaurantViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(restaurantViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}