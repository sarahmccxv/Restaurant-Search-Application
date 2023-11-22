package use_case.add_to_favourites;

import entity.Restaurant;
import entity.User;

public class AddToFavouritesOutputData {
    String username;
    String restaurantName;
    String successMessage;
    String failureMessage;

    public AddToFavouritesOutputData(User user, Restaurant restaurant){
        username = user.getUsername();
        restaurantName = restaurant.getRestaurantName();
        successMessage = restaurantName + " was successfully added to favourites.";
        failureMessage = restaurantName + " already exists in favourites.";
    }

    public String getUsername(){return username;}
    public String getRestaurantName(){return  restaurantName;}
    public String getFailureMessage() {
        return failureMessage;
    }
    public String getSuccessMessage() {
        return successMessage;
    }
}
