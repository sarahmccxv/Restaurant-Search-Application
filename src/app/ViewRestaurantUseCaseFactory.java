package app;

import entity.RestaurantFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginController;
import interface_adapter.restaurant.RestaurantController;
import interface_adapter.search_restaurants.SearchRestaurantController;
import interface_adapter.search_restaurants.SearchRestaurantPresenter;
import interface_adapter.sort_and_filter.SortAndFilterController;
import interface_adapter.sort_and_filter.SortAndFilterViewModel;
import interface_adapter.view_restaurants.ViewRestaurantController;
import interface_adapter.view_restaurants.ViewRestaurantPresenter;
import interface_adapter.view_restaurants.ViewRestaurantViewModel;
import use_case.register.RegisterUserDataAccessInterface;
import use_case.search_restaurant.SearchRestaurantDataAccessInterface;
import use_case.search_restaurant.SearchRestaurantInputBoundary;
import use_case.search_restaurant.SearchRestaurantInteractor;
import use_case.search_restaurant.SearchRestaurantOutputBoundary;
import use_case.view_restaurant.*;
import view.ViewRestaurantView;

import java.io.IOException;

public class ViewRestaurantUseCaseFactory {
    private ViewRestaurantUseCaseFactory() {}
    public static ViewRestaurantView create(ViewManagerModel viewManagerModel,
                                            ViewRestaurantViewModel viewRestaurantViewModel,
                                            ViewRestaurantDataAccessInterface viewRestaurantDataAccessObject,
                                            RegisterUserDataAccessInterface fileUserDataAccessObject,
                                            SearchRestaurantDataAccessInterface searchRestaurantDataAccessObject,
                                            LoginController loginController,
                                            RestaurantController restaurantController,
                                            SortAndFilterController sortAndFilterController,
                                            SortAndFilterViewModel sortAndFilterViewModel) {
        ViewRestaurantController viewRestaurantController = createViewRestaurantUseCase(viewManagerModel,
                viewRestaurantViewModel, viewRestaurantDataAccessObject, fileUserDataAccessObject);
        SearchRestaurantController searchRestaurantController = createSearchRestaurantUseCase(viewManagerModel, viewRestaurantViewModel,searchRestaurantDataAccessObject);
        return new ViewRestaurantView(viewRestaurantViewModel, viewRestaurantController,
                loginController, restaurantController, sortAndFilterController, sortAndFilterViewModel, searchRestaurantController);
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

    public static SearchRestaurantController createSearchRestaurantUseCase(ViewManagerModel viewManagerModel, ViewRestaurantViewModel viewRestaurantViewModel, SearchRestaurantDataAccessInterface searchRestaurantDataAccessObject){
        SearchRestaurantOutputBoundary searchRestaurantOutputBoundary = new SearchRestaurantPresenter(viewManagerModel, viewRestaurantViewModel);
        SearchRestaurantInputBoundary searchRestaurantInteractor = new SearchRestaurantInteractor(searchRestaurantDataAccessObject, searchRestaurantOutputBoundary);
        return new SearchRestaurantController(searchRestaurantInteractor);
    }
}
