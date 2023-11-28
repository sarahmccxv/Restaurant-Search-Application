package use_case.view_favourites;

public interface ViewFavouritesOutputBoundary {
    void prepareSuccessView(ViewFavouritesOutputData outputData);
    void prepareFailView(String username, String password, String noFavouritesMessage);
}