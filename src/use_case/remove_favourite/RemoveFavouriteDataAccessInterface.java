package use_case.remove_favourite;

import entity.FavouritesList;
import entity.Restaurant;
import entity.User;

public interface RemoveFavouriteDataAccessInterface {
    void removeFavourite(String username, Restaurant restaurant);
    FavouritesList getFavouritesList(String username);
}
