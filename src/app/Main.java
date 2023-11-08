package app;


import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import entity.RestaurantFactory;
import interface_adapter.ViewManagerModel;
import view.ViewManager;
import view.ViewFavouritesView;
import interface_adapter.view_favourites.ViewFavouritesViewModel;
import data_access.FileFavouritesDataAccessObject;
import interface_adapter.logged_in.LoggedInState;

public class Main {
    public static void main(String[] args) {
        JFrame application = new JFrame("View Favourites");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        CardLayout cardLayout = new CardLayout();
        JPanel views = new JPanel(cardLayout);
        application.add(views);
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        ViewFavouritesViewModel viewFavouritesViewModel = new ViewFavouritesViewModel();
        FileFavouritesDataAccessObject fileFavouritesDataAccessObject;
        try {
            fileFavouritesDataAccessObject = new FileFavouritesDataAccessObject("./favourites.csv", new RestaurantFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        ViewFavouritesView viewFavouritesView = ViewFavouritesUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, userDataAccessObject);
        views.add(viewFavouritesView, viewFavouritesView.viewName);
        viewManagerModel.setActiveView(viewFavouritesView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
