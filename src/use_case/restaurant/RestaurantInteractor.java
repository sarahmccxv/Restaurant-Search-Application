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
        //System.out.println("This is Restaurant Interactor. I've received restaurant ID: " + restaurantInputData.getRestaurantID());
        Integer userID = restaurantInputData.getUserID();
        //System.out.println("I also receive userID: " + userID);
        String username = restaurantInputData.getUsername();
        String password = restaurantInputData.getPassword();
        String restaurantID = restaurantInputData.getRestaurantID();
        Restaurant restaurant = restaurantDataAccessObject.getRestaurantByID(restaurantID);
        //System.out.println("I've called the API and I got restaurant name: " + restaurant.getRestaurantName());
        RestaurantOutputData restaurantOutputData = new RestaurantOutputData(userID, username, password, restaurant);
        //System.out.println("Now I am passing the data to presenter.");
        restaurantPresenter.prepareSuccessView(restaurantOutputData);
    }
}
