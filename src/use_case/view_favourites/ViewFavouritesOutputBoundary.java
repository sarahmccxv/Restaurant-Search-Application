package use_case.view_favourites;

import use_case.view_favourites.ViewFavouritesOutputData;
public interface ViewFavouritesOutputBoundary {
    void prepareSuccessView(ViewFavouritesOutputData outputData);
    void prepareFailView(String username, String password, String noFavouritesMessage);
}