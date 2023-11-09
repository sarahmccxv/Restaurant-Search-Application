package interface_adapter.view_favourites;


import java.util.ArrayList;

public class ViewFavouritesState {
    private String username;
    private ArrayList<String> favourites;
    private String noFavouritesMessage;

    public ViewFavouritesState(){};

    public ViewFavouritesState(ViewFavouritesState copy) {
        username = copy.username;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername() {
        this.username = username;
    }

    public ArrayList<String> getFavourites() {
        return favourites;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFavourites(ArrayList<String> favourites) {
        this.favourites = favourites;
    }

    public void setNoFavourites(String noFavouritesMessage){
        this.noFavouritesMessage = noFavouritesMessage;
    }
}
