package interface_adapter.sort_and_filter;

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
        //System.out.println("sort and filter presenter executed");
        sortAndFilterState.setCriteria(sortAndFilterRestaurantOutputData.getCriteria());
        sortAndFilterState.setRestaurants(sortAndFilterRestaurantOutputData.getRestaurants());
        sortAndFilterState.setPreviousView(sortAndFilterRestaurantOutputData.getPreviousView());
        //System.out.println("previous view is" + sortAndFilterRestaurantOutputData.getPreviousView());
        this.sortAndFilterViewModel.setState(sortAndFilterState);
        this.sortAndFilterViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(sortAndFilterViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
        //System.out.println("sort and filter view should change");
    }

    public void prepareFailView(String error) {
        SortAndFilterState sortAndFilterState = sortAndFilterViewModel.getState();
        this.sortAndFilterViewModel.setState(sortAndFilterState);
        this.sortAndFilterViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(sortAndFilterViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
