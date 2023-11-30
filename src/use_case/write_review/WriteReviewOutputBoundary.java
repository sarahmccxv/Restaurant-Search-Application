package use_case.write_review;

public interface WriteReviewOutputBoundary {
    void prepareSuccessView(WriteReviewOutputData writeReviewOutputData);

    void prepareFailureView(WriteReviewOutputData writeReviewOutputData);
}
