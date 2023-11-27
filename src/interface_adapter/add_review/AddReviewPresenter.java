package interface_adapter.add_review;

import interface_adapter.ViewManagerModel;
import use_case.add_review.AddReviewOutputBoundary;
import use_case.add_review.AddReviewOutputData;

public class AddReviewPresenter implements AddReviewOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final AddReviewViewModel addReviewViewModel;

    public AddReviewPresenter(ViewManagerModel viewManagerModel,
                              AddReviewViewModel addReviewViewModel){
        this.viewManagerModel = viewManagerModel;
        this.addReviewViewModel = addReviewViewModel;
    }
    public void prepareSuccessView(AddReviewOutputData addReviewOutputData) {
        AddReviewState state = addReviewViewModel.getState();
        state.setUser(addReviewOutputData.getUser());
        state.setRestaurant(addReviewOutputData.getRestaurant());
        state.setPrevious_view(addReviewOutputData.getPreviousView());
        this.addReviewViewModel.setState(state);
        this.addReviewViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(addReviewViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
