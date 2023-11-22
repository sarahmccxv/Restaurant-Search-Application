package use_case.add_to_favourites;

public interface AddToFavouritesOutputBoundary {
    void prepareSuccessView(AddToFavouritesOutputData addToFavouritesOutputData);
    void prepareFailView(AddToFavouritesOutputData addToFavouritesOutputData);
}
