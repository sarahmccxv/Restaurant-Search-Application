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
        int userID = viewRestaurantInputData.getUserID();
        //System.out.println("UserID is " + userID);
        fileUserDataAccessObject.update();
        User user = fileUserDataAccessObject.get(userID);
        String location = user.getLocation();
        ArrayList<Restaurant> local_restaurants = viewRestaurantDataAccessObject.getLocalRestaurants(location);
        ViewRestaurantOutputData viewRestaurantOutputData = new ViewRestaurantOutputData(local_restaurants);
        viewRestaurantPresenter.prepareSuccessView(viewRestaurantOutputData);
    }
}
