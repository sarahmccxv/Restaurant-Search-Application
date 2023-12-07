package app;

import api.yelp.YelpApiServices;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginViewModel;
import interface_adapter.user_profile.UserProfileController;
import interface_adapter.user_profile.UserProfilePresenter;
import interface_adapter.user_profile.UserProfileViewModel;
import interface_adapter.view_restaurants.ViewRestaurantViewModel;
import use_case.register.RegisterUserDataAccessInterface;
import use_case.user_profile.UserProfileDataAccessInterface;
import use_case.user_profile.UserProfileInteractor;
import use_case.user_profile.UserProfileOutputBoundary;
import view.UserProfileView;

import javax.swing.*;
import java.io.IOException;

public class UserProfileUseCaseFactory {
    private UserProfileUseCaseFactory() {}

    public static UserProfileView create(ViewManagerModel viewManagerModel,
                                         YelpApiServices APIRestaurantDataAccessObject,
                                         UserProfileViewModel userProfileViewModel,
                                         UserProfileDataAccessInterface fileUserDataAccessObject,
                                         LoginController loginController) {
            UserProfileController userProfileController = createUserProfileUseCase(viewManagerModel,
                    APIRestaurantDataAccessObject, userProfileViewModel, fileUserDataAccessObject);
            return new UserProfileView(viewManagerModel, userProfileViewModel, userProfileController, loginController);
    }

    public static UserProfileController createUserProfileUseCase(ViewManagerModel viewManagerModel,
                                                                 YelpApiServices APIRestaurantDataAccessObject,
                                                                 UserProfileViewModel userProfileViewModel,
                                                                 UserProfileDataAccessInterface fileUserDataAccessObject) {
        UserProfileOutputBoundary userProfileOutputBoundary = new UserProfilePresenter(
                viewManagerModel, userProfileViewModel);
        UserFactory userFactory = new UserFactory();
        UserProfileInteractor userProfileInteractor = new UserProfileInteractor(
                userProfileOutputBoundary, fileUserDataAccessObject, APIRestaurantDataAccessObject, userFactory);

        return new UserProfileController(userProfileInteractor);
    }
}