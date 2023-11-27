package entity;

import java.time.LocalDateTime;

public class YelpReview extends CommonReview implements ReviewInterface {
    private final YelpUser author;

    public YelpReview(String reviewID, YelpUser author, String restaurantID, Float rating,
                  String content, LocalDateTime creationTime) {
        super(reviewID, author, restaurantID, rating, content, creationTime);
        this.author = author;
    }

    @Override
    public YelpUser getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return super.getReviewID() + "\n User: " + author.getUserID() +
                ", " + author.getUsername() +
                "\n Rating: " + super.getRating() +
                "\n CreationTime: " + super.getCreationTime();
    }
}
