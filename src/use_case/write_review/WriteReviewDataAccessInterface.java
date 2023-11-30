package use_case.write_review;

import entity.Restaurant;
import entity.Review;
import entity.User;

public interface WriteReviewDataAccessInterface {
    boolean existsByID(String reviewID);
    void save(Review review);
    void update();
    Review getByUserID(String userID);
}
