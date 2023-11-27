package use_case.restaurant;

import entity.Restaurant;
import entity.User;

public class RestaurantOutputData {
    private String userID;
    private String username;
    private String password;
    private Restaurant restaurant;
    private String previousView;


    public RestaurantOutputData(String userID, String username, String password, Restaurant restaurant,
                                String previousView) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.restaurant = restaurant;
        this.previousView = previousView;
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
    public String getPreviousView() {return previousView;}
}
