package interface_adapter.view_favourites;

import use_case.view_favourites.ViewFavouritesInputBoundary;
import use_case.view_favourites.ViewFavouritesInputData;

public class ViewFavouritesController {
    final ViewFavouritesInputBoundary ViewFavouritesInteractor;

    public ViewFavouritesController(ViewFavouritesInputBoundary viewFavouritesInteractor) {
        this.ViewFavouritesInteractor = viewFavouritesInteractor;
    }

    public void execute(int userID) {
        ViewFavouritesInputData viewFavouritesinputData = new ViewFavouritesInputData(userID);
        ViewFavouritesInteractor.execute(viewFavouritesinputData);
    }
}