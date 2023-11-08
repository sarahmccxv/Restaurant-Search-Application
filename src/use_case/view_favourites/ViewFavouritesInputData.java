package use_case.view_favourites;

import javax.swing.text.View;
import interface_adapter.view_favourites.ViewFavouritesState;

public class ViewFavouritesInputData {
    private final int userID;
    public ViewFavouritesInputData(int userID){
        this.userID = userID;
    };

    public int getUserID() {
        return userID;
    }
}
