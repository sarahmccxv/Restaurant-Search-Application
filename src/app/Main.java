package app;

import data_access.APIRestaurantDataAccessObject;
import data_access.FileReviewDataAccessObject;
import data_access.FileUserDataAccessObject;
import data_access.FileFavouritesDataAccessObject;
import entity.ReviewFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_review.AddReviewController;
import interface_adapter.add_review.AddReviewViewModel;
import interface_adapter.add_to_favourites.AddToFavouritesViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginViewModel;
import interface_adapter.register.RegisterViewModel;
import interface_adapter.remove_favourite.RemoveFavouriteViewModel;
import interface_adapter.restaurant.RestaurantController;
import interface_adapter.restaurant.RestaurantViewModel;
import interface_adapter.search_restaurants.SearchRestaurantController;
import interface_adapter.sort_and_filter.SortAndFilterController;
import interface_adapter.sort_and_filter.SortAndFilterViewModel;
import interface_adapter.user_profile.UserProfileViewModel;
import interface_adapter.view_favourites.ViewFavouritesViewModel;
import interface_adapter.view_restaurants.ViewRestaurantController;
import interface_adapter.view_restaurants.ViewRestaurantState;
import interface_adapter.view_restaurants.ViewRestaurantViewModel;
import interface_adapter.write_review.WriteReviewViewModel;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.

        // The main application window.
        JFrame application = new JFrame("Restaurant Search Application");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);


        // The data for the views, such as username and password, are in the ViewModels.
        // This information will be changed by a presenter object that is reporting the
        // results from the use case. The ViewModels are observable, and will
        // be observed by the Views.
        LoginViewModel loginViewModel = new LoginViewModel();
        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
        RegisterViewModel registerViewModel = new RegisterViewModel();
        ViewFavouritesViewModel viewFavouritesViewModel = new ViewFavouritesViewModel();
        ViewRestaurantViewModel viewRestaurantViewModel = new ViewRestaurantViewModel();
        RestaurantViewModel restaurantViewModel = new RestaurantViewModel();
        SortAndFilterViewModel sortAndFilterViewModel = new SortAndFilterViewModel();
        AddToFavouritesViewModel addToFavouritesViewModel = new AddToFavouritesViewModel();
        UserProfileViewModel userProfileViewModel = new UserProfileViewModel();
        RemoveFavouriteViewModel removeFavouriteViewModel = new RemoveFavouriteViewModel();
        AddReviewViewModel addReviewViewModel = new AddReviewViewModel();
        WriteReviewViewModel writeReviewViewModel = new WriteReviewViewModel();


        FileUserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new FileUserDataAccessObject("./users.csv", new UserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        APIRestaurantDataAccessObject apiRestaurantDataAccessObject = new APIRestaurantDataAccessObject();

        FileFavouritesDataAccessObject fileFavouritesDataAccessObject;
        try {
            fileFavouritesDataAccessObject = new FileFavouritesDataAccessObject("./favourites.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        FileReviewDataAccessObject fileReviewDataAccessObject;
        try {
            fileReviewDataAccessObject = new FileReviewDataAccessObject("./reviews.csv", new ReviewFactory(),
                    userDataAccessObject);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        RegisterView registerView = RegisterUseCaseFactory.create(viewManagerModel, loginViewModel, registerViewModel,
                userDataAccessObject, apiRestaurantDataAccessObject);
        views.add(registerView, registerView.viewName);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel, userDataAccessObject);
        views.add(loginView, loginView.viewName);

         LoggedInView loggedInView = LoggedInUseCaseFactory.create(viewManagerModel, loggedInViewModel,
                viewRestaurantViewModel, apiRestaurantDataAccessObject,
                viewFavouritesViewModel, fileFavouritesDataAccessObject, userProfileViewModel, userDataAccessObject, userDataAccessObject, sortAndFilterViewModel);
        views.add(loggedInView, loggedInView.viewName);

        LoginController loginController = LoginUseCaseFactory.createLoginUseCase(viewManagerModel, loginViewModel, loggedInViewModel,
                userDataAccessObject);

        RestaurantController restaurantController = RestaurantUseCaseFactory.createRestaurantUseCase(
                viewManagerModel, restaurantViewModel, apiRestaurantDataAccessObject, userDataAccessObject);

        SortAndFilterController sortAndFilterController = SortAndFilterUseCaseFactory.createSortAndFilterUseCase(viewManagerModel, sortAndFilterViewModel, apiRestaurantDataAccessObject);
        SearchRestaurantController searchRestaurantController = ViewRestaurantUseCaseFactory.createSearchRestaurantUseCase(viewManagerModel, viewRestaurantViewModel, apiRestaurantDataAccessObject, sortAndFilterViewModel);
        ViewRestaurantView viewRestaurantView = ViewRestaurantUseCaseFactory.create(viewManagerModel,
                viewRestaurantViewModel, apiRestaurantDataAccessObject, userDataAccessObject, apiRestaurantDataAccessObject,
                loginController, restaurantController, sortAndFilterController, sortAndFilterViewModel);
        views.add(viewRestaurantView, viewRestaurantView.viewName);

        ViewRestaurantController viewRestaurantController = ViewRestaurantUseCaseFactory.createViewRestaurantUseCase(viewManagerModel, viewRestaurantViewModel,
                apiRestaurantDataAccessObject, userDataAccessObject, sortAndFilterViewModel);

        ViewFavouritesView viewFavouritesView = ViewFavouritesUseCaseFactory.create(viewManagerModel, restaurantViewModel,
                loginViewModel, loggedInViewModel, viewFavouritesViewModel, removeFavouriteViewModel, apiRestaurantDataAccessObject,
                userDataAccessObject, fileFavouritesDataAccessObject);
        views.add(viewFavouritesView, ViewFavouritesView.viewName);

        RestaurantView restaurantView = RestaurantUseCaseFactory.create(viewManagerModel, restaurantViewModel,
                addReviewViewModel, addToFavouritesViewModel, apiRestaurantDataAccessObject, userDataAccessObject,
                viewRestaurantController,
                fileFavouritesDataAccessObject, viewFavouritesViewModel, fileFavouritesDataAccessObject);
        views.add(restaurantView, restaurantView.viewName);

        SortAndFilterView sortAndFilterView = new SortAndFilterView(sortAndFilterController, sortAndFilterViewModel,
                viewRestaurantViewModel);
        SortAndFilterView sortAndFilterView = SortAndFilterUseCaseFactory.create(viewManagerModel, viewRestaurantViewModel, sortAndFilterViewModel, apiRestaurantDataAccessObject, viewRestaurantController, restaurantController);
        views.add(sortAndFilterView, sortAndFilterView.viewName);

        WriteReviewView writeReviewView = WriteReviewUseCaseFactory.create(viewManagerModel, addReviewViewModel,
                writeReviewViewModel, restaurantViewModel, fileReviewDataAccessObject, apiRestaurantDataAccessObject,
                userDataAccessObject);
        views.add(writeReviewView, writeReviewView.viewName);

        UserProfileView userProfileView = UserProfileUseCaseFactory.create(viewManagerModel, userProfileViewModel,
                userDataAccessObject, loginController);
        views.add(userProfileView, userProfileView.viewName);

        viewManagerModel.setActiveView(registerView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}