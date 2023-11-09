package use_case.view_favourites;

import javax.swing.text.View;
import interface_adapter.view_favourites.ViewFavouritesState;

public class ViewFavouritesInputData {
    private final String username;
    public ViewFavouritesInputData(String username){
        this.username = username;
    };

    public String getUsername() {
        return username;
    }
}