package interface_adapter.write_review;

import entity.Restaurant;
import entity.User;
import use_case.write_review.WriteReviewInputBoundary;
import use_case.write_review.WriteReviewInputData;
import use_case.write_review.WriteReviewInteractor;

public class WriteReviewController {
    final WriteReviewInputBoundary writeReviewInteractor;

    public WriteReviewController(WriteReviewInputBoundary writeReviewInteractor) {
        this.writeReviewInteractor = writeReviewInteractor;
    }

    public void execute(User user, Restaurant restaurant, String rating, String content) {
        WriteReviewInputData writeReviewInputData =
                new WriteReviewInputData(user, restaurant, rating, content);
        writeReviewInteractor.execute(writeReviewInputData);
    }
}
