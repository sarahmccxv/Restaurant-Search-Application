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
        //System.out.println("This is Restaurant presenter and I've received restaurant ID: " + restaurantOutputData.getRestaurant().getRestaurantID());
        //System.out.println("I also have userID: " + restaurantOutputData.getUserID());
        RestaurantState restaurantState = restaurantViewModel.getState();
        restaurantState.setUserID(restaurantOutputData.getUserID());
        restaurantState.setUsername(restaurantOutputData.getUsername());
        restaurantState.setPassword(restaurantOutputData.getPassword());
        restaurantState.setRestaurant(restaurantOutputData.getRestaurant());
        this.restaurantViewModel.setState(restaurantState);
        //System.out.println("Now I've successfully set the Restaurant state with ID: " + restaurantState.getRestaurant().getRestaurantID());
        this.restaurantViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(restaurantViewModel.getViewName());
        //System.out.println("Now I've successfully passed new viewName as: " + restaurantViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
