package interface_adapter.view_favourites;


import entity.FavouritesList;

public class ViewFavouritesState {
    private boolean success;
    private String userID;
    private String username;
    private String password;
    private FavouritesList favouritesList;
    private String noFavouritesMessage;

    public ViewFavouritesState(){
        success = false;
        username = "";
        password = "";
        favouritesList = new FavouritesList();
        noFavouritesMessage = "";
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public FavouritesList getFavouritesList() {
        return favouritesList;
    }
    public void setFavouritesList(FavouritesList favourites) {
        this.favouritesList = favourites;
    }
    public String getNoFavouritesMessage() {
        return noFavouritesMessage;
    }
    public void setNoFavouritesMessage(String noFavouritesMessage){
        this.noFavouritesMessage = noFavouritesMessage;
    }
    public void setSuccess(boolean success) {this.success = success;}
    public boolean getSuccess() {return success;}
    public String getUserID() {return userID;}
    public void setUserID(String userID) {this.userID = userID;}
}
