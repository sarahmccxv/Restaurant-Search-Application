package interface_adapter.view_restaurants;

import entity.Restaurant;
import interface_adapter.view_favourites.ViewFavouritesState;

import java.util.ArrayList;

public class ViewRestaurantState {
    private String userID;
    private String username;
    private String password;
    private String location;
    private ArrayList<Restaurant> restaurants;
    private String restaurantName;
    private String previousView;
    public ViewRestaurantState(){
        userID = "";
        username = "";
        password = "";
        location = "";
        restaurants = new ArrayList<>();
        restaurantName = "";
        previousView = "";
    };

    public String getUsername() {
        return username;
    }

    public String getUserID() { return userID; }

    public String getPassword() { return password; }
    public String getLocation() { return location; }
    public String getRestaurantName(){ return restaurantName;}
    public String getPreviousView(){return previousView;}

    public void setUserID(String userID) {
        this.userID = userID;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setRestaurants(ArrayList<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }
    public void setLocation(String location) { this.location = location; }
    public void setRestaurantName(String restaurantName){ this.restaurantName = restaurantName;}
    public void setPreviousView(String previousView){this.previousView = previousView;}

    public ArrayList<Restaurant> getRestaurants(){
        return restaurants;
    }
}
