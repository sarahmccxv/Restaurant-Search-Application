package interface_adapter.remove_favourite;

import entity.FavouritesList;
import interface_adapter.ViewManagerModel;
import interface_adapter.view_favourites.ViewFavouritesState;
import interface_adapter.view_favourites.ViewFavouritesViewModel;
import use_case.remove_favourite.RemoveFavouriteOutputBoundary;
import use_case.remove_favourite.RemoveFavouriteOutputData;

public class RemoveFavouritePresenter implements RemoveFavouriteOutputBoundary {
    ViewFavouritesViewModel viewFavouritesViewModel;
    RemoveFavouriteViewModel removeFavouriteViewModel;
    ViewManagerModel viewManagerModel;

    public RemoveFavouritePresenter(RemoveFavouriteViewModel removeFavouriteViewModel,
                                     ViewManagerModel viewManagerModel, ViewFavouritesViewModel viewFavouritesViewModel){
        this.removeFavouriteViewModel = removeFavouriteViewModel;
        this.viewManagerModel = viewManagerModel;
        this.viewFavouritesViewModel = viewFavouritesViewModel;
    }

    public void prepareSuccessView(RemoveFavouriteOutputData removeFavouriteOutputData){
        ViewFavouritesState viewFavouritesState = viewFavouritesViewModel.getState();
        FavouritesList newFavouritesList = removeFavouriteOutputData.getNewFavouritesList();
        viewFavouritesState.setFavouritesList(newFavouritesList);
        if (newFavouritesList.isEmpty()){
            viewFavouritesState.setSuccess(false);
        }
        viewFavouritesViewModel.setState(viewFavouritesState);
        viewFavouritesViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(viewFavouritesViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
        RemoveFavouriteState removeFavouriteState = removeFavouriteViewModel.getState();
        removeFavouriteState.setMessage(removeFavouriteOutputData.getMessage());
        removeFavouriteViewModel.setState(removeFavouriteState);
        removeFavouriteViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(removeFavouriteViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
