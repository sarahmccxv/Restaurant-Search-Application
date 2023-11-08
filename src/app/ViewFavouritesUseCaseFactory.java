package app;

import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.view_favourites.ViewFavouritesViewModel;
import use_case.view_favourites.ViewFavouritesDataAccessInterface;
import view.ViewFavouritesView;

import javax.swing.*;
import java.io.IOException;

public class ViewFavouritesUseCaseFactory {

    /** Prevent instantiation. */
    private ViewFavouritesUseCaseFactory() {}

    public static ViewFavouritesView create(ViewManagerModel viewManagerModel,
                                            LoggedInViewModel loggedINViewModel,
                                            ViewFavouritesViewModel viewFavouritesViewModel,
                                            ViewFavouritesDataAccessInterface userDataAccessObject) {

        try {
            ClearViewModel clearViewModel = new ClearViewModel();
            SignupController signupController = createUserSignupUseCase(viewManagerModel, signupViewModel, loginViewModel, userDataAccessObject);
            ClearController clearController = createClearController(clearViewModel, viewManagerModel, (ClearUserDataAccessInterface) userDataAccessObject);
            return new SignupView(signupController, signupViewModel, clearController, clearViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }

    private static SignupController createUserSignupUseCase(ViewManagerModel viewManagerModel, SignupViewModel signupViewModel, LoginViewModel loginViewModel, SignupUserDataAccessInterface userDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel);

        UserFactory userFactory = new CommonUserFactory();

        SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory);

        return new SignupController(userSignupInteractor);
    }

}
