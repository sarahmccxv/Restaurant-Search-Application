package app;

import data_access.FileFavouritesDataAccessObject;
import entity.RestaurantFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_to_favourites.AddToFavouritesController;
import interface_adapter.add_to_favourites.AddToFavouritesPresenter;
import interface_adapter.add_to_favourites.AddToFavouritesViewModel;
import interface_adapter.restaurant.RestaurantController;
import interface_adapter.restaurant.RestaurantPresenter;
import interface_adapter.restaurant.RestaurantViewModel;
import interface_adapter.view_restaurants.ViewRestaurantController;
import use_case.add_to_favourites.AddToFavouritesDataAccessInterface;
import use_case.add_to_favourites.AddToFavouritesInputBoundary;
import use_case.add_to_favourites.AddToFavouritesInteractor;
import use_case.add_to_favourites.AddToFavouritesOutputBoundary;
import use_case.register.RegisterUserDataAccessInterface;
import use_case.restaurant.RestaurantInputBoundary;
import use_case.restaurant.RestaurantInteractor;
import use_case.restaurant.RestaurantOutputBoundary;
import use_case.view_restaurant.ViewRestaurantDataAccessInterface;
import view.RestaurantView;

public class RestaurantUseCaseFactory {
    private RestaurantUseCaseFactory() {}

    public static RestaurantView create(ViewManagerModel viewManagerModel,
                                        RestaurantViewModel restaurantViewModel,
                                        AddToFavouritesViewModel addToFavouritesViewModel,
                                        ViewRestaurantDataAccessInterface RestaurantDataAccessObject,
                                        RegisterUserDataAccessInterface fileUserDataAccessObject,
                                        ViewRestaurantController viewRestaurantController,
                                        AddToFavouritesDataAccessInterface favouritesDataAccessObject) {
        RestaurantController restaurantController = createRestaurantUseCase(viewManagerModel,
                restaurantViewModel, RestaurantDataAccessObject, fileUserDataAccessObject);
        AddToFavouritesController addToFavouritesController = createAddToFavouritesUseCase(viewManagerModel, addToFavouritesViewModel,
                favouritesDataAccessObject, fileUserDataAccessObject);
        return new RestaurantView(restaurantViewModel, restaurantController, viewRestaurantController,
                addToFavouritesController, addToFavouritesViewModel);
    }

    public static RestaurantController createRestaurantUseCase(ViewManagerModel viewManagerModel,
                                                                RestaurantViewModel restaurantViewModel,
                                                                ViewRestaurantDataAccessInterface
                                                                        restaurantDataAccessObject,
                                                                RegisterUserDataAccessInterface
                                                                        fileUserDataAccessObject) {
        RestaurantOutputBoundary restaurantOutputBoundary = new RestaurantPresenter(
                viewManagerModel, restaurantViewModel);
        RestaurantFactory restaurantFactory = new RestaurantFactory();
        RestaurantInputBoundary RestaurantInteractor = new RestaurantInteractor(
                fileUserDataAccessObject, restaurantDataAccessObject, restaurantOutputBoundary);
        return new RestaurantController(RestaurantInteractor);
    }

    public static AddToFavouritesController createAddToFavouritesUseCase(ViewManagerModel viewManagerModel,
                                                                         AddToFavouritesViewModel addToFavouritesViewModel,
                                                                         AddToFavouritesDataAccessInterface favouritesDataAccessObject,
                                                                         RegisterUserDataAccessInterface fileUserDataAccessObject){
        AddToFavouritesOutputBoundary addToFavouritesOutputBoundary = new AddToFavouritesPresenter(viewManagerModel, addToFavouritesViewModel);
        AddToFavouritesInputBoundary addToFavouritesInteractor = new AddToFavouritesInteractor(
                fileUserDataAccessObject, favouritesDataAccessObject, addToFavouritesOutputBoundary);
        return new AddToFavouritesController(addToFavouritesInteractor);

    }
}
