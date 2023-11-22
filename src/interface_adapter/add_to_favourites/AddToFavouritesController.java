package interface_adapter.add_to_favourites;

import entity.Restaurant;
import use_case.add_to_favourites.AddToFavouritesInputBoundary;
import use_case.add_to_favourites.AddToFavouritesInputData;

public class AddToFavouritesController {
    final AddToFavouritesInputBoundary addToFavouritesInteractor;

    public AddToFavouritesController(AddToFavouritesInputBoundary addToFavouritesInteractor){
        this.addToFavouritesInteractor = addToFavouritesInteractor;
    }

    public void execute(String username, Restaurant restaurant){
        AddToFavouritesInputData addToFavouritesInputData = new AddToFavouritesInputData(username, restaurant);
        addToFavouritesInteractor.execute(addToFavouritesInputData);
        System.out.println("Add To Favourites Controller called");
    }

}
