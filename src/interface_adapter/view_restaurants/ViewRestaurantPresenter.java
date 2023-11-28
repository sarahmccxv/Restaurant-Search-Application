package interface_adapter.view_restaurants;

import api.Search.SearchCriteria;
import interface_adapter.ViewManagerModel;
import interface_adapter.sort_and_filter.SortAndFilterState;
import interface_adapter.sort_and_filter.SortAndFilterViewModel;
import use_case.search_restaurant.SearchResaturantInputData;
import use_case.view_restaurant.ViewRestaurantOutputBoundary;
import use_case.view_restaurant.ViewRestaurantOutputData;

public class ViewRestaurantPresenter implements ViewRestaurantOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final ViewRestaurantViewModel viewRestaurantViewModel;
    private final SortAndFilterViewModel sortAndFilterViewModel;

    public ViewRestaurantPresenter(ViewManagerModel viewManagerModel,
                                   ViewRestaurantViewModel viewRestaurantViewModel,
                                   SortAndFilterViewModel sortAndFilterViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.viewRestaurantViewModel = viewRestaurantViewModel;
        this.sortAndFilterViewModel = sortAndFilterViewModel;
    }


    @Override
    public void prepareSuccessView(ViewRestaurantOutputData viewRestaurantOutputData) {
        ViewRestaurantState viewRestaurantState = viewRestaurantViewModel.getState();
        viewRestaurantState.setUserID(viewRestaurantOutputData.getUserID());
        //System.out.println("This is ViewRestaurantPresentor. The UserID received from output data is " + viewRestaurantOutputData.getUserID());
        viewRestaurantState.setUsername(viewRestaurantOutputData.getUsername());
        viewRestaurantState.setPassword(viewRestaurantOutputData.getPassword());
        viewRestaurantState.setRestaurants(viewRestaurantOutputData.getLocalRestaurants());
        viewRestaurantState.setLocation(viewRestaurantOutputData.getLocation());
        //System.out.println("Restaurants received at presentor are " + viewRestaurantOutputData.getLocalRestaurants().toString());
        SortAndFilterState sortAndFilterState = sortAndFilterViewModel.getState();
        sortAndFilterState.setLocation(viewRestaurantOutputData.getLocation());
        this.sortAndFilterViewModel.setState(sortAndFilterState);
        sortAndFilterViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(sortAndFilterViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

        this.viewRestaurantViewModel.setState(viewRestaurantState);
        viewRestaurantViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(viewRestaurantViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
