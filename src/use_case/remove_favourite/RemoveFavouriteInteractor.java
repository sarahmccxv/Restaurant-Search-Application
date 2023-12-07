package use_case.remove_favourite;

import entity.FavouritesList;
import entity.Restaurant;
import entity.User;
import use_case.register.RegisterUserDataAccessInterface;

public class RemoveFavouriteInteractor implements RemoveFavouriteInputBoundary{
    RegisterUserDataAccessInterface fileUserDataAccessObject;
    RemoveFavouriteDataAccessInterface removeFavouriteDataAccessObject;
    RemoveFavouriteOutputBoundary removeFavouritePresenter;

    public RemoveFavouriteInteractor(RegisterUserDataAccessInterface fileUserDataAccessObject,
                                     RemoveFavouriteDataAccessInterface removeFavouriteDataAccessObject,
                                     RemoveFavouriteOutputBoundary removeFavouritePresenter){
        this.fileUserDataAccessObject = fileUserDataAccessObject;
        this.removeFavouriteDataAccessObject = removeFavouriteDataAccessObject;
        this.removeFavouritePresenter = removeFavouritePresenter;
    }

    public void execute(RemoveFavouriteInputData removeFavouriteInputData){
        String username = removeFavouriteInputData.getUsername();
        Restaurant restaurant = removeFavouriteInputData.getRestaurant();
        removeFavouriteDataAccessObject.removeFavourite(username, restaurant);
        FavouritesList newFavouritesList = removeFavouriteDataAccessObject.getFavouritesList(username);
        RemoveFavouriteOutputData removeFavouriteOutputData = new RemoveFavouriteOutputData(restaurant, newFavouritesList);
        removeFavouritePresenter.prepareSuccessView(removeFavouriteOutputData);
    }
}
