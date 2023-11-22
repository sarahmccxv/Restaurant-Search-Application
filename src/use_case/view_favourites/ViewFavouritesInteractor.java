package use_case.view_favourites;

import data_access.FileUserDataAccessObject;
import entity.FavouritesList;
import entity.User;

import java.util.ArrayList;

import interface_adapter.view_favourites.ViewFavouritesPresenter;
import use_case.register.RegisterUserDataAccessInterface;
import use_case.view_favourites.ViewFavouritesOutputBoundary;

public class ViewFavouritesInteractor implements ViewFavouritesInputBoundary{
    private final RegisterUserDataAccessInterface fileUserDataAccessObject;
    private final ViewFavouritesDataAccessInterface favouritesDataAccessObject;
    private final ViewFavouritesOutputBoundary viewFavouritesPresenter;

    public ViewFavouritesInteractor(ViewFavouritesDataAccessInterface favouritesDataAccessObject,
                                    ViewFavouritesOutputBoundary viewFavouritesOutputBoundary,
                                    RegisterUserDataAccessInterface fileUserDataAccessObject){
        this.favouritesDataAccessObject = favouritesDataAccessObject;
        this.viewFavouritesPresenter = viewFavouritesOutputBoundary;
        this.fileUserDataAccessObject = fileUserDataAccessObject;
    }

    @Override
    public void execute(ViewFavouritesInputData viewFavouritesInputData) {
        String username = viewFavouritesInputData.getUsername();
        String password = fileUserDataAccessObject.getByUsername(username).getPassword();
        if (favouritesDataAccessObject.hasFavourites(username)){
            FavouritesList favouritesList = favouritesDataAccessObject.getFavouritesList(username);
            int userID = fileUserDataAccessObject.get(username).getUserID();
            ViewFavouritesOutputData viewFavouritesOutputData = new ViewFavouritesOutputData(userID, username, password, favouritesList);
            viewFavouritesPresenter.prepareSuccessView(viewFavouritesOutputData);
        } else {
            viewFavouritesPresenter.prepareFailView(username, password, "No Favourites");
        }
    }
}
