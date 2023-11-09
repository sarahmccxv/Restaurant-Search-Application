package app;

import entity.RestaurantFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.view_favourites.ViewFavouritesController;
import interface_adapter.view_favourites.ViewFavouritesPresenter;
import interface_adapter.view_favourites.ViewFavouritesViewModel;
import use_case.view_favourites.ViewFavouritesDataAccessInterface;
import use_case.view_favourites.ViewFavouritesInputBoundary;
import use_case.view_favourites.ViewFavouritesInteractor;
import use_case.view_favourites.ViewFavouritesOutputBoundary;
import view.LoggedInView;
import view.LoginView;
import view.ViewFavouritesView;

import javax.swing.*;
import java.io.IOException;

public class ViewFavouritesUseCaseFactory {

    /** Prevent instantiation. */
    private ViewFavouritesUseCaseFactory() {}

    public static LoggedInView create(ViewManagerModel viewManagerModel,
                                      LoggedInViewModel loggedInViewModel,
                                      ViewFavouritesViewModel viewFavouritesViewModel,
                                      ViewFavouritesDataAccessInterface userDataAccessObject) {

        try {
            ViewFavouritesController viewFavouritesController = createViewFavouritesUseCase(viewManagerModel, loggedInViewModel,
                    viewFavouritesViewModel, userDataAccessObject);
            return new LoggedInView(loggedInViewModel, viewFavouritesViewModel, viewFavouritesController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }

    private static ViewFavouritesController createViewFavouritesUseCase(ViewManagerModel viewManagerModel,
                                                                        LoggedInViewModel loggedInViewModel,
                                                                        ViewFavouritesViewModel viewFavouritesViewModel,
                                                                        ViewFavouritesDataAccessInterface userDataAccessObject) throws IOException {

        ViewFavouritesOutputBoundary viewFavouritesOutputBoundary = new ViewFavouritesPresenter(viewManagerModel, viewFavouritesViewModel);
        ViewFavouritesInputBoundary viewFavouritesInteractor = new ViewFavouritesInteractor(
                userDataAccessObject, viewFavouritesOutputBoundary);

        return new ViewFavouritesController(viewFavouritesInteractor);
    }

}
