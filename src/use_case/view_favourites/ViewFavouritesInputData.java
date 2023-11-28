package use_case.view_favourites;

public class ViewFavouritesInputData {
    private final String username;

    public ViewFavouritesInputData(String username){
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

}