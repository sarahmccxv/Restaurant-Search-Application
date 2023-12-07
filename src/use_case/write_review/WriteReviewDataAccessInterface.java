package use_case.write_review;

import entity.Review;

public interface WriteReviewDataAccessInterface {
    boolean existsByID(String reviewID);
    void save(Review review);
    void update();
    Review getByReviewID(String reviewID);
}
