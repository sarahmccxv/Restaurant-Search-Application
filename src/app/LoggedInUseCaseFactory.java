package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.sort_and_filter.SortAndFilterViewModel;
import interface_adapter.user_profile.UserProfileController;
import interface_adapter.user_profile.UserProfileViewModel;
import interface_adapter.view_favourites.ViewFavouritesController;
import interface_adapter.view_favourites.ViewFavouritesPresenter;
import interface_adapter.view_favourites.ViewFavouritesViewModel;
import interface_adapter.view_restaurants.ViewRestaurantController;
import interface_adapter.view_restaurants.ViewRestaurantPresenter;
import interface_adapter.view_restaurants.ViewRestaurantViewModel;
import use_case.register.RegisterUserDataAccessInterface;
import use_case.user_profile.UserProfileDataAccessInterface;
import use_case.view_favourites.ViewFavouritesDataAccessInterface;
import use_case.view_favourites.ViewFavouritesInputBoundary;
import use_case.view_favourites.ViewFavouritesInteractor;
import use_case.view_favourites.ViewFavouritesOutputBoundary;
import use_case.view_restaurant.ViewRestaurantDataAccessInterface;
import use_case.view_restaurant.ViewRestaurantInputBoundary;
import use_case.view_restaurant.ViewRestaurantInteractor;
import use_case.view_restaurant.ViewRestaurantOutputBoundary;
import view.LoggedInView;
import view.UserProfileView;

import javax.swing.*;
import java.io.IOException;

public class LoggedInUseCaseFactory {

    private LoggedInUseCaseFactory() {}

    public static LoggedInView create(ViewManagerModel viewManagerModel,
                                      LoggedInViewModel loggedInViewModel,
                                      ViewRestaurantViewModel viewRestaurantViewModel,
                                      ViewRestaurantDataAccessInterface viewRestaurantDataAccessObject,
                                      ViewFavouritesViewModel viewFavouritesViewModel,
                                      ViewFavouritesDataAccessInterface userDataAccessObject,
                                      UserProfileViewModel userProfileViewModel,
                                      UserProfileDataAccessInterface userProfileDataAccessObject,
                                      RegisterUserDataAccessInterface fileUserDataAccessObject,
                                      SortAndFilterViewModel sortAndFilterViewModel) {

        try {
            ViewFavouritesController viewFavouritesController = createViewFavouritesUseCase(viewManagerModel,
                    viewFavouritesViewModel, userDataAccessObject, fileUserDataAccessObject);
            ViewRestaurantController viewRestaurantController = createViewRestaurantUseCase(viewManagerModel,
                    viewRestaurantViewModel, viewRestaurantDataAccessObject, fileUserDataAccessObject, sortAndFilterViewModel);
            UserProfileController userProfileController = UserProfileUseCaseFactory.createUserProfileUseCase(
                    viewManagerModel, userProfileViewModel, userProfileDataAccessObject);
            return new LoggedInView(loggedInViewModel, viewFavouritesViewModel,
                    viewFavouritesController, viewRestaurantViewModel, viewRestaurantController, userProfileViewModel, userProfileController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }

    private static ViewRestaurantController createViewRestaurantUseCase(ViewManagerModel viewManagerModel,
                                                                        ViewRestaurantViewModel viewRestaurantViewModel,
                                                                        ViewRestaurantDataAccessInterface
                                                                                RestaurantDataAccessObject,
                                                                        RegisterUserDataAccessInterface
                                                                                fileUserDataAccessObject,
                                                                        SortAndFilterViewModel sortAndFilterViewModel)
            throws IOException {
        ViewRestaurantOutputBoundary viewRestaurantOutputBoundary = new ViewRestaurantPresenter(viewManagerModel,
                viewRestaurantViewModel, sortAndFilterViewModel);
        ViewRestaurantInputBoundary viewRestaurantInteractor = new ViewRestaurantInteractor(
                viewRestaurantOutputBoundary, RestaurantDataAccessObject, fileUserDataAccessObject);

        return new ViewRestaurantController(viewRestaurantInteractor);
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