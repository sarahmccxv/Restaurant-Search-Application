package app;

import entity.RestaurantFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.restaurant.RestaurantController;
import interface_adapter.restaurant.RestaurantPresenter;
import interface_adapter.restaurant.RestaurantViewModel;
import interface_adapter.view_restaurants.ViewRestaurantController;
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
                                        ViewRestaurantDataAccessInterface RestaurantDataAccessObject,
                                        RegisterUserDataAccessInterface fileUserDataAccessObject,
                                        ViewRestaurantController viewRestaurantController) {
        RestaurantController restaurantController = createRestaurantUseCase(viewManagerModel,
                restaurantViewModel, RestaurantDataAccessObject, fileUserDataAccessObject);
        return new RestaurantView(restaurantViewModel, restaurantController, viewRestaurantController);
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
}
