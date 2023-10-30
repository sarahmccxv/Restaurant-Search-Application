package interface_adapter.register;

import interface_adapter.register.RegisterState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.ViewManagerModel;
import use_case.register.RegisterOutputBoundary;
import use_case.register.RegisterOutputData;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RegisterPresenter implements RegisterOutputBoundary {

    private final RegisterViewModel registerViewModel;
    private final LoginViewModel loginViewModel;
    private ViewManagerModel viewManagerModel;

    public RegisterPresenter(ViewManagerModel viewManagerModel,
                             RegisterViewModel registerViewModel,
                           LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.registerViewModel = registerViewModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(RegisterOutputData response) {
        // On success, switch to the login view.
        LocalDateTime responseTime = LocalDateTime.parse(response.getCreationTime());
        response.setCreationTime(responseTime.format(DateTimeFormatter.ofPattern("hh:mm:ss")));

        LoginState loginState = loginViewModel.getState();
        loginState.setUsername(response.getUsername());
        this.loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        RegisterState registerState = registerViewModel.getState();
        registerState.setUsernameError(error);
        registerViewModel.firePropertyChanged();
    }
}