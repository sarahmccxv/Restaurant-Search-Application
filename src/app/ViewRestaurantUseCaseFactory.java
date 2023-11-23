package app;

import entity.RestaurantFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginController;
import interface_adapter.restaurant.RestaurantController;
import interface_adapter.view_restaurants.ViewRestaurantController;
import interface_adapter.view_restaurants.ViewRestaurantPresenter;
import interface_adapter.view_restaurants.ViewRestaurantViewModel;
import use_case.register.RegisterUserDataAccessInterface;
import use_case.view_restaurant.*;
import view.ViewRestaurantView;

public class ViewRestaurantUseCaseFactory {
    private ViewRestaurantUseCaseFactory() {}
    public static ViewRestaurantView create(ViewManagerModel viewManagerModel,
                                            ViewRestaurantViewModel viewRestaurantViewModel,
                                            ViewRestaurantDataAccessInterface viewRestaurantDataAccessObject,
                                            RegisterUserDataAccessInterface fileUserDataAccessObject,
                                            LoginController loginController,
                                            RestaurantController restaurantController) {
        ViewRestaurantController viewRestaurantController = createViewRestaurantUseCase(viewManagerModel,
                viewRestaurantViewModel, viewRestaurantDataAccessObject, fileUserDataAccessObject);
        return new ViewRestaurantView(viewRestaurantViewModel, viewRestaurantController,
                loginController, restaurantController);
    }

    public static ViewRestaurantController createViewRestaurantUseCase(ViewManagerModel viewManagerModel,
                                                                        ViewRestaurantViewModel viewRestaurantViewModel,
                                                                        ViewRestaurantDataAccessInterface
                                                                        viewRestaurantDataAccessObject,
                                                                        RegisterUserDataAccessInterface
                                                                                fileUserDataAccessObject)
    {
        ViewRestaurantOutputBoundary viewRestaurantOutputBoundary = new ViewRestaurantPresenter(
                viewManagerModel, viewRestaurantViewModel);
        RestaurantFactory restaurantFactory = new RestaurantFactory();
        ViewRestaurantInputBoundary viewRestaurantInteractor = new ViewRestaurantInteractor(
                viewRestaurantOutputBoundary, viewRestaurantDataAccessObject, fileUserDataAccessObject);
        return new ViewRestaurantController(viewRestaurantInteractor);
    }
}
