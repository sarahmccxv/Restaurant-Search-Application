package interface_adapter.sort_and_filter;

import interface_adapter.ViewManagerModel;
import use_case.view_favourites.ViewFavouritesOutputBoundary;
import use_case.view_favourites.ViewFavouritesOutputData;

public class SortAndFilterPresenter implements ViewFavouritesOutputBoundary {
    private final SortAndFilterViewModel sortAndFilterViewModel;
    private final ViewManagerModel viewManagerModel;

    public SortAndFilterPresenter(ViewManagerModel viewManagerModel,
                                  SortAndFilterViewModel sortAndFilterViewModel) {
        this.sortAndFilterViewModel = sortAndFilterViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(ViewFavouritesOutputData viewFavouritesOutputData){
        SortAndFilterState sortAndFilterState = sortAndFilterViewModel.getState();
        sortAndFilterState.setUsername(viewFavouritesOutputData.getUsername());
        sortAndFilterState.setPassword(viewFavouritesOutputData.getPassword());
        sortAndFilterState.setFavourites(viewFavouritesOutputData.getFavouritesList());
        this.sortAndFilterViewModel.setState(sortAndFilterState);
        this.sortAndFilterViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(sortAndFilterViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String username, String password, String noFavouritesMessage){
        SortAndFilterState sortAndFilterState = sortAndFilterViewModel.getState();
        sortAndFilterState.setUsername(username);
        sortAndFilterState.setPassword(password);
        sortAndFilterState.setNoFavouritesMessage(noFavouritesMessage);
        this.sortAndFilterViewModel.setState(sortAndFilterState);
        this.sortAndFilterViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(sortAndFilterViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
