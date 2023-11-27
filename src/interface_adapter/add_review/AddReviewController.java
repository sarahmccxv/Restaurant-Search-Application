package interface_adapter.add_review;

import entity.Restaurant;
import entity.User;
import use_case.add_review.AddReviewInputBoundary;
import use_case.add_review.AddReviewInputData;
import use_case.add_review.AddReviewInteractor;

public class AddReviewController {
    final AddReviewInputBoundary addReviewInteractor;

    public AddReviewController(AddReviewInputBoundary addReviwInputBoundary) {
        this.addReviewInteractor = addReviwInputBoundary;
    }

    public void execute(String userID, Restaurant restaurant, String previousView) {
        AddReviewInputData addReviewInputData = new AddReviewInputData(userID, restaurant, previousView);
        addReviewInteractor.execute(addReviewInputData);
    }

}
