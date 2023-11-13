package interface_adapter.view_favourites;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import use_case.view_favourites.ViewFavouritesOutputBoundary;
import use_case.view_favourites.ViewFavouritesOutputData;

public class ViewFavouritesPresenter implements ViewFavouritesOutputBoundary {
    private final ViewFavouritesViewModel viewFavouritesViewModel;
    private final ViewManagerModel viewManagerModel;

    public ViewFavouritesPresenter(ViewManagerModel viewManagerModel,
                          ViewFavouritesViewModel viewFavouritesViewModel) {
        this.viewFavouritesViewModel = viewFavouritesViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(ViewFavouritesOutputData viewFavouritesOutputData){
        ViewFavouritesState viewFavouritesState = viewFavouritesViewModel.getState();
        viewFavouritesState.setUsername(viewFavouritesOutputData.getUsername());
        viewFavouritesState.setPassword(viewFavouritesOutputData.getPassword());
        viewFavouritesState.setFavourites(viewFavouritesOutputData.getFavouritesList());
        this.viewFavouritesViewModel.setState(viewFavouritesState);
        this.viewFavouritesViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(viewFavouritesViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String noFavouritesMessage){
        ViewFavouritesState viewFavouritesState = viewFavouritesViewModel.getState();
        viewFavouritesState.setNoFavourites(noFavouritesMessage);
        this.viewFavouritesViewModel.setState(viewFavouritesState);
        this.viewFavouritesViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(viewFavouritesViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
