package interface_adapter.view_restaurants;

import entity.Restaurant;
import interface_adapter.view_favourites.ViewFavouritesState;

import java.util.ArrayList;

public class ViewRestaurantState {
    private Integer userID;
    private String username;
    private String password;
    private ArrayList<Restaurant> restaurants;
    public ViewRestaurantState(){
        userID = 0;
        username = "";
        password = "";
        restaurants = new ArrayList<>();
    };

    public String getUsername() {
        return username;
    }

    public Integer getUserID() { return userID; }

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
    public void setRestaurants(ArrayList<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public ArrayList<Restaurant> getRestaurants(){
        return restaurants;
    }
}
