package interface_adapter.restaurant;

import entity.Restaurant;

public class RestaurantState {
    private String userID;
    private String username;
    private String password;
    private Restaurant restaurant;

    public RestaurantState(){
        userID = "";
        username = "";
        password = "";
        restaurant = null;
    }

    public String getUserID() { return userID; }

    public String getUsername() {
        return username;
    }

    public String getPassword() { return password; }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
    public Restaurant getRestaurant(){
        return restaurant;
    }
}
