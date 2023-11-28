package use_case.view_favourites;

import entity.FavouritesList;

public class ViewFavouritesOutputData {
    private final String userID;
    private final String username;
    private final String password;
    private final FavouritesList favouritesList;

    public ViewFavouritesOutputData(String userID, String username, String password, FavouritesList favouritesList) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.favouritesList = favouritesList;
    }

    public String getUsername() {
        return username;
    }

    public FavouritesList getFavouritesList() {
        return favouritesList;
    }

    public String getPassword() {
        return password;
    }

    public String getUserID() {return userID;}
}