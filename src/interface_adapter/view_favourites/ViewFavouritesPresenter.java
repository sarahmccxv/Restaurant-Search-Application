package interface_adapter.view_favourites;

import interface_adapter.ViewManagerModel;
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
        viewFavouritesState.setUserID(viewFavouritesOutputData.getUserID());
        viewFavouritesState.setUsername(viewFavouritesOutputData.getUsername());
        viewFavouritesState.setPassword(viewFavouritesOutputData.getPassword());
        viewFavouritesState.setFavouritesList(viewFavouritesOutputData.getFavouritesList());
        viewFavouritesState.setSuccess(true);
        this.viewFavouritesViewModel.setState(viewFavouritesState);
        this.viewFavouritesViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(viewFavouritesViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String username, String password, String noFavouritesMessage){
        ViewFavouritesState viewFavouritesState = viewFavouritesViewModel.getState();
        viewFavouritesState.setUsername(username);
        viewFavouritesState.setPassword(password);
        viewFavouritesState.setNoFavouritesMessage(noFavouritesMessage);
        viewFavouritesState.setSuccess(false);
        this.viewFavouritesViewModel.setState(viewFavouritesState);
        this.viewFavouritesViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(viewFavouritesViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
