package interface_adapter.view_favourites;


public class ViewFavouritesState {
    private int userID;
    public ViewFavouritesState(ViewFavouritesState copy) {
        userID = copy.userID;
    }

    public ViewFavouritesState(){};

    public int getUserID() {
        return userID;
    }
}
