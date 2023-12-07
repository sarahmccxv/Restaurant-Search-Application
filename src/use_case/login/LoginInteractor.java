package use_case.login;

import entity.User;

import javax.swing.*;

public class LoginInteractor implements LoginInputBoundary {
    final LoginUserDataAccessInterface userDataAccessObject;
    final LoginOutputBoundary loginPresenter;

    public LoginInteractor(LoginUserDataAccessInterface userDataAccessInterface,
                           LoginOutputBoundary loginOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        String username = loginInputData.getUsername();
        String password = loginInputData.getPassword();
        if (!userDataAccessObject.existsByName(username)) {
            loginPresenter.prepareFailView(username + ": Account does not exist.");
            JOptionPane.showMessageDialog(null,  username + " does not exist.", "Login Error", JOptionPane.ERROR_MESSAGE);
        } else {
            String pwd = userDataAccessObject.getByUsername(username).getPassword();
            if (!password.equals(pwd)) {
                //System.out.println("password does is not correct");
                loginPresenter.prepareFailView("Incorrect password for " + username + ".");
                JOptionPane.showMessageDialog(null, "Incorrect password for " + username + ".", "Login Error", JOptionPane.ERROR_MESSAGE);
            } else {

                User user = userDataAccessObject.getByUsername(loginInputData.getUsername());

                LoginOutputData loginOutputData = new LoginOutputData(
                        user.getUserID(), user.getUsername(), user.getPassword(), user.getLocation(), false);
                loginPresenter.prepareSuccessView(loginOutputData);
            }
        }
    }
}