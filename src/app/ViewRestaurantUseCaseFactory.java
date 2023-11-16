package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.view_favourites.ViewFavouritesController;
import interface_adapter.view_favourites.ViewFavouritesPresenter;
import interface_adapter.view_favourites.ViewFavouritesViewModel;
import interface_adapter.view_restaurants.ViewRestaurantController;
import interface_adapter.view_restaurants.ViewRestaurantPresenter;
import interface_adapter.view_restaurants.ViewRestaurantViewModel;
import use_case.register.RegisterUserDataAccessInterface;
import use_case.view_favourites.ViewFavouritesDataAccessInterface;
import use_case.view_favourites.ViewFavouritesInputBoundary;
import use_case.view_favourites.ViewFavouritesInteractor;
import use_case.view_favourites.ViewFavouritesOutputBoundary;
import use_case.view_restaurant.ViewRestaurantDataAccessInterface;
import use_case.view_restaurant.ViewRestaurantInputBoundary;
import use_case.view_restaurant.ViewRestaurantInteractor;
import use_case.view_restaurant.ViewRestaurantOutputBoundary;
import view.LoggedInView;

import javax.swing.*;
import java.io.IOException;

public class ViewRestaurantUseCaseFactory {
    private ViewRestaurantUseCaseFactory(){}
    public static LoggedInView create(ViewManagerModel viewManagerModel,
                                      LoggedInViewModel loggedInViewModel,
                                      ViewRestaurantViewModel viewRestaurantViewMode,
                                      ViewRestaurantDataAccessInterface viewRestaurantDataAccessObject,
                                      RegisterUserDataAccessInterface fileUserDataAccessObject) {

        try {
            ViewRestaurantController viewRestaurantController = createViewRestaurantUseCase(viewManagerModel,
                    viewRestaurantViewMode, viewRestaurantDataAccessObject, fileUserDataAccessObject);
            return new LoggedInView(loggedInViewModel, viewRestaurantViewMode, viewRestaurantController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not get restaurant data.");
        }
        return null;
    }

    private static ViewRestaurantController createViewRestaurantUseCase(ViewManagerModel viewManagerModel,
                                                                        ViewRestaurantViewModel viewRestaurantViewModel,
                                                                        ViewRestaurantDataAccessInterface viewRestaurantDataAccessObject,
                                                                        RegisterUserDataAccessInterface fileUserDataAccessObject) throws IOException {

        ViewRestaurantOutputBoundary viewRestaurantOutputBoundary = new ViewRestaurantPresenter(viewManagerModel, viewRestaurantViewModel);
        ViewRestaurantInputBoundary viewRestaurantInteractor = new ViewRestaurantInteractor(
                viewRestaurantOutputBoundary, viewRestaurantDataAccessObject, fileUserDataAccessObject);

        return new ViewRestaurantController(viewRestaurantInteractor);
    }

}
