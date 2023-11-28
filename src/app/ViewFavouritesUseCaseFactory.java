package app;


import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.remove_favourite.RemoveFavouriteController;
import interface_adapter.remove_favourite.RemoveFavouritePresenter;
import interface_adapter.remove_favourite.RemoveFavouriteViewModel;
import interface_adapter.restaurant.RestaurantController;
import interface_adapter.restaurant.RestaurantPresenter;
import interface_adapter.restaurant.RestaurantViewModel;
import interface_adapter.view_favourites.ViewFavouritesViewModel;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginUserDataAccessInterface;
import use_case.register.RegisterUserDataAccessInterface;
import use_case.remove_favourite.*;
import use_case.restaurant.RestaurantInputBoundary;
import use_case.restaurant.RestaurantInteractor;
import use_case.restaurant.RestaurantOutputBoundary;
import use_case.view_restaurant.ViewRestaurantDataAccessInterface;
import view.ViewFavouritesView;

import java.io.IOException;

public class ViewFavouritesUseCaseFactory {
    private ViewFavouritesUseCaseFactory() {
    }

    public static ViewFavouritesView create(ViewManagerModel viewManagerModel,
                                            RestaurantViewModel restaurantViewModel,
                                            LoginViewModel loginViewModel,
                                            LoggedInViewModel loggedInViewModel,
                                            ViewFavouritesViewModel viewFavouritesViewModel,
                                            RemoveFavouriteViewModel removeFavouriteViewModel,
                                            ViewRestaurantDataAccessInterface restaurantDataAccessObject,
                                            RegisterUserDataAccessInterface fileUserDataAccessObject,
                                            RemoveFavouriteDataAccessInterface removeFavouriteDataAccessObject) throws IOException {
        RemoveFavouriteController removeFavouriteController = createRemoveFavouriteUseCase(removeFavouriteViewModel, viewFavouritesViewModel,
                viewManagerModel, fileUserDataAccessObject, removeFavouriteDataAccessObject);
        LoginController loginController = createLoginUseCase(viewManagerModel, loginViewModel, loggedInViewModel,
                (LoginUserDataAccessInterface) fileUserDataAccessObject);
        RestaurantController restaurantController = createRestaurantUseCase(viewManagerModel, restaurantViewModel,
                restaurantDataAccessObject, fileUserDataAccessObject);
        return new ViewFavouritesView(viewFavouritesViewModel, loginController, restaurantController,
                           removeFavouriteController, removeFavouriteViewModel);

    }

    public static LoginController createLoginUseCase(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInViewModel loggedInViewModel,
            LoginUserDataAccessInterface userDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel, loggedInViewModel, loginViewModel);
        LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary);

        return new LoginController(loginInteractor);
    }

    public static RestaurantController createRestaurantUseCase(ViewManagerModel viewManagerModel,
                                                               RestaurantViewModel restaurantViewModel,
                                                               ViewRestaurantDataAccessInterface
                                                                       restaurantDataAccessObject,
                                                               RegisterUserDataAccessInterface
                                                                       fileUserDataAccessObject) {
        RestaurantOutputBoundary restaurantOutputBoundary = new RestaurantPresenter(
                viewManagerModel, restaurantViewModel);
        RestaurantInputBoundary restaurantInteractor = new RestaurantInteractor(
                fileUserDataAccessObject, restaurantDataAccessObject, restaurantOutputBoundary);
        return new RestaurantController(restaurantInteractor);
    }

    public static RemoveFavouriteController createRemoveFavouriteUseCase(RemoveFavouriteViewModel removeFavouriteViewModel,
                                                                         ViewFavouritesViewModel viewFavouritesViewModel,
                                                                         ViewManagerModel viewManagerModel,
                                                                         RegisterUserDataAccessInterface fileUserDataAccessObject,
                                                                         RemoveFavouriteDataAccessInterface removeFavouriteDataAccessObject){
        RemoveFavouriteOutputBoundary removeFavouritePresenter = new RemoveFavouritePresenter(removeFavouriteViewModel,
                viewManagerModel, viewFavouritesViewModel);
        RemoveFavouriteInputBoundary removeFavouriteInteractor = new RemoveFavouriteInteractor(fileUserDataAccessObject,
                 removeFavouriteDataAccessObject, removeFavouritePresenter);
        return new RemoveFavouriteController(removeFavouriteInteractor);
    }
}
