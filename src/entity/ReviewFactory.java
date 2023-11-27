package entity;

import java.time.LocalDateTime;

public class ReviewFactory {
    public Review create(String reviewID, User author, String restaurantID, Float rating,
                         String content, LocalDateTime creationTime) {
        return new Review(reviewID, author, restaurantID, rating, content, creationTime);
    }
}
