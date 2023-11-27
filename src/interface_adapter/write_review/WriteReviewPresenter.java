package interface_adapter.write_review;

import interface_adapter.ViewManagerModel;
import use_case.write_review.WriteReviewInteractor;
import use_case.write_review.WriteReviewOutputBoundary;
import use_case.write_review.WriteReviewOutputData;

public class WriteReviewPresenter implements WriteReviewOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final WriteReviewViewModel writeReviewViewModel;

    public WriteReviewPresenter(ViewManagerModel viewManagerModel,
                                WriteReviewViewModel writeReviewViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.writeReviewViewModel = writeReviewViewModel;
    }
    @Override
    public void prepareSuccessView(WriteReviewOutputData writeReviewOutputData) {
        WriteReviewState state = writeReviewViewModel.getState();
        state.setUser(writeReviewOutputData.getUser());
        state.setRestaurant(writeReviewOutputData.getRestaurant());
        state.setMessage(writeReviewOutputData.getSuccessMessage());
        this.writeReviewViewModel.setState(state);
        this.writeReviewViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(writeReviewViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailureView(WriteReviewOutputData writeReviewOutputData) {
        WriteReviewState state = writeReviewViewModel.getState();
        state.setUser(writeReviewOutputData.getUser());
        state.setRestaurant(writeReviewOutputData.getRestaurant());
        state.setMessage(writeReviewOutputData.getFailureMessage());
        this.writeReviewViewModel.setState(state);
        this.writeReviewViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(writeReviewViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
