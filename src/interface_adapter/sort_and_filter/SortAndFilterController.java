package interface_adapter.sort_and_filter;

import use_case.view_favourites.ViewFavouritesInputBoundary;
import use_case.view_favourites.ViewFavouritesInputData;

public class SortAndFilterController {
    final ViewFavouritesInputBoundary viewFavouritesInteractor;

    public SortAndFilterController(ViewFavouritesInputBoundary viewFavouritesInteractor) {
        this.viewFavouritesInteractor = viewFavouritesInteractor;
    }

    public void execute(String username) {
        ViewFavouritesInputData viewFavouritesinputData = new ViewFavouritesInputData(username);
        viewFavouritesInteractor.execute(viewFavouritesinputData);
    }
}