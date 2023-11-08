package use_case.view_favourites;

import entity.FavouritesList;
import entity.Restaurant;
import entity.User;
import entity.Restaurant;

import java.util.ArrayList;

public interface ViewFavouritesDataAccessInterface {

    void saveFavourites(User user);

    FavouritesList getFavouritesList(String userID);

}
