package app;

import data_access.FileUserDataAccessObject;
import entity.ReviewFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_review.AddReviewController;
import interface_adapter.add_review.AddReviewPresenter;
import interface_adapter.add_review.AddReviewViewModel;
import interface_adapter.restaurant.RestaurantController;
import interface_adapter.restaurant.RestaurantPresenter;
import interface_adapter.restaurant.RestaurantViewModel;
import interface_adapter.write_review.WriteReviewController;
import interface_adapter.write_review.WriteReviewPresenter;
import interface_adapter.write_review.WriteReviewViewModel;
import use_case.add_review.AddReviewInputBoundary;
import use_case.add_review.AddReviewInteractor;
import use_case.add_review.AddReviewOutputBoundary;
import use_case.register.RegisterUserDataAccessInterface;
import use_case.restaurant.RestaurantInputBoundary;
import use_case.restaurant.RestaurantInteractor;
import use_case.restaurant.RestaurantOutputBoundary;
import use_case.view_restaurant.ViewRestaurantDataAccessInterface;
import use_case.write_review.WriteReviewDataAccessInterface;
import use_case.write_review.WriteReviewInputBoundary;
import use_case.write_review.WriteReviewInteractor;
import use_case.write_review.WriteReviewOutputBoundary;
import view.ViewRestaurantView;
import view.WriteReviewView;

import javax.swing.text.View;

public class WriteReviewUseCaseFactory {
    private WriteReviewUseCaseFactory() {};

    public static WriteReviewView create(ViewManagerModel viewManagerModel,
                                         AddReviewViewModel addReviewViewModel,
                                         WriteReviewViewModel writeReviewViewModel,
                                         RestaurantViewModel restaurantViewModel,
                                         WriteReviewDataAccessInterface reviewDataAccessObject,
                                         ViewRestaurantDataAccessInterface restaurantDataAccessObject,
                                         RegisterUserDataAccessInterface fileUserDataAccessObject) {
        AddReviewController addReviewController = createAddReviewUseCase(viewManagerModel, addReviewViewModel,
                fileUserDataAccessObject);
        WriteReviewController writeReviewController = createWriteReviewUseCase(viewManagerModel, writeReviewViewModel,
                reviewDataAccessObject);
        RestaurantController restaurantController = createRestaurantUseCase(viewManagerModel,
                restaurantViewModel, restaurantDataAccessObject, fileUserDataAccessObject);
        return new WriteReviewView(addReviewViewModel, writeReviewViewModel,
                writeReviewController, restaurantController);

    }

    public static AddReviewController createAddReviewUseCase(ViewManagerModel viewManagerModel,
                                                             AddReviewViewModel addReviewViewModel,
                                                             RegisterUserDataAccessInterface fileUserDataAccessObject) {
        AddReviewOutputBoundary addReviewPresenter = new AddReviewPresenter(viewManagerModel, addReviewViewModel);
        AddReviewInputBoundary addReviewInteractor =
                new AddReviewInteractor(addReviewPresenter, fileUserDataAccessObject);
        return new AddReviewController(addReviewInteractor);
    }

    public static WriteReviewController createWriteReviewUseCase(ViewManagerModel viewManagerModel,
                                                                 WriteReviewViewModel writeReviewViewModel,
                                                                 WriteReviewDataAccessInterface
                                                                         reviewDataAccessObject) {
        WriteReviewOutputBoundary writeReviewPresenter = new WriteReviewPresenter(viewManagerModel,
                writeReviewViewModel);
        ReviewFactory reviewFactory = new ReviewFactory();
        WriteReviewInputBoundary writeReviewInteractor =
                new WriteReviewInteractor(reviewDataAccessObject, writeReviewPresenter, reviewFactory);
        return new WriteReviewController(writeReviewInteractor);
    }

    public static RestaurantController createRestaurantUseCase(ViewManagerModel viewManagerModel,
                                                               RestaurantViewModel restaurantViewModel,
                                                               ViewRestaurantDataAccessInterface
                                                                       restaurantDataAccessObject,
                                                               RegisterUserDataAccessInterface
                                                                       fileUserDataAccessObject) {
        RestaurantOutputBoundary restaurantPresenter = new RestaurantPresenter(
                viewManagerModel, restaurantViewModel);
        RestaurantInputBoundary restaurantInteractor = new RestaurantInteractor(
                fileUserDataAccessObject, restaurantDataAccessObject, restaurantPresenter);
        return new RestaurantController(restaurantInteractor);
    }
}

