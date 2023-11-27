package entity;

import java.time.LocalDateTime;

public class YelpReviewFactory {
    public YelpReview create(String reviewID, YelpUser author, String restaurantID, Float rating,
                         String content, LocalDateTime creationTime) {
        return new YelpReview(reviewID, author, restaurantID, rating, content, creationTime);
    }
}
