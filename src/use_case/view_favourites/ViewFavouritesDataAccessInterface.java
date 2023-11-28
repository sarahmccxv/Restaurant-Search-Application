package use_case.view_favourites;

import entity.FavouritesList;

public interface ViewFavouritesDataAccessInterface {

    FavouritesList getFavouritesList(String userID);

    boolean hasFavourites(String username);
}
