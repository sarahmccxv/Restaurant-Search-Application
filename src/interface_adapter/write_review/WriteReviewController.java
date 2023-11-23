package interface_adapter.write_review;

import entity.Restaurant;
import use_case.write_review.WriteReviewInputBoundary;
import use_case.write_review.WriteReviewInputData;
import use_case.write_review.WriteReviewInteractor;

public class WriteReviewController {
    final WriteReviewInputBoundary writeReviewInteractor;

    public WriteReviewController(WriteReviewInputBoundary writeReviewInteractor) {
        this.writeReviewInteractor = writeReviewInteractor;
    }

    public void execute(String userID, Restaurant restaurant) {
        WriteReviewInputData writeReviewInputData = new WriteReviewInputData(userID, restaurant);
        writeReviewInteractor.execute(writeReviewInputData);
    }
}
