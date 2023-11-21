package interface_adapter.add_to_favourites;

import entity.Restaurant;

import java.util.ArrayList;

public class AddToFavouritesState {
    boolean successfullyAdded;
    String username;
    String restaurantName;
    String message;

    public AddToFavouritesState(){
    };

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setSuccessfullyAdded(boolean successfullyAdded){
        this.successfullyAdded = successfullyAdded;
    }

    public boolean getSuccessfullyAdded(){
        return this.successfullyAdded;
    }
}
