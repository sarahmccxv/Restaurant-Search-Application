package interface_adapter.restaurant;

import entity.Restaurant;

public class RestaurantState {
    private Integer userID;
    private String username;
    private String password;
    private Restaurant restaurant;

    public RestaurantState(){
        userID = 0;
        username = "";
        password = "";
        restaurant = null;
    }

    public Integer getUserID() { return userID; }

    public String getUsername() {
        return username;
    }

    public String getPassword() { return password; }

    public void setUserID(Integer userID) {
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
