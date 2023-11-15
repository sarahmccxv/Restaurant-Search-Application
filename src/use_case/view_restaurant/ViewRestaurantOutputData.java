package use_case.view_restaurant;

import entity.Restaurant;

import java.util.ArrayList;

public class ViewRestaurantOutputData {
    private ArrayList<Restaurant> restaurants;

    public ViewRestaurantOutputData(ArrayList<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public ArrayList<Restaurant> getLocalRestaurants() {
        return restaurants;
    }
}
