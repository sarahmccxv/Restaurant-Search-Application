package interface_adapter.view_favourites;

import interface_adapter.ViewManagerModel;
import use_case.view_favourites.ViewFavouritesOutputBoundary;
import use_case.view_favourites.ViewFavouritesOutputData;

public class ViewFavouritesPresenter implements ViewFavouritesOutputBoundary {

    private final ViewFavouritesViewModel viewFavouritesViewModel;
    private final ViewManagerModel viewManagerModel;

    public ViewFavouritesPresenter(ViewManagerModel viewManagerModel,
                          ViewFavouritesViewModel clearViewModel) {
        this.viewFavouritesViewModel = clearViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(ViewFavouritesOutputData viewFavouritesOutputData){
        ViewFavouritesState viewFavouritesState = viewFavouritesViewModel.getState();
        viewFavouritesViewModel.firePropertyChanged();
    }
}
