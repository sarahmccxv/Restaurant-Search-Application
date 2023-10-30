package app;

import data_access.FileUserDataAccessObject;
import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.register.RegisterViewModel;
import interface_adapter.register.RegisterController;
import interface_adapter.register.RegisterPresenter;
import use_case.register.RegisterInputBoundary;
import use_case.register.RegisterInteractor;
import use_case.register.RegisterOutputBoundary;
import use_case.register.RegisterUserDataAccessInterface;
import view.RegisterView;

import javax.swing.*;
import java.io.IOException;

public class RegisterUseCaseFactory {

    /** Prevent instantiation. */
    private RegisterUseCaseFactory() {}

    public static RegisterView create(
            ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, RegisterViewModel registerViewModel,
            RegisterUserDataAccessInterface userDataAccessObject) {
        try {
            RegisterController registerController = createUserRegisterUseCase(viewManagerModel, registerViewModel,
                    loginViewModel, userDataAccessObject);

            return new RegisterView(registerController, registerViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static RegisterController createUserRegisterUseCase(ViewManagerModel viewManagerModel,
                                                            RegisterViewModel registerViewModel,
                                                            LoginViewModel loginViewModel,
                                                            RegisterUserDataAccessInterface userDataAccessObject)
            throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        RegisterOutputBoundary registerOutputBoundary = new RegisterPresenter(viewManagerModel, registerViewModel, loginViewModel);

        UserFactory userFactory = new CommonUserFactory();

        RegisterInputBoundary userRegisterInteractor = new RegisterInteractor(
                userDataAccessObject, registerOutputBoundary, userFactory);

        return new RegisterController(userRegisterInteractor);
    }

    public static RegisterView create(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, RegisterViewModel registerViewModel, FileUserDataAccessObject userDataAccessObject) {
    }
}
