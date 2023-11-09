package use_case.view_favourites;

import entity.FavouritesList;
import entity.Restaurant;

import java.util.ArrayList;

public class ViewFavouritesOutputData {
    private String username;
    private ArrayList<String> favouritesListString = new ArrayList<String>();

    public ViewFavouritesOutputData(String username, FavouritesList favouritesList) {
        this.username = username;
        for(Restaurant restaurant : favouritesList.getFavourites()){
            favouritesListString.add(restaurant.toString());
        }
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<String> getFavouritesList() {
        return favouritesListString;
    }


}