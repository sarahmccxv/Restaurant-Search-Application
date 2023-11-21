package interface_adapter.restaurant;

import entity.Restaurant;

public class RestaurantState {
    private Integer userID;
    private String username;
    private String password;
    private Restaurant restaurant;
    private String previousView;

    public RestaurantState(){
        userID = 0;
        username = "";
        password = "";
        restaurant = null;
        previousView = "";
    }

    public Integer getUserID() { return userID; }
    public String getUsername() {
        return username;
    }
    public String getPassword() { return password; }
    public Restaurant getRestaurant(){
        return restaurant;
    }
    public String getPreviousView() {return previousView;}

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
    public void setPreviousView(String previousView) {this.previousView = previousView;}
}
