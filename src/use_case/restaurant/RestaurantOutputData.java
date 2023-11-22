package use_case.restaurant;

import entity.Restaurant;

public class RestaurantOutputData {
    private String userID;
    private String username;
    private String password;
    private Restaurant restaurant;

    public RestaurantOutputData(String userID, String username, String password, Restaurant restaurant) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.restaurant = restaurant;
    }

    public String getUserID() {
        return userID;
    }
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }
}
