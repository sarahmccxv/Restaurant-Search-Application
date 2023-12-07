package use_case.add_to_favourites;

import entity.FavouritesList;
import entity.Restaurant;
import entity.User;

public interface AddToFavouritesDataAccessInterface {
    boolean hasFavourite(User user, Restaurant restaurant);
    void saveFavourites(User user);

    FavouritesList getFavouritesList(String username);
}
