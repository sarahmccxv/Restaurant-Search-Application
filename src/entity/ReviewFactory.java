package entity;

import java.time.LocalDateTime;

public class ReviewFactory {
    public Review create(int reviewID, User author, Float rating,
                         String content, LocalDateTime creationTime) {
        return new Review(reviewID, author, rating, content, creationTime);
    }
}
