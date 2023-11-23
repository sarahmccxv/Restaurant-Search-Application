package interface_adapter.sort_and_filter;

import api.Search.SearchCriteria;
import api.Search.SearchPriceLevel;
import api.Search.SearchSortingMethods;
import interface_adapter.ViewManagerModel;
import use_case.sortandfilter.SortAndFilterRestaurantOutputBoundary;
import use_case.sortandfilter.SortAndFilterRestaurantOutputData;

public class SortAndFilterPresenter implements SortAndFilterRestaurantOutputBoundary {
    private final SortAndFilterViewModel sortAndFilterViewModel;
    private final ViewManagerModel viewManagerModel;


    public SortAndFilterPresenter(ViewManagerModel viewManagerModel,
                                  SortAndFilterViewModel sortAndFilterViewModel) {
        this.sortAndFilterViewModel = sortAndFilterViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(SortAndFilterRestaurantOutputData sortAndFilterRestaurantOutputData) {
        SortAndFilterState sortAndFilterState = sortAndFilterViewModel.getState();
        this.sortAndFilterViewModel.setState(sortAndFilterState);
        this.sortAndFilterViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(sortAndFilterViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {

    }

    @Override
    public void prepareFailView(String username, String password, String noFavouritesMessage) {
        SortAndFilterState sortAndFilterState = sortAndFilterViewModel.getState();
        this.sortAndFilterViewModel.setState(sortAndFilterState);
        this.sortAndFilterViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(sortAndFilterViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
