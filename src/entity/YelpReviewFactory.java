package entity;

import java.time.LocalDateTime;

public class YelpReviewFactory {
    public YelpReview create(String reviewID, YelpUser author, Restaurant restaurant, Float rating,
                         String content, LocalDateTime creationTime) {
        return new YelpReview(reviewID, author, restaurant, rating, content, creationTime);
    }
}
