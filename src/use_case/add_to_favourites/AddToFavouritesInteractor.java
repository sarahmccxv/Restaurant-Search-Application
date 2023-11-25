package use_case.add_to_favourites;


import entity.Restaurant;
import entity.User;
import use_case.register.RegisterUserDataAccessInterface;

public class AddToFavouritesInteractor implements AddToFavouritesInputBoundary{
    private final RegisterUserDataAccessInterface fileUserDataAccessObject;
    private final AddToFavouritesDataAccessInterface favouritesDataAccessObject;
    private final AddToFavouritesOutputBoundary addToFavouritesPresenter;

    public AddToFavouritesInteractor(RegisterUserDataAccessInterface fileUserDataAccessObject,
                                     AddToFavouritesDataAccessInterface favouritesDataAccessObject,
                                     AddToFavouritesOutputBoundary addToFavouritesPresenter){
        this.fileUserDataAccessObject = fileUserDataAccessObject;
        this.favouritesDataAccessObject = favouritesDataAccessObject;
        this.addToFavouritesPresenter = addToFavouritesPresenter;
    }

    public void execute(AddToFavouritesInputData addToFavouritesInputData){
        String username = addToFavouritesInputData.getUsername();
        User user = fileUserDataAccessObject.get(username);
        Restaurant restaurant = addToFavouritesInputData.getRestaurant();
        AddToFavouritesOutputData addToFavouritesOutputData = new AddToFavouritesOutputData(user, restaurant);
        if (!favouritesDataAccessObject.hasFavourite(user, restaurant)) {
            // if the restaurant is not already in favourites success view, add it
            user.addToFavourites(restaurant);
            favouritesDataAccessObject.saveFavourites(user);
            addToFavouritesPresenter.prepareSuccessView(addToFavouritesOutputData);
        } else {
            // else, inform the use that the favourites already exists in the favourites list
            addToFavouritesPresenter.prepareFailView(addToFavouritesOutputData);
        }
    }
}
