package use_case.remove_favourite;

import entity.FavouritesList;
import entity.Restaurant;

public class RemoveFavouriteOutputData {
    String message;
    FavouritesList newFavouritesList;

    public RemoveFavouriteOutputData(Restaurant restaurant, FavouritesList newFavouritesList){
        message = restaurant.getRestaurantName() + " has been removed from favourites.";
        this.newFavouritesList = newFavouritesList;
    }

    public String getMessage() {
        return message;
    }

    public FavouritesList getNewFavouritesList() {
        return newFavouritesList;
    }
}
