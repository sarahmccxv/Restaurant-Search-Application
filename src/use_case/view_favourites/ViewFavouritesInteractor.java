package use_case.view_favourites;

import entity.FavouritesList;
import entity.User;

import java.util.ArrayList;
import use_case.view_favourites.ViewFavouritesOutputBoundary;

public class ViewFavouritesInteractor implements ViewFavouritesInputBoundary{
    final ViewFavouritesDataAccessInterface favouritesDataAccessObject;
    final ViewFavouritesOutputBoundary viewFavouritesPresenter;

    public ViewFavouritesInteractor(ViewFavouritesDataAccessInterface userdataAccessObject,
                                    ViewFavouritesOutputBoundary viewFavouritesOutputBoundary){
        this.favouritesDataAccessObject = userdataAccessObject;
        this.viewFavouritesPresenter = viewFavouritesOutputBoundary;
    }

    @Override
    public void execute(ViewFavouritesInputData ViewFavouritesInputData) {
//        FavouritesList favouritesList = favouritesDataAccessObject.getFavouritesList();
//        ViewFavouritesOutputData outputData = new ViewFavouritesOutputData(deleted_users);
//        favouritesDataAccessObject.clear();
//        viewFavouritesPresenter.prepareSuccessView(outputData);
    }
}
