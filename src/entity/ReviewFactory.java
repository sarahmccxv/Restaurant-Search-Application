package entity;

import java.time.LocalDateTime;

public class ReviewFactory {
    public Review create(String reviewID, User author, Restaurant restaurant, Float rating,
                         String content, LocalDateTime creationTime) {
        return new Review(reviewID, author, restaurant, rating, content, creationTime);
    }
}
