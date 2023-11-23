package use_case.write_review;

import use_case.register.RegisterUserDataAccessInterface;
import use_case.restaurant.RestaurantOutputBoundary;
import use_case.view_restaurant.ViewRestaurantDataAccessInterface;

public class WriteReviewInteractor implements WriteReviewInputBoundary {
    final RegisterUserDataAccessInterface fileUserDataAccessObject;
    final ViewRestaurantDataAccessInterface restaurantDataAccessObject;
    final RestaurantOutputBoundary restaurantPresenter;
    public WriteReviewInteractor() {

    }
    @Override
    public void execute(WriteReviewInputData writeReviewInputData) {

    }
}
