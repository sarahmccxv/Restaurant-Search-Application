package use_case.view_restaurant;

import api.ApiRestaurant;
import entity.Restaurant;
import entity.User;
import use_case.register.RegisterUserDataAccessInterface;

import java.util.ArrayList;

public class ViewRestaurantInteractor implements ViewRestaurantInputBoundary {
    final RegisterUserDataAccessInterface fileUserDataAccessObject;
    final ViewRestaurantDataAccessInterface viewRestaurantDataAccessObject;
    final ViewRestaurantOutputBoundary viewRestaurantPresenter;

    public ViewRestaurantInteractor(ViewRestaurantOutputBoundary viewRestaurantOutputBoundary,
                                    ViewRestaurantDataAccessInterface viewRestaurantDataAccessObject,
                                    RegisterUserDataAccessInterface fileUserDataAccessObject) {
        this.viewRestaurantPresenter = viewRestaurantOutputBoundary;
        this.fileUserDataAccessObject = fileUserDataAccessObject;
        this.viewRestaurantDataAccessObject = viewRestaurantDataAccessObject;
    }

    @Override
    public void execute(ViewRestaurantInputData viewRestaurantInputData) {
        Integer userID = viewRestaurantInputData.getUserID();
        String username = viewRestaurantInputData.getUsername();
        String password = viewRestaurantInputData.getPassword();
        System.out.println("viewRestaurant Input Data received as UserID is " + userID + " , password is " + password);
        fileUserDataAccessObject.update();
        User user = fileUserDataAccessObject.get(userID);
        String location = user.getLocation();
        ArrayList<Restaurant> local_restaurants = viewRestaurantDataAccessObject.getLocalRestaurants(location);
        ViewRestaurantOutputData viewRestaurantOutputData = new ViewRestaurantOutputData(
                userID, username, password, local_restaurants);
        viewRestaurantPresenter.prepareSuccessView(viewRestaurantOutputData);
    }
}
