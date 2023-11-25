package interface_adapter.remove_favourite;

import entity.Restaurant;
import use_case.remove_favourite.RemoveFavouriteInputBoundary;
import use_case.remove_favourite.RemoveFavouriteInputData;
import use_case.remove_favourite.RemoveFavouriteInteractor;

public class RemoveFavouriteController {
    RemoveFavouriteInputBoundary removeFavouriteInteractor;

    public RemoveFavouriteController(RemoveFavouriteInputBoundary removeFavouriteInteractor){
        this.removeFavouriteInteractor = removeFavouriteInteractor;
    }

    public void execute(String username, Restaurant restaurant){
        RemoveFavouriteInputData removeFavouriteInputData = new RemoveFavouriteInputData(username, restaurant);
        removeFavouriteInteractor.execute(removeFavouriteInputData);
    }

}
