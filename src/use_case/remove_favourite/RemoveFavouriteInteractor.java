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
        User user = fileUserDataAccessObject.getByUsername(username);
        Restaurant restaurant = removeFavouriteInputData.getRestaurant();
        user.removeFavourite(restaurant.getRestaurantID());
        removeFavouriteDataAccessObject.removeFavourite(user, restaurant);
        RemoveFavouriteOutputData removeFavouriteOutputData = new RemoveFavouriteOutputData(restaurant, user.getFavouritesList());
        removeFavouritePresenter.prepareSuccessView(removeFavouriteOutputData);
    }
}
