package use_case.view_favourites;

import entity.FavouritesList;
import entity.User;

import java.util.ArrayList;

import interface_adapter.view_favourites.ViewFavouritesPresenter;
import use_case.view_favourites.ViewFavouritesOutputBoundary;

public class ViewFavouritesInteractor implements ViewFavouritesInputBoundary{
    final ViewFavouritesDataAccessInterface favouritesDataAccessObject;
    final ViewFavouritesOutputBoundary viewFavouritesPresenter;

    public ViewFavouritesInteractor(ViewFavouritesDataAccessInterface favouritesDataAccessObject,
                                    ViewFavouritesOutputBoundary viewFavouritesOutputBoundary){
        this.favouritesDataAccessObject = favouritesDataAccessObject;
        this.viewFavouritesPresenter = viewFavouritesOutputBoundary;
    }

    @Override
    public void execute(ViewFavouritesInputData viewFavouritesInputData) {
        String username = viewFavouritesInputData.getUsername();
        if (favouritesDataAccessObject.hasFavourites(username)){
            FavouritesList favouritesList = favouritesDataAccessObject.getFavouritesList(username);
            ViewFavouritesOutputData viewFavouritesOutputData = new ViewFavouritesOutputData(username, favouritesList);
            viewFavouritesPresenter.prepareSuccessView(viewFavouritesOutputData);
        } else {
            viewFavouritesPresenter.prepareFailView("No Favourites");
        }
    }
}
