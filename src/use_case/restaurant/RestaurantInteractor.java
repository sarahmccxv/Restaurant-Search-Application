package use_case.restaurant;

import entity.Restaurant;
import use_case.register.RegisterUserDataAccessInterface;
import use_case.view_restaurant.ViewRestaurantDataAccessInterface;

public class RestaurantInteractor implements RestaurantInputBoundary {
    final RegisterUserDataAccessInterface fileUserDataAccessObject;
    final ViewRestaurantDataAccessInterface restaurantDataAccessObject;
    final RestaurantOutputBoundary restaurantPresenter;

    public RestaurantInteractor(RegisterUserDataAccessInterface fileUserDataAccessObject,
                                ViewRestaurantDataAccessInterface restaurantDataAccessObject,
                                RestaurantOutputBoundary restaurantOutputBoundary) {
        this.fileUserDataAccessObject = fileUserDataAccessObject;
        this.restaurantDataAccessObject = restaurantDataAccessObject;
        this.restaurantPresenter = restaurantOutputBoundary;
    }

    @Override
    public void execute(RestaurantInputData restaurantInputData) {
        Integer userID = restaurantInputData.getUserID();
        String username = restaurantInputData.getUsername();
        String password = restaurantInputData.getPassword();
        String restaurantID = restaurantInputData.getRestaurantID();
        String previousView = restaurantInputData.getPreviousView();
        Restaurant restaurant = restaurantDataAccessObject.getRestaurantByID(restaurantID);
        RestaurantOutputData restaurantOutputData = new RestaurantOutputData(userID, username, password,
                restaurant, previousView);
        restaurantPresenter.prepareSuccessView(restaurantOutputData);
    }
}
