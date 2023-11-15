package interface_adapter.view_restaurants;

import entity.Restaurant;
import interface_adapter.view_favourites.ViewFavouritesState;

import java.util.ArrayList;

public class ViewRestaurantState {
    private ArrayList<Restaurant> restaurants;
    public ViewRestaurantState(){};
    public ViewRestaurantState(ViewRestaurantState copy) {
        restaurants = copy.restaurants;
    }

    public void setRestaurants(ArrayList<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public ArrayList<Restaurant> getLocalRestaurants(){
        return restaurants;
    }
}
