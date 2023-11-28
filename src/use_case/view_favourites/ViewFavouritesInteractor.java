package use_case.view_favourites;

import entity.FavouritesList;
import use_case.register.RegisterUserDataAccessInterface;

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
            String userID = fileUserDataAccessObject.getByUsername(username).getUserID();
            ViewFavouritesOutputData viewFavouritesOutputData = new ViewFavouritesOutputData(userID, username, password, favouritesList);
            viewFavouritesPresenter.prepareSuccessView(viewFavouritesOutputData);
        } else {
            viewFavouritesPresenter.prepareFailView(username, password, "No Favourites");
        }
    }
}
