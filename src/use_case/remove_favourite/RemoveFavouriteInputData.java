package use_case.remove_favourite;

import entity.Restaurant;

public class RemoveFavouriteInputData {
    String username;
    Restaurant restaurant;
    public RemoveFavouriteInputData(String username, Restaurant restaurant) {
        this.username = username;
        this.restaurant = restaurant;
    }

    public String getUsername() {
        return username;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }
}
