package interface_adapter.view_favourites;

import use_case.view_favourites.ViewFavouritesInputBoundary;
import use_case.view_favourites.ViewFavouritesInputData;

public class ViewFavouritesController {
    final ViewFavouritesInputBoundary viewFavouritesInteractor;

    public ViewFavouritesController(ViewFavouritesInputBoundary viewFavouritesInteractor) {
        this.viewFavouritesInteractor = viewFavouritesInteractor;
    }

    public void execute(String username) {
        ViewFavouritesInputData viewFavouritesInputData = new ViewFavouritesInputData(username);
        viewFavouritesInteractor.execute(viewFavouritesInputData);
    }
}