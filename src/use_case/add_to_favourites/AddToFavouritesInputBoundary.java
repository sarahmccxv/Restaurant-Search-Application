package use_case.add_to_favourites;

import use_case.view_restaurant.ViewRestaurantInputData;

public interface AddToFavouritesInputBoundary {
    void execute(AddToFavouritesInputData addToFavouritesInputData);
}
