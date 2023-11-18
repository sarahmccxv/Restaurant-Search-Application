package entity;

import java.util.ArrayList;
import java.util.Objects;

public class FavouritesList {
    private ArrayList<Restaurant> favouritesList = new ArrayList<>();

    public FavouritesList(){
        this.favouritesList = new ArrayList<Restaurant>();
    }

    public void add(Restaurant favourite){
        favouritesList.add(favourite);
    }

    public ArrayList<Restaurant> getFavourites(){
        return favouritesList;
    }

    public void remove(String restaurantID){
        favouritesList.removeIf(restaurant -> restaurant.getRestaurantID().equals(restaurantID));
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        for (Restaurant favourite : favouritesList){
            result.append(favourite.toString()).append(",");
        }
        result.delete(result.length() - 1, result.length());
        return result.toString();
    }
}