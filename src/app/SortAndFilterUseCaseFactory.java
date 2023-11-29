package app;

import entity.RestaurantFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.restaurant.RestaurantController;
import interface_adapter.sort_and_filter.SortAndFilterController;
import interface_adapter.sort_and_filter.SortAndFilterPresenter;
import interface_adapter.sort_and_filter.SortAndFilterViewModel;
import interface_adapter.view_restaurants.ViewRestaurantViewModel;
import use_case.register.RegisterUserDataAccessInterface;
import use_case.sortandfilter.SortAndFilterRestaurantDataAccessInterface;
import use_case.sortandfilter.SortAndFilterRestaurantInputBoundary;
import use_case.sortandfilter.SortAndFilterRestaurantInteractor;
import use_case.sortandfilter.SortAndFilterRestaurantOutputBoundary;
import view.SortAndFilterView;

public class SortAndFilterUseCaseFactory {
    private SortAndFilterUseCaseFactory(){}
    public static SortAndFilterView create(ViewManagerModel viewManagerModel,
                                            ViewRestaurantViewModel viewRestaurantViewModel,
                                            SortAndFilterViewModel sortAndFilterViewModel,
                                            SortAndFilterRestaurantDataAccessInterface sortAndFilterRestaurantDataAccessObject) {
        SortAndFilterController sortAndFilterController = createSortAndFilterUseCase(viewManagerModel,
                sortAndFilterViewModel, sortAndFilterRestaurantDataAccessObject);
        return new SortAndFilterView(sortAndFilterController, sortAndFilterViewModel, viewManagerModel);
    }

    public static SortAndFilterController createSortAndFilterUseCase(ViewManagerModel viewManagerModel,
                                                                       SortAndFilterViewModel sortAndFilterViewModel,
                                                                       SortAndFilterRestaurantDataAccessInterface
                                                                               sortAndFilterRestaurantDataAccessObject)
    {
        SortAndFilterRestaurantOutputBoundary sortAndFilterRestaurantOutputBoundary = new SortAndFilterPresenter(
                viewManagerModel, sortAndFilterViewModel);
        SortAndFilterRestaurantInputBoundary sortAndFilterInteractor = new SortAndFilterRestaurantInteractor(
                sortAndFilterRestaurantDataAccessObject, sortAndFilterRestaurantOutputBoundary);
        return new SortAndFilterController(sortAndFilterInteractor);
    }
}
