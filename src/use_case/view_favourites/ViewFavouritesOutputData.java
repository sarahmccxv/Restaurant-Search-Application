package use_case.view_favourites;

import entity.FavouritesList;
import entity.Restaurant;

import java.util.ArrayList;

public class ViewFavouritesOutputData {
    private String username;
    private String password;
    private ArrayList<String> favouritesListString = new ArrayList<>();

    public ViewFavouritesOutputData(String username, String password, FavouritesList favouritesList) {
        this.username = username;
        this.password = password;
        for (Restaurant restaurant : favouritesList.getFavourites()){
            favouritesListString.add(restaurant.toString());
        }
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<String> getFavouritesList() {
        return favouritesListString;
    }

    public String getPassword() {
        return password;
    }
}