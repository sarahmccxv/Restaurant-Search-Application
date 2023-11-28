package use_case.remove_favourite;

import entity.Restaurant;
import entity.User;

public interface RemoveFavouriteDataAccessInterface {
    void removeFavourite(User user, Restaurant restaurant);
}
