package interface_adapter.add_to_favourites;

import interface_adapter.ViewManagerModel;
import use_case.add_to_favourites.AddToFavouritesOutputBoundary;
import use_case.add_to_favourites.AddToFavouritesOutputData;

public class AddToFavouritesPresenter implements AddToFavouritesOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final AddToFavouritesViewModel addToFavouritesViewModel;

    public AddToFavouritesPresenter(ViewManagerModel viewManagerModel,
                                    AddToFavouritesViewModel addToFavouritesViewModel){
        this.viewManagerModel = viewManagerModel;
        this.addToFavouritesViewModel = addToFavouritesViewModel;
    }

    public void prepareSuccessView(AddToFavouritesOutputData addToFavouritesOutputData){
        AddToFavouritesState state = addToFavouritesViewModel.getState();
        state.setUsername(addToFavouritesOutputData.getUsername());
        state.setRestaurantName(addToFavouritesOutputData.getRestaurantName());
        state.setMessage(addToFavouritesOutputData.getSuccessMessage());
        this.addToFavouritesViewModel.setState(state);
        this.addToFavouritesViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(addToFavouritesViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    public void prepareFailView(AddToFavouritesOutputData addToFavouritesOutputData){
        AddToFavouritesState state = addToFavouritesViewModel.getState();
        state.setUsername(addToFavouritesOutputData.getUsername());
        state.setRestaurantName(addToFavouritesOutputData.getRestaurantName());
        state.setMessage(addToFavouritesOutputData.getFailureMessage());
        this.addToFavouritesViewModel.setState(state);
        this.addToFavouritesViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(addToFavouritesViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
