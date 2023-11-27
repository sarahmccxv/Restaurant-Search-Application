package app;

import data_access.FileFavouritesDataAccessObject;
import entity.RestaurantFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_review.AddReviewController;
import interface_adapter.add_review.AddReviewPresenter;
import interface_adapter.add_review.AddReviewViewModel;
import interface_adapter.add_to_favourites.AddToFavouritesController;
import interface_adapter.add_to_favourites.AddToFavouritesPresenter;
import interface_adapter.add_to_favourites.AddToFavouritesViewModel;
import interface_adapter.restaurant.RestaurantController;
import interface_adapter.restaurant.RestaurantPresenter;
import interface_adapter.restaurant.RestaurantViewModel;
import interface_adapter.view_favourites.ViewFavouritesController;
import interface_adapter.view_favourites.ViewFavouritesPresenter;
import interface_adapter.view_favourites.ViewFavouritesViewModel;
import interface_adapter.view_restaurants.ViewRestaurantController;
import use_case.add_review.AddReviewInputBoundary;
import use_case.add_review.AddReviewInteractor;
import use_case.add_review.AddReviewOutputBoundary;
import use_case.add_to_favourites.AddToFavouritesDataAccessInterface;
import use_case.add_to_favourites.AddToFavouritesInputBoundary;
import use_case.add_to_favourites.AddToFavouritesInteractor;
import use_case.add_to_favourites.AddToFavouritesOutputBoundary;
import use_case.register.RegisterUserDataAccessInterface;
import use_case.restaurant.RestaurantInputBoundary;
import use_case.restaurant.RestaurantInteractor;
import use_case.restaurant.RestaurantOutputBoundary;
import use_case.view_favourites.ViewFavouritesDataAccessInterface;
import use_case.view_favourites.ViewFavouritesInputBoundary;
import use_case.view_favourites.ViewFavouritesInteractor;
import use_case.view_favourites.ViewFavouritesOutputBoundary;
import use_case.view_restaurant.ViewRestaurantDataAccessInterface;
import view.RestaurantView;

import java.io.IOException;

public class RestaurantUseCaseFactory {
    private RestaurantUseCaseFactory() {}

    public static RestaurantView create(ViewManagerModel viewManagerModel,
                                        RestaurantViewModel restaurantViewModel,
                                        AddReviewViewModel addReviewViewModel,
                                        AddToFavouritesViewModel addToFavouritesViewModel,
                                        ViewRestaurantDataAccessInterface RestaurantDataAccessObject,
                                        RegisterUserDataAccessInterface fileUserDataAccessObject,
                                        ViewRestaurantController viewRestaurantController,
                                        AddToFavouritesDataAccessInterface favouritesDataAccessObject,
                                        ViewFavouritesViewModel viewFavouritesViewModel,
                                        FileFavouritesDataAccessObject favouritesDataAccessObject2) throws IOException {
        RestaurantController restaurantController = createRestaurantUseCase(viewManagerModel,
                restaurantViewModel, RestaurantDataAccessObject, fileUserDataAccessObject);
        AddReviewController addReviewController = createAddReviewUseCase(viewManagerModel, addReviewViewModel,
                fileUserDataAccessObject);
        AddToFavouritesController addToFavouritesController = createAddToFavouritesUseCase(viewManagerModel, addToFavouritesViewModel,
                favouritesDataAccessObject, fileUserDataAccessObject);
        ViewFavouritesController viewFavouritesController = createViewFavouritesUseCase(viewManagerModel,
                viewFavouritesViewModel, favouritesDataAccessObject2, fileUserDataAccessObject);
        return new RestaurantView(restaurantViewModel, restaurantController, addReviewViewModel, addReviewController,
                viewRestaurantController, addToFavouritesController, addToFavouritesViewModel, viewFavouritesController);
    }

    public static RestaurantController createRestaurantUseCase(ViewManagerModel viewManagerModel,
                                                                RestaurantViewModel restaurantViewModel,
                                                                ViewRestaurantDataAccessInterface
                                                                        restaurantDataAccessObject,
                                                                RegisterUserDataAccessInterface
                                                                        fileUserDataAccessObject) {
        RestaurantOutputBoundary restaurantOutputBoundary = new RestaurantPresenter(
                viewManagerModel, restaurantViewModel);
        RestaurantFactory restaurantFactory = new RestaurantFactory();
        RestaurantInputBoundary RestaurantInteractor = new RestaurantInteractor(
                fileUserDataAccessObject, restaurantDataAccessObject, restaurantOutputBoundary);
        return new RestaurantController(RestaurantInteractor);
    }

    public static AddReviewController createAddReviewUseCase(ViewManagerModel viewManagerModel,
                                                             AddReviewViewModel addReviewViewModel,
                                                             RegisterUserDataAccessInterface fileUserDataAccessObject) {
        AddReviewOutputBoundary addReviewPresenter = new AddReviewPresenter(viewManagerModel, addReviewViewModel);
        AddReviewInputBoundary addReviewInteractor = new AddReviewInteractor(addReviewPresenter, fileUserDataAccessObject);
        return new AddReviewController(addReviewInteractor);
    }

    public static AddToFavouritesController createAddToFavouritesUseCase(ViewManagerModel viewManagerModel,
                                                                         AddToFavouritesViewModel addToFavouritesViewModel,
                                                                         AddToFavouritesDataAccessInterface favouritesDataAccessObject,
                                                                         RegisterUserDataAccessInterface fileUserDataAccessObject){
        AddToFavouritesOutputBoundary addToFavouritesOutputBoundary = new AddToFavouritesPresenter(viewManagerModel, addToFavouritesViewModel);
        AddToFavouritesInputBoundary addToFavouritesInteractor = new AddToFavouritesInteractor(
                fileUserDataAccessObject, favouritesDataAccessObject, addToFavouritesOutputBoundary);
        return new AddToFavouritesController(addToFavouritesInteractor);

    }

    private static ViewFavouritesController createViewFavouritesUseCase(ViewManagerModel viewManagerModel,
                                                                        ViewFavouritesViewModel viewFavouritesViewModel,
                                                                        ViewFavouritesDataAccessInterface userDataAccessObject,
                                                                        RegisterUserDataAccessInterface fileUserDataAccessObject) throws IOException {

        ViewFavouritesOutputBoundary viewFavouritesOutputBoundary = new ViewFavouritesPresenter(viewManagerModel, viewFavouritesViewModel);
        ViewFavouritesInputBoundary viewFavouritesInteractor = new ViewFavouritesInteractor(
                userDataAccessObject, viewFavouritesOutputBoundary, fileUserDataAccessObject);

        return new ViewFavouritesController(viewFavouritesInteractor);
    }
}
