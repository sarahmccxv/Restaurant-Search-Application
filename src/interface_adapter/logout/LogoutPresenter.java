package interface_adapter.logout;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import use_case.logout.LogoutOutputBoundary;
import use_case.logout.LogoutOutputData;

import javax.swing.*;

public class LogoutPresenter implements LogoutOutputBoundary {
    private LogoutViewModel logoutViewModel;
    private ViewManagerModel viewManagerModel;
    private LoginViewModel loginViewModel;

    public LogoutPresenter(ViewManagerModel viewManagerModel, LogoutViewModel logoutViewModel,
                           LoginViewModel loginViewModel){
        this.viewManagerModel = viewManagerModel;
        this.logoutViewModel = logoutViewModel;
        this.loginViewModel = loginViewModel;
    }

    public void prepareSuccessView(LogoutOutputData outputData){
        LoginState loginState = loginViewModel.getState();
        loginState.setLogout(true);
        loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();
        LogoutState logoutState = logoutViewModel.getState();
        logoutState.setMessage("Logged out of " + outputData.getUsername() + ".");
        logoutViewModel.setState(logoutState);
        logoutViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
