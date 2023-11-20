package use_case.write_review;

import use_case.view_favourites.ViewFavouritesInputData;

public interface WriteReviewInputBoundary {
    void execute(WriteReviewInputData writeReviewInputData);
}
