package entity;

import java.util.ArrayList;

public class FavouritesList {
    private final ArrayList<Restaurant> favouritesList;

    public FavouritesList(){
        this.favouritesList = new ArrayList<>();
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
        if (favouritesList.isEmpty()){
            return "";
        }
        StringBuilder result = new StringBuilder();
        for (Restaurant favourite : favouritesList){
            result.append(favourite.getRestaurantID()).append(",");
        }
        result.delete(result.length() - 1, result.length());
        return result.toString();
    }

    public boolean isEmpty() {
        return favouritesList.isEmpty();
    }
}